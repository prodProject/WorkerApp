package com.prod.app.CommonCode;

import com.prod.app.protobuff.Email;

public class Strings {

    public static Email.EmailIdPb getEmailIdPbFromStringId(String emailId){
        String[] email = emailId.split("@");
        Email.EmailIdPb.Builder builder = Email.EmailIdPb.newBuilder();
        builder.setLocalPart(email[0]);
        builder.setDomain(email[1]);
        return builder.build();
    }
    public static boolean isEmail(String text){
        if(text.contains("@")){
            return true;
        }
        return false;
    }
}
