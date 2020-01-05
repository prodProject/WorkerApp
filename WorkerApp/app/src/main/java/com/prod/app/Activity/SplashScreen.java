package com.prod.app.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.prod.app.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.prod.app.Firebase.GetFirebasePushNotificationToken;
import com.prod.app.Helper.DeviceHelper;
import com.prod.app.LocalDatabase.DaoSession;
import com.prod.app.LocalDatabase.DatabaseInitHandler;
import com.prod.app.R;
import com.prod.app.ServerConfig.ServerUrlManeger;
import com.prod.app.Session.SessionManager;
import com.prod.app.SessionsManger.WorkerSession;
import com.prod.app.Widget.DownloadImageWidget.DownloadImageWidget;
import com.prod.app.Widget.FirebaseWidget.FirebaseFileWidget;
import com.prod.app.clientServices.PushNotificationnClientService;
import com.prod.app.clientServices.RegistrationClientService;
import com.prod.app.protobuff.Persontypeenum;
import com.prod.app.protobuff.Worker;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import javax.inject.Inject;

public class SplashScreen extends AppCompatActivity{

    private ServerUrlManeger m_serverManeger;
    //   private Button click;
    private RegistrationClientService m_service;
    private DatabaseInitHandler databaseInitHandler;
    private DaoSession daoSession;
    private WorkerSession m_session;
    private SessionManager m_maneger;
    private FirebaseFileWidget m_firebaseFileWidget;

    private DownloadImageWidget m_downloadImageWidget;

    private PushNotificationnClientService m_pushNotificationnClientService;
    private DeviceHelper m_deviceHelper;

    @Inject
    private LoginEntityDaoHelper m_LoginEntityDaoHelper;

    String url = "https://firebasestorage.googleapis.com/v0/b/workerandconsumerapp.appspot.com/o/Images%2Fjpg?alt=media&token=faebb42b-9148-4e4c-9aaf-6b22c0298b26";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_pushNotificationnClientService = new PushNotificationnClientService();
        m_deviceHelper = new DeviceHelper();
        getFirebaseConnection();
       // GetFirebasePushNotificationToken.getToken();
        //  onViewCreated(new View(this),savedInstanceState);
        m_LoginEntityDaoHelper = new LoginEntityDaoHelper(getApplicationContext());
        m_serverManeger = new ServerUrlManeger();
        m_maneger = new SessionManager(getApplicationContext());
        //   click = (Button) findViewById(R.id.button);
        m_service = new RegistrationClientService();
        Worker.WorkerPb.Builder bu = Worker.WorkerPb.newBuilder();
        bu.getTypeBuilder().setPersonType(Persontypeenum.PersonTypeEnum.WORKER);
        m_session = new WorkerSession();
        m_session.setSession(bu.build());

    }

    private void getFirebaseConnection() {
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("notify", "getInstanceId failed", task.getException());
                            return;
                        }
                        String token = task.getResult().getToken();
                        Log.w("notify", token);
                        SharedPreferences sharedPrefrences =  getApplicationContext().getSharedPreferences("firebaetokenId",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPrefrences.edit();
                        editor.putString("firebaetokenId", token);
                        editor.commit();
                    }
                });
    }


    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        m_firebaseFileWidget.onActivityResult(requestCode, resultCode, data);
    }*/
}