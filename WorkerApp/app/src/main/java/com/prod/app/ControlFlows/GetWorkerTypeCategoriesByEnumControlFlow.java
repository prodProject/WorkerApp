package com.prod.app.ControlFlows;

import com.prod.app.Helper.WorkerDataHelper;
import com.prod.app.Widget.WorkerDataTypeWidget.WorkerDataTypeView;
import com.prod.app.clientServices.WorkerTypeClientService;
import com.prod.app.protobuff.Workertype;
import com.prod.basic.common.exception.VoidException;
import com.prod.basic.common.httpCommon.Interfaces.IFuture;

public class GetWorkerTypeCategoriesByEnumControlFlow {

    private WorkerDataHelper m_workerDataHelper;
    private WorkerTypeClientService m_workerTypeClientService;

    public GetWorkerTypeCategoriesByEnumControlFlow() {
        m_workerDataHelper = new WorkerDataHelper();
        m_workerTypeClientService = new WorkerTypeClientService();
    }

    public IFuture<Void, VoidException> getWorkerTypeCategories(WorkerDataTypeView view, Workertype.WorkerTypeEnum workerTypeEnum) {
        GetWorkerTypeCategoriesByEnumCF cf = new GetWorkerTypeCategoriesByEnumCF(view, workerTypeEnum, m_workerDataHelper, m_workerTypeClientService);
        cf.addLogObjects(cf);
        cf.startAsyncCall();
        return cf.getFutureResult();
    }
}
