package com.prod.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.prod.app.Adapters.CustomCheckboxAdapter;
import com.prod.app.Async.AsyncJob;
import com.prod.app.CommonCode.CommonHelper;
import com.prod.app.CommonCode.Strings;
import com.prod.app.Formatter.WorkerTypeEnumFormatter;
import com.prod.app.Helper.WorkerDataHelper;
import com.prod.app.Model.CheckboxModel;
import com.prod.app.R;
import com.prod.app.clientServices.WorkerClientService;
import com.prod.app.clientServices.WorkerTypeClientService;
import com.prod.app.protobuff.Names;
import com.prod.app.protobuff.Worker;
import com.prod.app.protobuff.Workertype;

import java.util.ArrayList;
import java.util.List;

public class WorkerDataActivity extends AppCompatActivity {

    private Spinner m_spinner;
    private Button m_finish;
    private WorkerTypeEnumFormatter m_workerTypeEnumFormatter;
    private WorkerDataHelper m_workerDataHelper;
    private WorkerTypeClientService m_workerTypeClientService;
    private WorkerClientService m_workerClientService;
    ArrayList<CheckboxModel> dataModels;
    ListView listView;
    private CustomCheckboxAdapter adapter;

    private Worker.WorkerPb.Builder m_worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_data);
        m_finish = (Button) findViewById(R.id.finish);
        listView = (ListView) findViewById(R.id.chkboxList);
        dataModels = new ArrayList();
        m_workerClientService = new WorkerClientService();
        m_workerTypeEnumFormatter = new WorkerTypeEnumFormatter();
        m_workerTypeClientService = new WorkerTypeClientService();
        m_workerDataHelper = new WorkerDataHelper();
        m_spinner = (Spinner) findViewById(R.id.workerType);
        List<String> workerTyepEnumList = m_workerDataHelper.getFormattedEnumList(CommonHelper.convertArrayToList(Workertype.WorkerTypeEnum.values()));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, workerTyepEnumList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_spinner.setAdapter(dataAdapter);
        m_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("selected", "" + position);
                if (m_workerTypeEnumFormatter.format(position) != Workertype.WorkerTypeEnum.UNKNOWN_WORKER_TYPE) {
                    final Workertype.WorkerTypeEnum workerType = m_workerTypeEnumFormatter.format(position);
                    m_worker = Worker.WorkerPb.newBuilder(m_workerDataHelper.setWorkerTypeConfig(workerType));
                    new AsyncJob.AsyncJobBuilder<Workertype.WorkerTypeSearchResponsePb>()
                            .doInBackground(new AsyncJob.AsyncAction<Workertype.WorkerTypeSearchResponsePb>() {
                                @Override
                                public Workertype.WorkerTypeSearchResponsePb doAsync() {
                                    return m_workerTypeClientService.search(m_workerDataHelper.getWorkerSearchRequestPb(workerType));
                                }

                            })
                            .doWhenFinished(new AsyncJob.AsyncResultAction<Workertype.WorkerTypeSearchResponsePb>() {
                                @Override
                                public void onResult(Workertype.WorkerTypeSearchResponsePb result) {
                                    if (result.getSummary().getTotalHits() > 0) {
                                        for (Names.NamesPb data : result.getResults(0).getCategoriesList()) {
                                            dataModels.add(new CheckboxModel(Strings.toTitileCaseFormatter(data.getCanonicalName()), data.getCanonicalName(), false));
                                        }

                                        listView.setAdapter(adapter);
                                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView parent, View view, final int position, long id) {
                                                CheckboxModel dataModel = dataModels.get(position);
                                                dataModel.checked = !dataModel.checked;
                                                adapter.notifyDataSetChanged();
                                            }
                                        });

                                        m_finish.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                if (m_worker.getWorkerTypeConfig().getWorkerType() != Workertype.WorkerTypeEnum.UNKNOWN_WORKER_TYPE) {
                                                    m_worker.getWorkerTypeConfigBuilder().addAllCategories(m_workerDataHelper.getNamePbToList(adapter.getTages()));
                                                    Log.e("resp", "" + m_workerClientService.update(m_worker.build()));
                                                }
                                            }
                                        });
                                    }
                                }
                            }).create().start();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
