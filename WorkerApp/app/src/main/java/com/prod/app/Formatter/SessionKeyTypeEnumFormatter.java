package com.prod.app.Formatter;

import com.prod.app.Enums.SessionKeyTypeEnum;
import com.prod.basic.common.httpCommon.Interfaces.IFormatter;

public class SessionKeyTypeEnumFormatter implements IFormatter<String, SessionKeyTypeEnum> {

    @Override
    public String format(SessionKeyTypeEnum sessionKeyTypeEnum) {
        switch (sessionKeyTypeEnum) {

            case WORKER:
                return "worker";
            case UNKNOWN:
                return "";
            default:
                return "";
        }
    }
}
