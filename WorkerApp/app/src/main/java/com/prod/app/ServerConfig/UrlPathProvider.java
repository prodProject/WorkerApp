package com.prod.app.ServerConfig;

public class UrlPathProvider {

    public enum UrlPathEnum {
        UNKNOWN_URL_PATH,
        REGISTRATION_WORKER,
        WORKER,
        LOGIN,
        WORKER_TYPE,
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
            case WORKER_TYPE:
                return "workerTypeMain";
            default:
                throw new IllegalStateException("Unexpected value: " + data);
        }
    }
}
