package com.prod.app.DatabaseEnitityHelper;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.prod.app.Interfaces.IDatabaseEntity;
import com.prod.app.LocalDatabase.DaoSession;
import com.prod.app.LocalDatabase.DatabaseInitHandler;
import com.prod.app.LocalDatabase.LoginEntityDao;

public class LoginEntityDaoHelper implements IDatabaseEntity<LoginEntityDao> {

    private DatabaseInitHandler m_databaseInitHandler;
    private DaoSession m_daoSession;
    private Context m_context;
    private RequestQueue m_RequestQueue;
    private LoginEntityDao m_loginEntityDao;

    public LoginEntityDaoHelper(Context context) {
        m_context = context;
        init();
    }

    private void init() {
        m_RequestQueue = Volley.newRequestQueue(m_context);
        m_databaseInitHandler = new DatabaseInitHandler();
        m_databaseInitHandler.initDataBase(m_context);
        m_daoSession = m_databaseInitHandler.getDaoSession();
        m_loginEntityDao = m_daoSession.getLoginEntityDao();
    }

    @Override
    public LoginEntityDao getDeoEntity() {
        return m_loginEntityDao;
    }

    @Override
    public DaoSession getDeoSession() {
        return m_daoSession;
    }
}
