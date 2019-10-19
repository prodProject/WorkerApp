package com.prod.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.prod.app.R;
import com.prod.app.ServerConfig.ServerUrlManeger;
import com.prod.app.ServerConfig.UrlPathProvider;
import com.prod.app.clientServices.RegistrationClientService;
import com.prod.app.protobuff.Entity;
import com.prod.app.protobuff.Mobile;
import com.prod.app.protobuff.Persontypeenum;
import com.prod.app.protobuff.Registration;
import com.prod.basic.common.httpReqAndResp.HttpReqHandler;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private ServerUrlManeger m_serverManeger;
    private Button click;
    private RegistrationClientService m_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  onViewCreated(new View(this),savedInstanceState);
        m_serverManeger = new ServerUrlManeger();
        click = (Button) findViewById(R.id.button);
        m_service = new RegistrationClientService();
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registration.RegistrationRequestPb.Builder builder = Registration.RegistrationRequestPb.newBuilder();
                builder.getWorkerBuilder().getNameBuilder().setFirstName("Shubham").setLastName("Tiwari");
                builder.getWorkerBuilder().getDbInfoBuilder().setLifeTime(Entity.StatusEnum.ACTIVE);
                builder.getWorkerBuilder().getContactDetailsBuilder().getEmailBuilder().setDomain("shubhamtiwaricr07").setLocalPart("gmail.com");
                builder.getWorkerBuilder().getContactDetailsBuilder().getPrimaryMobileBuilder().setCode(Mobile.CountryCodeEnum.ISD_91).setNumber("1");
                builder.getWorkerBuilder().getContactDetailsBuilder().addSecondryMobileBuilder().setCode(Mobile.CountryCodeEnum.ISD_91).setNumber("1");
                builder.getWorkerBuilder().getTypeBuilder().setPersonType(Persontypeenum.PersonTypeEnum.WORKER);
                builder.setPassword("password");
                builder.getWorkerBuilder().getDeviceBuilder().setMacId("19:68:15:c4:77:ad");
                builder.getWorkerBuilder().getDeviceBuilder().setDeviceName("H@cker");
                builder.getWorkerBuilder().getDeviceBuilder().setModel("Redmi");
                builder.getWorkerBuilder().getDeviceBuilder().setOsType("ANDROID");
                Registration.RegistrationResponsePb.Builder response = null;
                try {
                    response = m_service.execute(builder.build()).get().toBuilder();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(response.getStatus()== Registration.ResponseStatusEnum.USER_EXIST)
                {
                    Toast.makeText(getApplicationContext(),response.getStatus().name().toLowerCase(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    /*public void onViewCreated(View view, Bundle savedInstanceState)
    {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
    }*/
}