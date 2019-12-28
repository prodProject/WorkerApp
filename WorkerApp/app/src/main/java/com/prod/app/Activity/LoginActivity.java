package com.prod.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.prod.app.Async.AsyncJob;
import com.prod.app.Helper.LoginHelper;
import com.prod.app.R;
import com.prod.app.SessionsManger.WorkerSession;
import com.prod.app.Utility.AndroidUtility;
import com.prod.app.clientServices.LoginClientService;
import com.prod.app.protobuff.Login;
import com.prod.app.protobuff.Responsestatusenum;
import com.prod.basic.common.httpCommon.Enums.RequestMethodEnum;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity {

    private EditText m_emailOrPhone;
    private EditText m_password;
    private Button m_login;

    @Inject
    private LoginHelper m_helper;
    @Inject
    private LoginClientService m_loginService;
    @Inject
    private WorkerSession m_workerSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        m_emailOrPhone = (EditText) findViewById(R.id.email);
        m_password = (EditText) findViewById(R.id.password);
        m_login = (Button) findViewById(R.id.login);
        m_helper = new LoginHelper();
        m_loginService = new LoginClientService();
        m_workerSession = new WorkerSession();
        m_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncJob.AsyncJobBuilder<Login.LoginResponsePb>()
                        .doInBackground(new AsyncJob.AsyncAction<Login.LoginResponsePb>() {
                            @Override
                            public Login.LoginResponsePb doAsync() {
                                    Login.LoginRequestPb req = m_helper.getLoginRequestPb(AndroidUtility.getTextFromEditText(m_emailOrPhone),
                                            AndroidUtility.getTextFromEditText(m_password));
                                    return m_loginService.doLogin(req);
                            }

                        })
                        .doWhenFinished(new AsyncJob.AsyncResultAction<Login.LoginResponsePb>() {
                            @Override
                            public void onResult(Login.LoginResponsePb result) {
                                if (result.getStatus().getStatusType() == Responsestatusenum.ResponseSatusEnum.SUCCESS) {
                                    m_workerSession.setSession(result.getWorker());
                                    Log.e("workerSession",m_workerSession.getSession().toString());
                                } else {
                                    Toast.makeText(getApplicationContext(), result.getStatus().getStatusType().name(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).create().start();
            }
        });

    }
}
