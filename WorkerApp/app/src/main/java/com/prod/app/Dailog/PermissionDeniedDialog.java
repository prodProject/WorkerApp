package com.prod.app.Dailog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prod.app.GeoLocation.DialogClickListener;
import com.prod.app.R;


/**
 * Created by AnkurYadav on 23-09-2017.
 */

public class PermissionDeniedDialog{

    Activity activity;
    DialogClickListener dialogClickListner;
    public PermissionDeniedDialog(Activity context) {
        this.activity=context;
    }

    AlertDialog.Builder alertDialog;
    Dialog dialog;
    public void showDialog(DialogClickListener listner) {

        try {
            dialogClickListner = listner;
            alertDialog=new AlertDialog.Builder(activity);
            LayoutInflater inflater=activity.getLayoutInflater();
            View v=inflater.inflate(R.layout.permission_denied_dialog, null);

            alertDialog.setView(v);
            if (dialog == null) {
                dialog = alertDialog.create();
            }


            LinearLayout positive_button=(LinearLayout)v.findViewById(R.id.positive_button);
            LinearLayout negative_button=(LinearLayout)v.findViewById(R.id.negative_button);

            TextView title=(TextView)v.findViewById(R.id.title);
            TextView message=(TextView)v.findViewById(R.id.message);

            TextView negative_txt=(TextView)v.findViewById(R.id.negative_txt);
            TextView positive_txt=(TextView)v.findViewById(R.id.positive_txt);


            positive_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogClickListner.positiveListener(activity,dialog);
                }
            });

            negative_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogClickListner.negativeListener(activity,dialog);
                }
            });

            dialog.show();

        }catch (Exception e){

        }
    }
}