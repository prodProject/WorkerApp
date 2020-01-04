package com.prod.app.Widget.LoginWidget;

import android.content.Context;

import com.prod.app.ControlFlows.LoginControlFlow;
import com.prod.app.Interfaces.IContext;
import com.prod.app.Utility.AndroidUtility;
import com.prod.app.clientServices.LoginClientService;
import com.prod.basic.common.code.Strings;

public class LoginView implements IContext {

    private Context m_context;
    private LoginControlFlow m_loginControlFlow;

    public LoginView() {
        m_loginControlFlow = new LoginControlFlow();
    }

    @Override
    public Context getActivityContext() {
        return m_context;
    }

    @Override
    public void setActivityContext(Context context) {
        m_context = context;
    }

    public void startlogin(String emailOrPhone, String password) {

        if (Strings.notEmpty(emailOrPhone) && Strings.notEmpty(password)) {
            m_loginControlFlow.startLogin(emailOrPhone, password);
        } else {
            AndroidUtility.getMakeTextToastLong(getActivityContext(), "email and password can not be empty!!!!");
        }
    }
}
