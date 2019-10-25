package com.prod.app.Session;

import android.content.Context;

public class SessionManager {

    private Context m_context;

    public SessionManager(Context context){
        m_context=context;
        FastSave.init(m_context);
    }
}
