package com.prod.app.ControlFlows;

import android.util.Log;

import com.prod.app.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.prod.app.Helper.LoginHelper;
import com.prod.app.SessionsManger.WorkerSession;
import com.prod.app.clientServices.LoginClientService;
import com.prod.app.clientServices.RegistrationClientService;
import com.prod.app.protobuff.Login;
import com.prod.app.protobuff.Registration;
import com.prod.app.protobuff.Responsestatusenum;
import com.prod.basic.common.async.AControlFlow;
import com.prod.basic.common.exception.VoidException;

public class LoginCF extends AControlFlow<LoginCF.State, Void, VoidException> {

    private final LoginHelper m_helper;
    private final LoginClientService m_loginService;
    private final WorkerSession m_workerSession;
    private Login.LoginRequestPb m_loginRequestPb;
    private Login.LoginResponsePb m_loginResponsePb;

    private String m_emailOrPhone;
    private String m_password;

    enum State {
        GET_LOGINPB,
        PERFORM_LOGIN,
        SET_WORKER_SESSION,
        DONE,
    }


    public LoginCF(LoginHelper helper, LoginClientService loginService, WorkerSession workerSession, String emailOrPhone, String password) {
        super(State.GET_LOGINPB, State.DONE);
        m_helper = helper;
        m_loginService = loginService;
        m_workerSession = workerSession;
        m_emailOrPhone = emailOrPhone;
        m_password = password;
        addStateHandler(State.GET_LOGINPB, new GetLoginPbHandler());
        addStateHandler(State.PERFORM_LOGIN, new PerformLoginHandler());
        addStateHandler(State.SET_WORKER_SESSION, new SetWorkerSessionHandler());
    }

    private class GetLoginPbHandler implements StateHandler<State> {
        @Override
        public void registerCalls() {

        }

        @Override
        public LoginCF.State handleState() {
            m_loginRequestPb = m_helper.getLoginRequestPb(m_emailOrPhone, m_password);
            return State.PERFORM_LOGIN;
        }

    }

    private class PerformLoginHandler implements StateHandler<State> {
        Login.LoginResponsePb response;

        @Override
        public void registerCalls() {
            response = m_loginService.doLogin(m_loginRequestPb);
            registerFutures();
        }

        @Override
        public LoginCF.State handleState() {
            if (response != null) {
                m_loginResponsePb = response;

                Log.e("status", m_loginResponsePb.getStatus().getStatusType().name());
                if (m_loginResponsePb.getStatus().getStatusType() == Responsestatusenum.ResponseSatusEnum.SUCCESS) {

                    return State.SET_WORKER_SESSION;
                } else {
                    return State.DONE;
                }
            } else {
                return State.DONE;
            }
        }
    }

    private class SetWorkerSessionHandler implements StateHandler<State> {


        @Override
        public void registerCalls() {

        }

        @Override
        public State handleState() {
            m_workerSession.setSession(m_loginResponsePb.getWorker());
            return State.DONE;
        }
    }

}