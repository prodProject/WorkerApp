package com.prod.app.clientServices;

import android.os.AsyncTask;

import com.prod.app.CommonCode.HttpCaller;
import com.prod.app.CommonCode.ProtoJsonUtil;
import com.prod.app.CommonCode.ProtoSerilizerAndDeserilizer;
import com.prod.app.Interfaces.IClientServices;
import com.prod.app.ServerConfig.ServerUrlManeger;
import com.prod.app.ServerConfig.UrlPathProvider;
import com.prod.app.protobuff.Login;
import com.prod.basic.common.httpCommon.Enums.RequestContentTypeEnum;
import com.prod.basic.common.httpCommon.Enums.RequestMethodEnum;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class LoginClientService implements IClientServices<Login.LoginPb, Login.LoginSearchRequestPb, Login.LoginSearchRespsonsePb> {

    private LoginProcess m_loginProcess;

    public LoginClientService() {
      m_loginProcess = new LoginProcess();
    }



    @Override
    public Login.LoginPb get(String id) {
        return null;
    }

    @Override
    public Login.LoginPb create(Login.LoginPb request) {
        return null;
    }

    @Override
    public Login.LoginPb update(Login.LoginPb request) {
        return null;
    }

    @Override
    public Login.LoginSearchRespsonsePb search(Login.LoginSearchRequestPb request) {
        return null;
    }

    public Login.LoginResponsePb doLogin(Login.LoginRequestPb requestPb){
        try {
            return new LoginProcess().execute(requestPb).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class LoginProcess extends AsyncTask<Login.LoginRequestPb, Void, Login.LoginResponsePb> {
        @Override
        protected Login.LoginResponsePb doInBackground(Login.LoginRequestPb... loginRequestPbs) {
            ServerUrlManeger urlManeger = new ServerUrlManeger();
            ProtoSerilizerAndDeserilizer serilizer = new ProtoSerilizerAndDeserilizer();
            HttpCaller caller = new HttpCaller(RequestMethodEnum.POST, RequestContentTypeEnum.CONTENT_TYPE_JSON, urlManeger.getServerUrl(UrlPathProvider.UrlPathEnum.LOGIN), serilizer.getJsonObject(loginRequestPbs[0]));
            try {
                return ProtoJsonUtil.fromJson(caller.execute().toString(), Login.LoginResponsePb.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
