package com.prod.app.ControlFlows;

import com.prod.app.protobuff.Login;
import com.prod.basic.common.async.AControlFlow;
import com.prod.basic.common.exception.VoidException;

public class DeviceAutoLoginCF extends AControlFlow<DeviceAutoLoginCF.State, Void, VoidException> {

    public Login.LoginPb m_loginPb;

    public DeviceAutoLoginCF(Login.LoginPb loginPb) {
        super(State.GET_LOGIN_FROM_LOCAL_DB, State.DONE);
        m_loginPb = loginPb;
    }

    enum State {
        GET_LOGIN_FROM_LOCAL_DB,
        CALL_FOR_LOGIN,
        DONE,
    }
}
