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
import com.prod.app.protobuff.Workertype;
import com.prod.basic.common.httpCommon.Enums.RequestContentTypeEnum;
import com.prod.basic.common.httpCommon.Enums.RequestMethodEnum;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class WorkerTypeClientService implements IClientServices<Workertype.WorkerTypePb, Workertype.WorkerTypeSearchRequestPb, Workertype.WorkerTypeSearchResponsePb> {

    private WorkerTypeSearchService m_search;

    public WorkerTypeClientService() {
        m_search = new WorkerTypeSearchService();
    }

    @Override
    public Workertype.WorkerTypePb get(String id) {
        return null;
    }

    @Override
    public Workertype.WorkerTypePb create(Workertype.WorkerTypePb request) {
        return null;
    }

    @Override
    public Workertype.WorkerTypePb update(Workertype.WorkerTypePb request) {
        return null;
    }

    @Override
    public Workertype.WorkerTypeSearchResponsePb search(Workertype.WorkerTypeSearchRequestPb request) {
        try {
            return new WorkerTypeSearchService().execute(request).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class WorkerTypeSearchService extends AsyncTask<Workertype.WorkerTypeSearchRequestPb, Void, Workertype.WorkerTypeSearchResponsePb> {


        @Override
        protected Workertype.WorkerTypeSearchResponsePb doInBackground(Workertype.WorkerTypeSearchRequestPb... workerTypeSearchRequestPbs) {
            ServerUrlManeger urlManeger = new ServerUrlManeger();
            ProtoSerilizerAndDeserilizer serilizer = new ProtoSerilizerAndDeserilizer();
            HttpCaller caller = new HttpCaller(RequestMethodEnum.GET, RequestContentTypeEnum.CONTENT_TYPE_JSON, GetUrlMaker.getUrlWIthQuery(urlManeger.getServerUrl(UrlPathProvider.UrlPathEnum.WORKER_TYPE), URlEncoder.encodeJson(serilizer.getJsonObject(workerTypeSearchRequestPbs[0]).toString())), null);
            try {
                return ProtoJsonUtil.fromJson(caller.execute().toString(), Workertype.WorkerTypeSearchResponsePb.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
