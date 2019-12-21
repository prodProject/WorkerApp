package com.prod.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
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
import com.prod.app.Widget.DownloadImageWidget.DownloadImageWidget;
import com.prod.app.Widget.FirebaseWidget.FirebaseFileWidget;
import com.prod.app.Widget.OtpVerificationWidget.OtpVerificationWidget;
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

import static com.google.firebase.messaging.FirebaseMessaging.getInstance;

public class SplashScreen extends AppCompatActivity {

    private ServerUrlManeger m_serverManeger;
    //   private Button click;
    private RegistrationClientService m_service;
    private DatabaseInitHandler databaseInitHandler;
    private DaoSession daoSession;
    private WorkerSession m_session;
    private SessionManager m_maneger;
    private FirebaseFileWidget m_firebaseFileWidget;

    private DownloadImageWidget m_downloadImageWidget;

    @Inject
    private LoginEntityDaoHelper m_LoginEntityDaoHelper;

    String url = "https://firebasestorage.googleapis.com/v0/b/workerandconsumerapp.appspot.com/o/Images%2Fjpg?alt=media&token=faebb42b-9148-4e4c-9aaf-6b22c0298b26";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        //  onViewCreated(new View(this),savedInstanceState);
        m_LoginEntityDaoHelper = new LoginEntityDaoHelper(getApplicationContext());
        m_serverManeger = new ServerUrlManeger();
        m_maneger = new SessionManager(getApplicationContext());
        //   click = (Button) findViewById(R.id.button);
        m_service = new RegistrationClientService(RequestMethodEnum.POST);
        Worker.WorkerPb.Builder bu = Worker.WorkerPb.newBuilder();
        bu.getTypeBuilder().setPersonType(Persontypeenum.PersonTypeEnum.WORKER);
        m_session = new WorkerSession();
        m_session.setSession(bu.build());

        //m_firebaseFileWidget = findViewById(R.id.firebaseWidget);
       // m_downloadImageWidget = findViewById(R.id.firebaseWidget);
       // m_downloadImageWidget.getImageFromUrl(url);
        /*click.setOnClickListener(new View.OnClickListener() {
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
        });*/
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("notify", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast

                        Log.d("notify", token);
                        Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        m_firebaseFileWidget.onActivityResult(requestCode, resultCode, data);
    }*/
}