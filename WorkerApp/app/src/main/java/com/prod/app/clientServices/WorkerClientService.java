package com.prod.app.clientServices;

import android.os.AsyncTask;

import com.prod.app.CommonCode.HttpCaller;
import com.prod.app.CommonCode.ProtoJsonUtil;
import com.prod.app.CommonCode.ProtoSerilizerAndDeserilizer;
import com.prod.app.Interfaces.IClientServices;
import com.prod.app.ServerConfig.ServerUrlManeger;
import com.prod.app.ServerConfig.UrlPathProvider;
import com.prod.app.protobuff.Worker;
import com.prod.app.protobuff.Workersearch;
import com.prod.basic.common.httpCommon.Enums.RequestContentTypeEnum;
import com.prod.basic.common.httpCommon.Enums.RequestMethodEnum;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class WorkerClientService implements IClientServices<Worker.WorkerPb, Workersearch.WorkerSearchRequestPb, Workersearch.WorkerSearchResponsePb> {

    private WorkerUpdateService m_workerUpdateService;

    public WorkerClientService() {
        m_workerUpdateService = new WorkerUpdateService();
    }

    @Override
    public Worker.WorkerPb get(String id) {
        return null;
    }

    @Override
    public Worker.WorkerPb create(Worker.WorkerPb request) {
        return null;
    }

    @Override
    public Worker.WorkerPb update(Worker.WorkerPb request) {
        try {
            return m_workerUpdateService.execute(request).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Workersearch.WorkerSearchResponsePb search(Workersearch.WorkerSearchRequestPb request) {
        return null;
    }

    private class WorkerUpdateService extends AsyncTask<Worker.WorkerPb, Void, Worker.WorkerPb> {

        @Override
        protected Worker.WorkerPb doInBackground(Worker.WorkerPb... workerPbs) {
            ServerUrlManeger urlManeger = new ServerUrlManeger();
            ProtoSerilizerAndDeserilizer serilizer = new ProtoSerilizerAndDeserilizer();

            HttpCaller caller = new HttpCaller(RequestMethodEnum.PUT, RequestContentTypeEnum.CONTENT_TYPE_JSON, urlManeger.getServerUrl(UrlPathProvider.UrlPathEnum.WORKER), serilizer.getJsonObject(workerPbs[0]));
            try {
                Worker.WorkerPb.Builder resBuilder1 = Worker.WorkerPb.newBuilder(ProtoJsonUtil.fromJson(caller.execute().toString(), Worker.WorkerPb.class));
                return resBuilder1.build();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
