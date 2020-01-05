package com.prod.app.clientServices;


import android.os.AsyncTask;

import com.prod.app.CommonCode.HttpCaller;
import com.prod.app.CommonCode.ProtoJsonUtil;
import com.prod.app.CommonCode.ProtoSerilizerAndDeserilizer;
import com.prod.app.ServerConfig.ServerUrlManeger;
import com.prod.app.ServerConfig.UrlPathProvider;
import com.prod.app.protobuff.Registration;
import com.prod.basic.common.httpCommon.Enums.RequestContentTypeEnum;
import com.prod.basic.common.httpCommon.Enums.RequestMethodEnum;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class RegistrationClientService {

    private RegistrationProcess m_registrationProcess;

    public RegistrationClientService() {
        m_registrationProcess = new RegistrationProcess();
    }

    public Registration.RegistrationResponsePb doRegistration(Registration.RegistrationRequestPb requiest) {
        try {
            return new RegistrationProcess().execute(requiest).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    private class RegistrationProcess extends AsyncTask<Registration.RegistrationRequestPb, Void, Registration.RegistrationResponsePb> {

        @Override
        protected Registration.RegistrationResponsePb doInBackground(Registration.RegistrationRequestPb... registrationRequestPbs) {
            ServerUrlManeger urlManeger = new ServerUrlManeger();
            ProtoSerilizerAndDeserilizer serilizer = new ProtoSerilizerAndDeserilizer();
            HttpCaller caller = new HttpCaller(RequestMethodEnum.POST, RequestContentTypeEnum.CONTENT_TYPE_JSON, urlManeger.getServerUrl(UrlPathProvider.UrlPathEnum.REGISTRATION_WORKER), serilizer.getJsonObject(registrationRequestPbs[0]));
            try {
                return ProtoJsonUtil.fromJson(caller.execute().toString(), Registration.RegistrationResponsePb.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
