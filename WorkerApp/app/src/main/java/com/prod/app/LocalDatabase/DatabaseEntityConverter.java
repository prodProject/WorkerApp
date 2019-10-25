package com.prod.app.LocalDatabase;


import com.prod.app.LocalDatabase.model.LoginData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed Fahmy on 3/2/16.
 */
public class DatabaseEntityConverter {
    public List<LoginData> convertBathroomEntity(List<LoginEntity> bathroomEntities) {
        List<LoginData> convertedLogins = new ArrayList<>();
        for (LoginEntity login : bathroomEntities) {
            Long id = login.getId();
            String eid = login.getEid();
            String raw_data = login.getData();


            LoginData loginDataObj = new LoginData();
            loginDataObj.setmId((id));
            loginDataObj.setEid(eid);
            loginDataObj.setData(raw_data);
            convertedLogins.add(loginDataObj);
        }
        return convertedLogins;
    }
}