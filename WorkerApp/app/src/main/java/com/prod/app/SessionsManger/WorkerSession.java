package com.prod.app.SessionsManger;


import com.prod.app.Enums.SessionKeyTypeEnum;
import com.prod.app.Formatter.SessionKeyTypeEnumFormatter;
import com.prod.app.Interfaces.ISession;
import com.prod.app.Session.FastSave;
import com.prod.app.clientServices.WorkerClientService;
import com.prod.app.protobuff.Worker;

public class WorkerSession implements ISession<Worker.WorkerPb> {

    private SessionKeyTypeEnumFormatter m_formatter;
    private WorkerClientService m_workerClientService;

    public WorkerSession() {
        m_formatter = new SessionKeyTypeEnumFormatter();
        m_workerClientService = new WorkerClientService();
    }

    @Override
    public Worker.WorkerPb getSession() {
        return FastSave.getInstance().getObject(m_formatter.format(SessionKeyTypeEnum.WORKER), Worker.WorkerPb.class);
    }

    @Override
    public void setSession(Worker.WorkerPb value) {
        FastSave.getInstance().saveObject(m_formatter.format(SessionKeyTypeEnum.WORKER), value);
    }

    @Override
    public void clear() {
        FastSave.getInstance().deleteValue(m_formatter.format(SessionKeyTypeEnum.WORKER));
    }

    @Override
    public boolean sessionExixts() {
        return FastSave.getInstance().isKeyExists(m_formatter.format(SessionKeyTypeEnum.WORKER));
    }

    @Override
    public Worker.WorkerPb updateSession(Worker.WorkerPb value) {
        if(value!=null){
            Worker.WorkerPb resp = m_workerClientService.update(value);
            setSession(resp);
            return resp ;
        }
        return value;
    }
}
