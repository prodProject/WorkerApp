package com.prod.app.Widget.WorkerDataTypeWidget;

import android.content.Context;
import android.widget.ListView;

import com.prod.app.Adapters.CustomCheckboxAdapter;
import com.prod.app.CommonCode.CommonHelper;
import com.prod.app.CommonCode.Strings;
import com.prod.app.ControlFlows.GetWorkerTypeCategoriesByEnumControlFlow;
import com.prod.app.Formatter.WorkerTypeEnumFormatter;
import com.prod.app.Helper.WorkerDataHelper;
import com.prod.app.Interfaces.IContext;
import com.prod.app.Model.CheckboxModel;
import com.prod.app.clientServices.WorkerClientService;
import com.prod.app.protobuff.Names;
import com.prod.app.protobuff.Workertype;

import java.util.ArrayList;
import java.util.List;

public class WorkerDataTypeView implements IContext {

    private Context m_context;
    private ArrayList<CheckboxModel> m_dataModels;
    private WorkerTypeEnumFormatter m_workerTypeEnumFormatter;
    private WorkerDataHelper m_workerDataHelper;
    private WorkerClientService m_workerClientService;
    private CustomCheckboxAdapter m_adapter;
    private ListView m_listView;
    private GetWorkerTypeCategoriesByEnumControlFlow m_getWorkerTypeCategoriesByEnumControlFlow;

    public WorkerDataTypeView() {
        m_dataModels = new ArrayList();
        m_workerTypeEnumFormatter = new WorkerTypeEnumFormatter();
        m_workerDataHelper = new WorkerDataHelper();
        m_getWorkerTypeCategoriesByEnumControlFlow = new GetWorkerTypeCategoriesByEnumControlFlow();
    }


    public List<String> getWorkerTypeEnumList() {
        return m_workerDataHelper.getFormattedEnumList(CommonHelper.convertArrayToList(Workertype.WorkerTypeEnum.values()));
    }


    public void setWorkerTypeResponse(Workertype.WorkerTypeSearchResponsePb responsePb) {
        m_dataModels.clear();
        if (responsePb.getSummary().getTotalHits() > 0) {
            for (Names.NamesPb data : responsePb.getResults(0).getCategoriesList()) {
                m_dataModels.add(new CheckboxModel(Strings.toTitileCaseFormatter(data.getCanonicalName()), data.getCanonicalName(), false));
            }
            m_adapter = new CustomCheckboxAdapter(m_dataModels, getActivityContext());
            m_listView.setAdapter(m_adapter);
        }
    }

    public void getWorkerTypeData(int position) {
        m_getWorkerTypeCategoriesByEnumControlFlow.getWorkerTypeCategories(this, m_workerTypeEnumFormatter.format(position));
    }

    public CheckboxModel getModelAtPosition(int position) {
        return m_dataModels.get(position);
    }

    public CustomCheckboxAdapter getCustomCheckboxAdapter() {
        return m_adapter;
    }

    public void setListView(ListView listview) {
        m_listView = listview;
    }


    @Override
    public Context getActivityContext() {
        return m_context;
    }

    @Override
    public void setActivityContext(Context context) {
        m_context = context;

    }
}
