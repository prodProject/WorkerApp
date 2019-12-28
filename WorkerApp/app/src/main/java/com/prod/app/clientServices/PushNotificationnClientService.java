package com.prod.app.clientServices;

import android.os.AsyncTask;

import com.prod.app.CommonCode.HttpCaller;
import com.prod.app.CommonCode.ProtoJsonUtil;
import com.prod.app.CommonCode.ProtoSerilizerAndDeserilizer;
import com.prod.app.CommonCode.URlEncoder;
import com.prod.app.Interfaces.IClientServices;
import com.prod.app.ServerConfig.GetUrlMaker;
import com.prod.app.ServerConfig.ServerUrlManeger;
import com.prod.app.ServerConfig.UrlPathProvider;
import com.prod.app.protobuff.Pushnotification;
import com.prod.basic.common.httpCommon.Enums.RequestContentTypeEnum;
import com.prod.basic.common.httpCommon.Enums.RequestMethodEnum;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class PushNotificationnClientService implements IClientServices<Pushnotification.PushNotificationPb, Pushnotification.PushNotificationSearchRequestPb, Pushnotification.PushNotificationSearchResponsePb> {

    private PushNotificationGetService m_pushNotificationGetService;
    private PushNotificationCreateService m_pushNotificationCreateService;
    private PushNotificationUpdateService m_pushNotificationUpdateService;
    private PushNotificationSearchService m_pushNotificationSearchService;


    public PushNotificationnClientService() {
        m_pushNotificationGetService = new PushNotificationGetService();
        m_pushNotificationCreateService = new PushNotificationCreateService();
        m_pushNotificationUpdateService = new PushNotificationUpdateService();
        m_pushNotificationSearchService = new PushNotificationSearchService();
    }

    @Override
    public Pushnotification.PushNotificationPb get(String id) {
        try {
            return m_pushNotificationGetService.execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Pushnotification.PushNotificationPb create(Pushnotification.PushNotificationPb request) {
        try {
            return m_pushNotificationCreateService.execute(request).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Pushnotification.PushNotificationPb update(Pushnotification.PushNotificationPb request) {
        try {
            return m_pushNotificationUpdateService.execute(request).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Pushnotification.PushNotificationSearchResponsePb search(Pushnotification.PushNotificationSearchRequestPb request) {
        try {
            return m_pushNotificationSearchService.execute(request).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class PushNotificationGetService extends AsyncTask<String, Void, Pushnotification.PushNotificationPb> {

        @Override
        protected Pushnotification.PushNotificationPb doInBackground(String... id) {
            ServerUrlManeger urlManeger = new ServerUrlManeger();
            HttpCaller caller = new HttpCaller(RequestMethodEnum.GET, RequestContentTypeEnum.CONTENT_TYPE_JSON, GetUrlMaker.getUrlWIthQuery(urlManeger.getServerUrl(UrlPathProvider.UrlPathEnum.PUSH_NOTIFICATION), id[0]), null);
            try {
                Pushnotification.PushNotificationPb.Builder resBuilder1 = Pushnotification.PushNotificationPb.newBuilder(ProtoJsonUtil.fromJson(caller.execute().toString(), Pushnotification.PushNotificationPb.class));
                return resBuilder1.build();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class PushNotificationCreateService extends AsyncTask<Pushnotification.PushNotificationPb, Void, Pushnotification.PushNotificationPb> {

        @Override
        protected Pushnotification.PushNotificationPb doInBackground(Pushnotification.PushNotificationPb... pushNotificationPbs) {
            ServerUrlManeger urlManeger = new ServerUrlManeger();
            ProtoSerilizerAndDeserilizer serilizer = new ProtoSerilizerAndDeserilizer();

            HttpCaller caller = new HttpCaller(RequestMethodEnum.POST, RequestContentTypeEnum.CONTENT_TYPE_JSON, urlManeger.getServerUrl(UrlPathProvider.UrlPathEnum.PUSH_NOTIFICATION), serilizer.getJsonObject(pushNotificationPbs[0]));
            try {
                Pushnotification.PushNotificationPb.Builder resBuilder1 = Pushnotification.PushNotificationPb.newBuilder(ProtoJsonUtil.fromJson(caller.execute().toString(), Pushnotification.PushNotificationPb.class));
                return resBuilder1.build();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class PushNotificationUpdateService extends AsyncTask<Pushnotification.PushNotificationPb, Void, Pushnotification.PushNotificationPb> {

        @Override
        protected Pushnotification.PushNotificationPb doInBackground(Pushnotification.PushNotificationPb... pushNotificationPbs) {
            ServerUrlManeger urlManeger = new ServerUrlManeger();
            ProtoSerilizerAndDeserilizer serilizer = new ProtoSerilizerAndDeserilizer();

            HttpCaller caller = new HttpCaller(RequestMethodEnum.PUT, RequestContentTypeEnum.CONTENT_TYPE_JSON, urlManeger.getServerUrl(UrlPathProvider.UrlPathEnum.PUSH_NOTIFICATION), serilizer.getJsonObject(pushNotificationPbs[0]));
            try {
                Pushnotification.PushNotificationPb.Builder resBuilder1 = Pushnotification.PushNotificationPb.newBuilder(ProtoJsonUtil.fromJson(caller.execute().toString(), Pushnotification.PushNotificationPb.class));
                return resBuilder1.build();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private class PushNotificationSearchService extends AsyncTask<Pushnotification.PushNotificationSearchRequestPb, Void, Pushnotification.PushNotificationSearchResponsePb> {


        @Override
        protected Pushnotification.PushNotificationSearchResponsePb doInBackground(Pushnotification.PushNotificationSearchRequestPb... pushNotificationSearchRequestPbs) {
            ServerUrlManeger urlManeger = new ServerUrlManeger();
            ProtoSerilizerAndDeserilizer serilizer = new ProtoSerilizerAndDeserilizer();
            HttpCaller caller = new HttpCaller(RequestMethodEnum.GET, RequestContentTypeEnum.CONTENT_TYPE_JSON, GetUrlMaker.getUrlWIthQuery(urlManeger.getServerUrl(UrlPathProvider.UrlPathEnum.WORKER_TYPE), URlEncoder.encodeJson(serilizer.getJsonObject(pushNotificationSearchRequestPbs[0]).toString())), null);
            try {
                return ProtoJsonUtil.fromJson(caller.execute().toString(), Pushnotification.PushNotificationSearchResponsePb.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
