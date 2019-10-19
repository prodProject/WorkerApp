package com.prod.app.Interfaces;

import android.content.Context;

import com.google.protobuf.GeneratedMessageV3;

public interface IClientServices<Req extends GeneratedMessageV3, Resp extends GeneratedMessageV3> {
    public Resp getCall(Req request, Class<Resp> clazz);
}
