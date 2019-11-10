package com.prod.app.clientServices;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.prod.app.CommonCode.HttpCaller;
import com.prod.app.CommonCode.ProtoJsonUtil;
import com.prod.app.CommonCode.ProtoSerilizerAndDeserilizer;
import com.prod.app.ServerConfig.GetUrlMaker;
import com.prod.app.ServerConfig.ServerUrlManeger;
import com.prod.app.ServerConfig.UrlPathProvider;
import com.prod.app.protobuff.Worker;
import com.prod.basic.common.httpCommon.Enums.RequestContentTypeEnum;
import com.prod.basic.common.httpCommon.Enums.RequestMethodEnum;

import java.io.IOException;

public class WorkerClientService extends AsyncTask<Worker.WorkerPb, Void, Worker.WorkerPb> {

    private RequestMethodEnum m_method;
    private String m_value;

    public WorkerClientService(RequestMethodEnum method, String value) {
        m_method = method;
        m_value = value;
    }

    @SuppressLint("WrongThread")
    @Override
    protected Worker.WorkerPb doInBackground(Worker.WorkerPb... workerPbs) {
        ServerUrlManeger urlManeger = new ServerUrlManeger();
        ProtoSerilizerAndDeserilizer serilizer = new ProtoSerilizerAndDeserilizer();
        HttpCaller caller = null;
        if (m_method == RequestMethodEnum.GET) {
            caller = new HttpCaller(m_method, RequestContentTypeEnum.CONTENT_TYPE_JSON, GetUrlMaker.getUrlWIthQuery(urlManeger.getServerUrl(UrlPathProvider.UrlPathEnum.WORKER), m_value), null);
        } else {
            caller = new HttpCaller(m_method, RequestContentTypeEnum.CONTENT_TYPE_JSON, urlManeger.getServerUrl(UrlPathProvider.UrlPathEnum.WORKER), serilizer.getJsonObject(workerPbs[0]));
        }
        try {
            Worker.WorkerPb.Builder resBuilder1 = Worker.WorkerPb.newBuilder(ProtoJsonUtil.fromJson(caller.execute().toString(), Worker.WorkerPb.class));
            return resBuilder1.build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
