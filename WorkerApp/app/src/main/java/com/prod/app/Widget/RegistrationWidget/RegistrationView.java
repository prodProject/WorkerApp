package com.prod.app.Widget.RegistrationWidget;

import android.content.Context;

import com.prod.app.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.prod.app.Helper.RegistrationHelper;
import com.prod.app.Interfaces.IContext;
import com.prod.app.SessionsManger.WorkerSession;
import com.prod.app.clientServices.RegistrationClientService;

public class RegistrationView implements IContext {

    private Context m_context;
    private RegistrationHelper m_helper;
    private LoginEntityDaoHelper m_loginEntityDaoHelper;
    private RegistrationClientService m_registrationClientService;
    private WorkerSession m_workerSession;

    public RegistrationView(){
        m_registrationClientService = new RegistrationClientService();
        m_helper = new RegistrationHelper();
        m_loginEntityDaoHelper= new LoginEntityDaoHelper(getActivityContext());
        m_workerSession = new WorkerSession();
    }

    @Override
    public Context getActivityContext() {
        return null;
    }

    @Override
    public void setActivityContext(Context context) {
        m_context = context;
    }
}
