package com.prod.app.ControlFlows;

import com.prod.app.protobuff.Login;
import com.prod.basic.common.async.AControlFlow;
import com.prod.basic.common.exception.VoidException;

public class DeviceAutoLoginCF extends AControlFlow<DeviceAutoLoginCF.State, Void, VoidException> {

    public Login.LoginPb m_loginPb;
    public LoginControlFlow m_loginControlFlow;

    public DeviceAutoLoginCF(Login.LoginPb loginPb, LoginControlFlow loginControlFlow) {
        super(State.GET_LOGIN_FROM_LOCAL_DB, State.DONE);
        m_loginPb = loginPb;
        m_loginControlFlow = loginControlFlow;
    }

    enum State {
        GET_LOGIN_FROM_LOCAL_DB,
        CALL_FOR_LOGIN,
        DONE,
    }

}
