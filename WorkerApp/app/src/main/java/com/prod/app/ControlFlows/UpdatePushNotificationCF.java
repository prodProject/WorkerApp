package com.prod.app.ControlFlows;

import com.prod.app.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.prod.app.SessionsManger.WorkerSession;
import com.prod.app.clientServices.PushNotificationnClientService;
import com.prod.app.clientServices.WorkerClientService;
import com.prod.app.protobuff.Pushnotification;
import com.prod.app.protobuff.Worker;
import com.prod.basic.common.async.AControlFlow;
import com.prod.basic.common.exception.VoidException;

import java.io.IOException;

public class UpdatePushNotificationCF extends AControlFlow<UpdatePushNotificationCF.State, Void, VoidException> {

    private LoginEntityDaoHelper m_loginEntityDaoHelper;
    private PushNotificationnClientService m_pushNotificationClientService;
    private WorkerSession m_workerSession;
    private WorkerClientService m_workerClientService;
    private String m_token;
    private Worker.WorkerPb m_worker;
    private Pushnotification.PushNotificationPb m_pushNotification;
    private boolean counter = false;


    public UpdatePushNotificationCF(String token, LoginEntityDaoHelper loginEntityDaoHelper, PushNotificationnClientService pushNotificationClientService, WorkerSession workerSession, WorkerClientService workerClientService) {
        super(State.GET_WORKER, State.DONE);
        m_token = token;
        m_loginEntityDaoHelper = loginEntityDaoHelper;
        m_pushNotificationClientService = pushNotificationClientService;
        m_workerSession = workerSession;
        m_workerClientService = workerClientService;
        addStateHandler(State.GET_WORKER, new GetWokerHandler());
        addStateHandler(State.GET_PUSHNOTIFICATION, new GetPushNotificationHandler());
        addStateHandler(State.UPDATE_PUSHNOTIFICATION, new UpdatePushNotificationHandler());
        addStateHandler(State.UPDATE_WORKER_SESSION, new UpdateWorkerSessionHandler());
    }

    enum State {
        GET_WORKER,
        GET_PUSHNOTIFICATION,
        UPDATE_PUSHNOTIFICATION,
        UPDATE_WORKER_SESSION,
        DONE,
    }

    private class GetWokerHandler implements StateHandler<State> {

        @Override
        public void registerCalls() {
            if (!counter) {
                try {
                    m_worker = m_workerClientService.get(m_loginEntityDaoHelper.getLoginPbFromInternalStorage(1l).getWorkerRef().getDbInfo().getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                m_worker = m_workerClientService.get(m_worker.getDbInfo().getId());
            }
        }

        @Override
        public State handleState() {
            if (!counter) {
                counter = true;
                if (m_worker != null) {
                    return State.GET_PUSHNOTIFICATION;
                } else {
                    return State.DONE;
                }
            } else {
                if (m_worker != null) {
                    return State.UPDATE_WORKER_SESSION;
                } else {
                    return State.DONE;
                }
            }
        }
    }

    private class GetPushNotificationHandler implements StateHandler<State> {

        @Override
        public void registerCalls() {
            m_pushNotification = m_pushNotificationClientService.get(m_worker.getPushNotificationRef().getDbInfo().getId());
        }

        @Override
        public State handleState() {
            if (m_pushNotification != null) {
                return State.UPDATE_PUSHNOTIFICATION;
            } else {
                return State.DONE;
            }

        }
    }

    private class UpdatePushNotificationHandler implements StateHandler<State> {

        @Override
        public void registerCalls() {
            m_pushNotification.toBuilder().setTokenId(m_token);
            m_pushNotification = m_pushNotificationClientService.update(m_pushNotification);
        }

        @Override
        public State handleState() {
            if (m_pushNotification != null) {
                return State.GET_WORKER;
            } else {
                return State.DONE;
            }

        }
    }

    private class UpdateWorkerSessionHandler implements StateHandler<State> {

        @Override
        public void registerCalls() {

        }

        @Override
        public State handleState() {
            m_workerSession.setSession(m_worker);
            return State.DONE;
        }
    }
}
