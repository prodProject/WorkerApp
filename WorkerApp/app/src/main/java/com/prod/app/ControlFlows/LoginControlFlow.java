package com.prod.app.ControlFlows;

import android.content.Context;

import com.prod.app.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.prod.app.Helper.LoginHelper;
import com.prod.app.Helper.LoginHelper;
import com.prod.app.SessionsManger.WorkerSession;
import com.prod.app.clientServices.LoginClientService;
import com.prod.app.protobuff.Login;
import com.prod.basic.common.exception.VoidException;
import com.prod.basic.common.httpCommon.Interfaces.IFuture;

public class LoginControlFlow {
    private LoginHelper m_helper;
    private LoginClientService m_loginService;
    private WorkerSession m_workerSession;

    public LoginControlFlow() {
        m_helper = new LoginHelper();
        m_loginService = new LoginClientService();
        m_workerSession = new WorkerSession();
    }

    public IFuture<Void, VoidException> startLogin(String emailOrPhone, String password) {
        LoginCF cf = new LoginCF(m_helper, m_loginService, m_workerSession, emailOrPhone, password);
        cf.addLogObjects(cf);
        cf.startAsyncCall();
        return cf.getFutureResult();

    }

}
