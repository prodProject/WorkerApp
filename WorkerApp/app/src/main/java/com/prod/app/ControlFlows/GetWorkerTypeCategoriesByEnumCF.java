package com.prod.app.ControlFlows;

import com.prod.app.Helper.WorkerDataHelper;
import com.prod.app.Widget.WorkerDataTypeWidget.WorkerDataTypeView;
import com.prod.app.clientServices.WorkerTypeClientService;
import com.prod.app.protobuff.Workertype;
import com.prod.basic.common.async.AControlFlow;
import com.prod.basic.common.exception.VoidException;

public class GetWorkerTypeCategoriesByEnumCF extends AControlFlow<GetWorkerTypeCategoriesByEnumCF.State, Void, VoidException> {

    private WorkerDataTypeView m_workerDataTypeView;
    private WorkerDataHelper m_workerDataHelper;
    private WorkerTypeClientService m_workerTypeClientService;
    private Workertype.WorkerTypeEnum m_workerType;
    private Workertype.WorkerTypeSearchRequestPb m_requestPb;
    private Workertype.WorkerTypeSearchResponsePb m_responsePb;

    public GetWorkerTypeCategoriesByEnumCF(WorkerDataTypeView workerDataTypeView, Workertype.WorkerTypeEnum workerType, WorkerDataHelper workerDataHelper, WorkerTypeClientService workerTypeClientService) {
        super(State.GET_WORKER_TYPE_SEARCH_REQ_PB, State.DONE);
        m_workerDataHelper = workerDataHelper;
        m_workerTypeClientService = workerTypeClientService;
        m_workerType = workerType;
        m_workerDataTypeView = workerDataTypeView;
        addStateHandler(State.GET_WORKER_TYPE_SEARCH_REQ_PB, new GetWorkerTypeSearchReqPbHandler());
        addStateHandler(State.PERFORM_SEARCH, new PerformSearchHandler());
        addStateHandler(State.FORM_RESPONSE, new FormResponseHandler());
    }

    enum State {
        GET_WORKER_TYPE_SEARCH_REQ_PB,
        PERFORM_SEARCH,
        FORM_RESPONSE,
        DONE,
    }

    private class GetWorkerTypeSearchReqPbHandler implements StateHandler<GetWorkerTypeCategoriesByEnumCF.State> {

        @Override
        public void registerCalls() {

        }

        @Override
        public State handleState() {
            if (m_workerType != Workertype.WorkerTypeEnum.UNKNOWN_WORKER_TYPE) {
                m_requestPb = m_workerDataHelper.getWorkerSearchRequestPb(m_workerType);
                return State.PERFORM_SEARCH;
            } else {
                return State.DONE;
            }

        }
    }

    private class PerformSearchHandler implements StateHandler<GetWorkerTypeCategoriesByEnumCF.State> {

        @Override
        public void registerCalls() {
            m_responsePb = m_workerTypeClientService.search(m_requestPb);
            registerFutures();
        }

        @Override
        public State handleState() {
            if (m_responsePb != null) {
                return State.FORM_RESPONSE;
            } else {
                return State.DONE;
            }
        }
    }

    private class FormResponseHandler implements StateHandler<GetWorkerTypeCategoriesByEnumCF.State> {

        @Override
        public void registerCalls() {

        }

        @Override
        public State handleState() {
            m_workerDataTypeView.setWorkerTypeResponse(m_responsePb);
            return State.DONE;
        }
    }
}
