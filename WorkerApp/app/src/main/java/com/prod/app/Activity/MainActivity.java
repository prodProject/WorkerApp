package com.prod.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.prod.app.BasicCache.LoginCache;
import com.prod.app.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.prod.app.LocalDatabase.DaoSession;
import com.prod.app.LocalDatabase.DatabaseInitHandler;
import com.prod.app.R;
import com.prod.app.ServerConfig.ServerUrlManeger;
import com.prod.app.Session.SessionManager;
import com.prod.app.SessionsManger.WorkerSession;
import com.prod.app.clientServices.RegistrationClientService;
import com.prod.app.protobuff.Persontypeenum;
import com.prod.app.protobuff.Worker;
import com.prod.basic.common.httpCommon.Enums.RequestMethodEnum;

public class MainActivity extends AppCompatActivity {

    private ServerUrlManeger m_serverManeger;
    private Button click;
    private RegistrationClientService m_service;
    private static Context context;
    private static RequestQueue mRequestQueue;
    private DatabaseInitHandler databaseInitHandler;
    private DaoSession daoSession;
    private LoginEntityDaoHelper m_LoginEntityDaoHelper;
    private WorkerSession m_session;
    private SessionManager m_maneger;

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
                Log.e("value", m_session.getSession().getType().getPersonType().name());
                Intent i = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(i);
            }
        });
    }


}