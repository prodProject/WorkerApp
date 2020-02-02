package com.prod.app.Widget.WorkerDataTypeWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.prod.app.Interfaces.IView;
import com.prod.app.Model.CheckboxModel;
import com.prod.app.R;

public class WorkerDataTypeWidget extends LinearLayout implements IView<WorkerDataTypeView> {


    private Context m_context;
    private Spinner m_spinner;
    private Button m_finish;
    private ListView m_listView;
    private ArrayAdapter<String> m_dataAdapter;

    private WorkerDataTypeView m_view;

    public WorkerDataTypeWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        m_view = new WorkerDataTypeView();
        getView().setActivityContext(context);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.worker_data_type_layout, this);
        inflateLayout();
        if (!isInEditMode()) {
            initWidget();
        }
    }

    private void inflateLayout() {
        m_finish = (Button) findViewById(R.id.finish);
        m_listView = (ListView) findViewById(R.id.chkboxList);
        m_spinner = (Spinner) findViewById(R.id.workerType);
        getView().setListView(m_listView);
        m_dataAdapter = new ArrayAdapter<String>(getView().getActivityContext(),
                android.R.layout.simple_spinner_item, getView().getWorkerTypeEnumList());
        m_dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_spinner.setAdapter(m_dataAdapter);
    }

    private void initWidget() {


        m_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, final int position, long id) {
                CheckboxModel dataModel = getView().getModelAtPosition(position);
                dataModel.checked = !dataModel.checked;
                getView().getCustomCheckboxAdapter().notifyDataSetChanged();
                if (getView().getCustomCheckboxAdapter().getTages().contains("^^others")) {

                }
            }
        });
        m_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getView().getWorkerTypeData(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public WorkerDataTypeView getView() {
        return m_view;
    }
}
