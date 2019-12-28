package com.prod.app.Helper;

import com.prod.app.protobuff.Login;
import com.prod.app.protobuff.Persontypeenum;
import com.prod.app.protobuff.Pushnotification;
import com.prod.basic.common.code.Strings;

public class DeviceHelper {

    public Login.LoginRequestPb getLoginRequestPbFromLoginPb(Login.LoginPb login) {
        Login.LoginRequestPb.Builder builder = Login.LoginRequestPb.newBuilder();
        Login.LoginPb.Builder bu = builder.getLoginBuilder();
        if (Strings.notEmpty(login.getContactDetails().getEmail().getLocalPart()) && Strings.notEmpty(login.getContactDetails().getEmail().getDomain())) {
            bu.getContactDetailsBuilder().getEmailBuilder().setLocalPart(login.getContactDetails().getEmail().getLocalPart());
            bu.getContactDetailsBuilder().getEmailBuilder().setDomain(login.getContactDetails().getEmail().getDomain());
        } else {
            bu.getContactDetailsBuilder().getPrimaryMobileBuilder().setCode(login.getContactDetails().getPrimaryMobile().getCode());
            bu.getContactDetailsBuilder().getPrimaryMobileBuilder().setNumber(login.getContactDetails().getPrimaryMobile().getNumber());
        }
        bu.setPassword(login.getPassword());
        bu.getPersonTypeBuilder().setPersonType(login.getPersonType().getPersonType());
        return builder.build();

    }

    public Pushnotification.PushNotificationPb getPushNotificationCreateBuilder(String token) {
        Pushnotification.PushNotificationPb.Builder builder = Pushnotification.PushNotificationPb.newBuilder();
        builder.getWorkerRefBuilder().getDbInfoBuilder().setId("ku");
        builder.getTypeBuilder().setPersonType(Persontypeenum.PersonTypeEnum.WORKER);
        builder.setTokenId(token);
        return builder.build();
    }
}
