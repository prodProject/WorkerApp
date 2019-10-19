package com.prod.app.ServerConfig;

public class UrlPathProvider {

   public enum UrlPathEnum{
        UNKNOWN_URL_PATH,
       REGISTRATION_WORKER,
    }

    public static String getPath(UrlPathEnum data){
        switch (data){

            case UNKNOWN_URL_PATH:
                return "";
            case REGISTRATION_WORKER:
                return "registrationWorkerMain";
            default:
                throw new IllegalStateException("Unexpected value: " + data);
        }
    }
}
