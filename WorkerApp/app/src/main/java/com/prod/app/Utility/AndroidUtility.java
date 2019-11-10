package com.prod.app.Utility;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;

public class AndroidUtility {

    public static String getTextFromEditText(EditText editText) {
        return editText.getText().toString().toLowerCase().trim();
    }

    public static void startActivity(Context context, Class clazz) {
        context.startActivity(new Intent(context, clazz));
    }
}
