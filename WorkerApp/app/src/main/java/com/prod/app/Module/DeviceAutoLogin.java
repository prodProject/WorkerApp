package com.prod.app.Module;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.prod.app.Activity.HomeActivity;
import com.prod.app.Activity.WorkerDataActivity;
import com.prod.app.Async.AsyncJob;
import com.prod.app.Helper.DeviceHelper;
import com.prod.app.SessionsManger.WorkerSession;
import com.prod.app.Utility.AndroidUtility;
import com.prod.app.clientServices.LoginClientService;
import com.prod.app.protobuff.Login;
import com.prod.app.protobuff.Responsestatusenum;
import com.prod.app.protobuff.Workertype;
import com.prod.basic.common.httpCommon.Enums.RequestMethodEnum;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class DeviceAutoLogin {


    private Context m_Context;
    private Login.LoginPb m_loginPb;
    private DeviceHelper m_helper;
    @Inject
    private LoginClientService m_loginService;
    @Inject
    private WorkerSession m_workerSession;

    public DeviceAutoLogin(Login.LoginPb loginPb, Context context) {
        m_Context = context;
        m_loginPb = loginPb;
        m_helper = new DeviceHelper();
        m_loginService = new LoginClientService();
        m_workerSession = new WorkerSession();
        performLogin();
    }

    private void performLogin() {
        new AsyncJob.AsyncJobBuilder<Login.LoginResponsePb>()
                .doInBackground(new AsyncJob.AsyncAction<Login.LoginResponsePb>() {
                    @Override
                    public Login.LoginResponsePb doAsync() {

                            Login.LoginRequestPb req = m_helper.getLoginRequestPbFromLoginPb(m_loginPb);
                            return null;
                    }

                })
                .doWhenFinished(new AsyncJob.AsyncResultAction<Login.LoginResponsePb>() {
                    @Override
                    public void onResult(Login.LoginResponsePb result) {
                        if (result.getStatus().getStatusType() == Responsestatusenum.ResponseSatusEnum.SUCCESS) {
                            m_workerSession.setSession(result.getWorker());
                            Log.e("workerSession", m_workerSession.getSession().toString());
                            if (m_workerSession.getSession().getWorkerTypeConfig().getWorkerType() == Workertype.WorkerTypeEnum.UNKNOWN_WORKER_TYPE) {
                                AndroidUtility.startActivity(m_Context, WorkerDataActivity.class);
                            } else {
                                AndroidUtility.startActivity(m_Context, HomeActivity.class);
                            }
                        } else {
                            Toast.makeText(m_Context, result.getStatus().getStatusType().name(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).create().start();
    }

}
