package com.prod.app.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.prod.app.Model.CheckboxModel;
import com.prod.app.R;
import com.prod.basic.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class CustomCheckboxAdapter extends ArrayAdapter {

    private ArrayList<CheckboxModel> dataSet;
    Context mContext;
    private List<String> tags;

    private static class ViewHolder {
        TextView txtName;
        CheckBox checkBox;
    }

    public CustomCheckboxAdapter(ArrayList data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = new ArrayList<CheckboxModel>();
        this.dataSet.clear();
        this.dataSet = data;
        super.notifyDataSetChanged();
        this.mContext = context;
        tags = new ArrayList<String>();

    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public CheckboxModel getItem(int position) {
        return dataSet.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);

            result = convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        final CheckboxModel item = getItem(position);


        viewHolder.txtName.setText(item.name);
        viewHolder.checkBox.setChecked(item.checked);
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tags.add(item.value);
                } else {
                    tags.remove(item.value);
                }
                for (String data : tags) {
                    Log.e("tags", data);

                }
                Log.e("tags", "" + tags.size());
            }
        });
        return result;

    }

    public ArrayList<CheckboxModel> getModel() {
        return dataSet;
    }

    public List<String> getTages() {
        return tags;
    }

    public void clearTages() {
        tags.clear();
    }
}