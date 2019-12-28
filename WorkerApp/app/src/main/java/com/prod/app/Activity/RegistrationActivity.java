package com.prod.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.prod.app.Async.AsyncJob;
import com.prod.app.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.prod.app.Helper.RegistrationHelper;
import com.prod.app.R;
import com.prod.app.SessionsManger.WorkerSession;
import com.prod.app.Utility.AndroidUtility;
import com.prod.app.clientServices.RegistrationClientService;
import com.prod.app.protobuff.Registration;
import com.prod.app.protobuff.Responsestatusenum;
import com.prod.basic.common.httpCommon.Enums.RequestMethodEnum;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import static com.prod.app.protobuff.Responsestatusenum.*;

public class RegistrationActivity extends AppCompatActivity {

    private WorkerSession m_session;
    private EditText fname;
    private EditText lname;
    private EditText email;
    private EditText number;
    private EditText password;

    private Button click;
    private Button login;

    @Inject
    private RegistrationHelper m_helper;
    @Inject
    private LoginEntityDaoHelper m_loginEntityDaoHelper;
    @Inject
    private RegistrationClientService m_registrationService;
    @Inject
    private WorkerSession m_workerSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        m_helper = new RegistrationHelper();
        m_registrationService = new RegistrationClientService();
        m_loginEntityDaoHelper = new LoginEntityDaoHelper(getApplicationContext());
        m_workerSession = new WorkerSession();
        click = (Button) findViewById(R.id.signUp);
        login = (Button) findViewById(R.id.login);
        fname = (EditText) findViewById(R.id.firstname);
        lname = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.email);
        number = (EditText) findViewById(R.id.mobileNumber);
        password = (EditText) findViewById(R.id.password);

        m_session = new WorkerSession();
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncJob.AsyncJobBuilder<Registration.RegistrationResponsePb>()
                        .doInBackground(new AsyncJob.AsyncAction<Registration.RegistrationResponsePb>() {
                            @Override
                            public Registration.RegistrationResponsePb doAsync() {
                                try {
                                    Registration.RegistrationRequestPb req = m_helper.getRegistrationRequestPb(AndroidUtility.getTextFromEditText(fname),
                                            AndroidUtility.getTextFromEditText(lname),
                                            AndroidUtility.getTextFromEditText(email),
                                            AndroidUtility.getTextFromEditText(number),
                                            AndroidUtility.getTextFromEditText(password));
                                    return m_registrationService.doRegistration(req);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                return null;
                            }

                        })
                        .doWhenFinished(new AsyncJob.AsyncResultAction<Registration.RegistrationResponsePb>() {
                            @Override
                            public void onResult(Registration.RegistrationResponsePb result) {
                                if (result.getStatus().getStatusType() == ResponseSatusEnum.SUCCESS) {
                                    m_loginEntityDaoHelper.getDeoEntity().insert(m_helper.getLoginEntityFromLoginPb(m_helper.getLoginPb(result.getLogin(),AndroidUtility.getTextFromEditText(password))));
                                    m_workerSession.setSession(result.getWorker());
                                } else {
                                    Toast.makeText(getApplicationContext(), result.getStatus().getStatusType().name(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).create().start();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
