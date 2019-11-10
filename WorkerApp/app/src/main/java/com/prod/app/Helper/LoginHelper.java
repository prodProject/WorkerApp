package com.prod.app.Helper;

import com.prod.app.CommonCode.Strings;
import com.prod.app.protobuff.Login;
import com.prod.app.protobuff.Mobile;
import com.prod.app.protobuff.Persontypeenum;

public class LoginHelper {
    public Login.LoginRequestPb getLoginRequestPb(String emailOrphone, String password) {
        Login.LoginRequestPb.Builder builder = Login.LoginRequestPb.newBuilder();
        Login.LoginPb.Builder bu = builder.getLoginBuilder();
        if (Strings.isEmail(emailOrphone)) {
            String[] email = emailOrphone.split("@");
            bu.getContactDetailsBuilder().getEmailBuilder().setLocalPart(email[0]);
            bu.getContactDetailsBuilder().getEmailBuilder().setDomain(email[1]);
        } else {
            bu.getContactDetailsBuilder().getPrimaryMobileBuilder().setCode(Mobile.CountryCodeEnum.ISD_91);
            bu.getContactDetailsBuilder().getPrimaryMobileBuilder().setNumber(emailOrphone);
        }
        bu.setPassword(password);
        bu.getPersonTypeBuilder().setPersonType(Persontypeenum.PersonTypeEnum.WORKER);
        return builder.build();
    }
}
