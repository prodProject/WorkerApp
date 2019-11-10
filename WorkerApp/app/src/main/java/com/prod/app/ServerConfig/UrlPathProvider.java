package com.prod.app.ServerConfig;

public class UrlPathProvider {

    public enum UrlPathEnum {
        UNKNOWN_URL_PATH,
        REGISTRATION_WORKER,
        WORKER,
        LOGIN,
    }

    public static String getPath(UrlPathEnum data) {
        switch (data) {

            case UNKNOWN_URL_PATH:
                return "";
            case REGISTRATION_WORKER:
                return "registrationWorkerMain";
            case WORKER:
                return "workerMain";
            case LOGIN:
                return "loginMain";
            default:
                throw new IllegalStateException("Unexpected value: " + data);
        }
    }
}
