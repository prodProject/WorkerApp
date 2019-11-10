package com.prod.app.clientServices;

import android.os.AsyncTask;

import com.prod.app.CommonCode.HttpCaller;
import com.prod.app.CommonCode.ProtoJsonUtil;
import com.prod.app.CommonCode.ProtoSerilizerAndDeserilizer;
import com.prod.app.ServerConfig.ServerUrlManeger;
import com.prod.app.ServerConfig.UrlPathProvider;
import com.prod.app.protobuff.Login;
import com.prod.basic.common.httpCommon.Enums.RequestContentTypeEnum;
import com.prod.basic.common.httpCommon.Enums.RequestMethodEnum;

import java.io.IOException;

public class LoginClientService extends AsyncTask<Login.LoginRequestPb, Void, Login.LoginResponsePb> {

    private RequestMethodEnum m_method;

    public LoginClientService(RequestMethodEnum method) {
        m_method = method;
    }

    @Override
    protected Login.LoginResponsePb doInBackground(Login.LoginRequestPb... loginRequestPbs) {
        ServerUrlManeger urlManeger = new ServerUrlManeger();
        ProtoSerilizerAndDeserilizer serilizer = new ProtoSerilizerAndDeserilizer();
        HttpCaller caller = new HttpCaller(m_method, RequestContentTypeEnum.CONTENT_TYPE_JSON, urlManeger.getServerUrl(UrlPathProvider.UrlPathEnum.LOGIN), serilizer.getJsonObject(loginRequestPbs[0]));
        try {
            return ProtoJsonUtil.fromJson(caller.execute().toString(), Login.LoginResponsePb.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
