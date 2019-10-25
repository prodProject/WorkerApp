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

public class RegistrationClientService extends AsyncTask<Registration.RegistrationRequestPb, Void, Registration.RegistrationResponsePb> {

    private RequestMethodEnum m_method;

    public RegistrationClientService(RequestMethodEnum method) {
        m_method = method;
    }

    @Override
    protected Registration.RegistrationResponsePb doInBackground(Registration.RegistrationRequestPb... registrationRequestPbs) {
        ServerUrlManeger urlManeger = new ServerUrlManeger();
        ProtoSerilizerAndDeserilizer serilizer = new ProtoSerilizerAndDeserilizer();
        HttpCaller caller = new HttpCaller(m_method, RequestContentTypeEnum.CONTENT_TYPE_JSON, urlManeger.getServerUrl(UrlPathProvider.UrlPathEnum.REGISTRATION_WORKER), serilizer.getJsonObject(registrationRequestPbs[0]));
        try {
            Registration.RegistrationResponsePb.Builder resBuilder1 = Registration.RegistrationResponsePb.newBuilder(ProtoJsonUtil.fromJson(caller.execute().toString(), Registration.RegistrationResponsePb.class));
            return resBuilder1.build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
