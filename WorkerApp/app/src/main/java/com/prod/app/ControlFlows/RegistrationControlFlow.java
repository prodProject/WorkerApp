package com.prod.app.ControlFlows;

import android.content.Context;

import com.prod.app.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.prod.app.Helper.RegistrationHelper;
import com.prod.app.SessionsManger.WorkerSession;
import com.prod.app.clientServices.RegistrationClientService;
import com.prod.app.protobuff.Registration;
import com.prod.basic.common.exception.VoidException;
import com.prod.basic.common.httpCommon.Interfaces.IFuture;

public class RegistrationControlFlow {

    private RegistrationHelper m_helper;
    private LoginEntityDaoHelper m_loginEntityDaoHelper;
    private RegistrationClientService m_registrationService;
    private WorkerSession m_workerSession;

    public RegistrationControlFlow(Context context) {
        m_helper = new RegistrationHelper();
        m_loginEntityDaoHelper = new LoginEntityDaoHelper(context);
        m_registrationService = new RegistrationClientService();
        m_workerSession = new WorkerSession();
    }

    public IFuture<Void, VoidException> startRegistration(Registration.RegistrationRequestPb request) {
        RegistrationCF cf = new RegistrationCF(request, m_helper, m_loginEntityDaoHelper, m_registrationService, m_workerSession);
        cf.addLogObjects(cf);
        cf.startAsyncCall();
        return cf.getFutureResult();

    }

}
