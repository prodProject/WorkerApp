package com.prod.app.Widget.LoginWidget;

import android.content.Context;

import com.prod.app.Interfaces.IContext;
import com.prod.app.clientServices.LoginClientService;

public class LoginView implements IContext {

    private Context m_context;
    private LoginClientService m_loginClientService;

    public LoginView(){
        m_loginClientService = new LoginClientService();
    }

    @Override
    public Context getActivityContext() {
        return m_context;
    }

    @Override
    public void setActivityContext(Context context) {
        m_context = context;
    }
}
