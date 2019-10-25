package com.prod.app.Interfaces;

import com.prod.app.LocalDatabase.DaoSession;

import org.greenrobot.greendao.AbstractDao;

public interface IDatabaseEntity<Dao extends AbstractDao> {

    public Dao getDeoEntity();
    public DaoSession getDeoSession();

}
