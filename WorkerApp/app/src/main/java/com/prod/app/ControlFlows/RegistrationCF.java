package com.prod.app.ControlFlows;

import com.prod.app.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.prod.app.Helper.RegistrationHelper;
import com.prod.app.SessionsManger.WorkerSession;
import com.prod.app.clientServices.RegistrationClientService;
import com.prod.app.protobuff.Registration;
import com.prod.basic.common.async.AControlFlow;
import com.prod.basic.common.exception.VoidException;


public class RegistrationCF extends AControlFlow<RegistrationCF.State, Void, VoidException> {


    private final RegistrationHelper m_helper;
    private final LoginEntityDaoHelper m_loginEntityDaoHelper;
    private final RegistrationClientService m_registrationService;
    private final WorkerSession m_workerSession;
    private Registration.RegistrationRequestPb m_registrationRequestPb;
    private Registration.RegistrationResponsePb m_registrationResponsePb;

    enum State {
        GET_REGISTRATIONPB,
        GET_PUSH_NOTIFICATION_TOKEN,
        CREATE_REGISTRATION,
        SET_LOGIN_TO_LOCAL_DB,
        SET_WORKER_SESSION,
        DONE,
    }

    public RegistrationCF(Registration.RegistrationRequestPb registrationRequestPb, RegistrationHelper helper, LoginEntityDaoHelper loginEntityDaoHelper, RegistrationClientService registrationService, WorkerSession workerSession) {
        super(State.GET_REGISTRATIONPB, State.DONE);
        m_helper = helper;
        m_loginEntityDaoHelper = loginEntityDaoHelper;
        m_registrationService = registrationService;
        m_workerSession = workerSession;
        m_registrationRequestPb = registrationRequestPb;
        addStateHandler(State.GET_REGISTRATIONPB, new GetRagistrationPbHandler());
        addStateHandler(State.GET_PUSH_NOTIFICATION_TOKEN, new GetPushNotificationTokenHandler());
        addStateHandler(State.CREATE_REGISTRATION, new CreateRagistrationHandler());
        addStateHandler(State.SET_LOGIN_TO_LOCAL_DB, new SetLoginToLocalDBHandler());
        addStateHandler(State.SET_WORKER_SESSION, new SetWorkerSessionHandler());
    }

    private class GetRagistrationPbHandler implements StateHandler<State> {

        @Override
        public void registerCalls() {

        }

        @Override
        public State handleState() {
            m_registrationRequestPb = m_helper.getRegistrationRequestPb(m_registrationRequestPb);
            return State.CREATE_REGISTRATION;
        }
    }

    private class GetPushNotificationTokenHandler implements StateHandler<State> {

        @Override
        public void registerCalls() {

        }

        @Override
        public State handleState() {
            m_registrationRequestPb.toBuilder().setPushNotificationToken("");
            return State.CREATE_REGISTRATION;
        }
    }

    private class CreateRagistrationHandler implements StateHandler<State> {
        Registration.RegistrationResponsePb response;

        @Override
        public void registerCalls() {
            response = m_registrationService.doRegistration(m_registrationRequestPb);
            registerFutures();
        }

        @Override
        public State handleState() {
            if (response != null) {
                m_registrationResponsePb = response;
                return State.SET_LOGIN_TO_LOCAL_DB;
            } else {
                return State.DONE;
            }
        }
    }

    private class SetLoginToLocalDBHandler implements StateHandler<State> {


        @Override
        public void registerCalls() {

        }

        @Override
        public State handleState() {
            m_loginEntityDaoHelper.getDeoEntity().insert(m_helper.getLoginEntityFromLoginPb(m_helper.getLoginPb(m_registrationResponsePb.getLogin(), m_registrationRequestPb.getPassword())));
            return State.SET_WORKER_SESSION;
        }
    }

    private class SetWorkerSessionHandler implements StateHandler<State> {


        @Override
        public void registerCalls() {

        }

        @Override
        public State handleState() {
            m_workerSession.setSession(m_registrationResponsePb.getWorker());
            return State.DONE;
        }
    }

}
