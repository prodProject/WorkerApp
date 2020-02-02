package com.prod.app.Helper;

import com.prod.app.Comapretors.WorkerTypeEnumComparetor;
import com.prod.app.Formatter.WorkerTypeEnumFormatter;
import com.prod.app.SessionsManger.WorkerSession;
import com.prod.app.protobuff.Entity;
import com.prod.app.protobuff.Names;
import com.prod.app.protobuff.Worker;
import com.prod.app.protobuff.Workertype;

import java.util.ArrayList;
import java.util.List;

public class WorkerDataHelper {

    private WorkerTypeEnumFormatter m_workerTypeEnumFormatter;
    private WorkerSession m_workerSession;

    public WorkerDataHelper() {
        m_workerTypeEnumFormatter = new WorkerTypeEnumFormatter();
        m_workerSession = new WorkerSession();
    }

    public List<String> getFormattedEnumList(List<Workertype.WorkerTypeEnum> workerTyepEnumList) {
        List<String> formattedNameList = new ArrayList<String>();
        formattedNameList.add("Select Type");
        for (Workertype.WorkerTypeEnum workerType : workerTyepEnumList) {
            if (!(workerType == Workertype.WorkerTypeEnum.UNKNOWN_WORKER_TYPE || workerType == Workertype.WorkerTypeEnum.UNRECOGNIZED)) {
                formattedNameList.add(m_workerTypeEnumFormatter.format(workerType));
            }
        }
        formattedNameList.sort(new WorkerTypeEnumComparetor());
        formattedNameList.add(0, "Select Type");
        return formattedNameList;
    }

    public Workertype.WorkerTypeSearchRequestPb getWorkerSearchRequestPb(Workertype.WorkerTypeEnum workerTypeEnum) {
        Workertype.WorkerTypeSearchRequestPb.Builder builder = Workertype.WorkerTypeSearchRequestPb.newBuilder();
        builder.getDbInfoBuilder().setLifeTime(Entity.StatusEnum.ACTIVE);
        builder.setWorkerType(workerTypeEnum);
        return builder.build();
    }

    public Worker.WorkerPb setWorkerTypeConfig(Workertype.WorkerTypeEnum workerType) {
        Worker.WorkerPb workerPb = m_workerSession.getSession();
        Worker.WorkerPb.Builder builder = workerPb.toBuilder();
        builder.getWorkerTypeConfigBuilder().setWorkerType(workerType);
        return builder.build();
    }

    public List<Names.NamesPb> getNamePbToList(List<String> tages) {
        List<Names.NamesPb> list = new ArrayList<Names.NamesPb>();
        for (String data : tages) {
            Names.NamesPb.Builder builder = Names.NamesPb.newBuilder();
            builder.setCanonicalName(data);
            list.add(builder.build());
        }
        return list;
    }
}
