package com.prod.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.prod.app.Async.AsyncJob;
import com.prod.app.BasicCache.LoginCache;
import com.prod.app.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.prod.app.LocalDatabase.DaoSession;
import com.prod.app.LocalDatabase.DatabaseInitHandler;
import com.prod.app.LocalDatabase.LoginEntity;
import com.prod.app.Module.DeviceAutoLogin;
import com.prod.app.R;
import com.prod.app.ServerConfig.ServerUrlManeger;
import com.prod.app.Session.SessionManager;
import com.prod.app.SessionsManger.WorkerSession;
import com.prod.app.Utility.AndroidUtility;
import com.prod.app.clientServices.RegistrationClientService;
import com.prod.app.clientServices.WorkerClientService;
import com.prod.app.protobuff.Entity;
import com.prod.app.protobuff.Mobile;
import com.prod.app.protobuff.Persontypeenum;
import com.prod.app.protobuff.Registration;
import com.prod.app.protobuff.Worker;
import com.prod.basic.common.httpCommon.Enums.RequestMethodEnum;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class SplashScreen extends AppCompatActivity {

    private ServerUrlManeger m_serverManeger;
    private Button click;
    private RegistrationClientService m_service;
    private DatabaseInitHandler databaseInitHandler;
    private DaoSession daoSession;
    private WorkerSession m_session;
    private SessionManager m_maneger;

    @Inject
    private LoginEntityDaoHelper m_LoginEntityDaoHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  onViewCreated(new View(this),savedInstanceState);
        m_LoginEntityDaoHelper = new LoginEntityDaoHelper(getApplicationContext());
        m_serverManeger = new ServerUrlManeger();
        m_maneger = new SessionManager(getApplicationContext());
        click = (Button) findViewById(R.id.button);
        m_service = new RegistrationClientService(RequestMethodEnum.POST);
        Worker.WorkerPb.Builder bu = Worker.WorkerPb.newBuilder();
        bu.getTypeBuilder().setPersonType(Persontypeenum.PersonTypeEnum.WORKER);
        m_session = new WorkerSession();
        m_session.setSession(bu.build());
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (m_LoginEntityDaoHelper.getLoginPbFromInternalStorage(1l) != null) {
                        Log.e("Msg", "Login Present" + m_LoginEntityDaoHelper.getDeoEntity().load(1l).getData());
                        new DeviceAutoLogin(m_LoginEntityDaoHelper.getLoginPbFromInternalStorage(1l), getApplicationContext());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
               // AndroidUtility.startActivity(getApplicationContext(),WorkerDataActivity.class);
            }
        });
    }


}