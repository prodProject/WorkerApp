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

    }
}
