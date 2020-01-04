package com.prod.app.ControlFlows;

import android.content.Context;

import com.prod.app.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.prod.app.SessionsManger.WorkerSession;
import com.prod.app.clientServices.PushNotificationnClientService;
import com.prod.app.clientServices.WorkerClientService;
import com.prod.basic.common.exception.VoidException;
import com.prod.basic.common.httpCommon.Interfaces.IFuture;

public class UpdatePushNotificationControlFlow {

    private LoginEntityDaoHelper m_loginEntityDaoHelper;
    private PushNotificationnClientService m_pushNotificationClientService;
    private WorkerClientService m_workerClientService;
    private WorkerSession m_workerSession;

    public UpdatePushNotificationControlFlow(Context context) {

        m_loginEntityDaoHelper = new LoginEntityDaoHelper(context);
        m_pushNotificationClientService = new PushNotificationnClientService();
        m_workerClientService = new WorkerClientService();
        m_workerSession = new WorkerSession();
    }

    public IFuture<Void, VoidException> updatePushNotification(String token) {
        UpdatePushNotificationCF cf = new UpdatePushNotificationCF(token, m_loginEntityDaoHelper, m_pushNotificationClientService, m_workerSession, m_workerClientService);
        cf.addLogObjects(cf);
        cf.startAsyncCall();
        return cf.getFutureResult();

    }
}
