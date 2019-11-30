package com.prod.app.Interfaces;

import android.content.Context;

import com.google.protobuf.GeneratedMessageV3;

public interface IClientServices<Pb extends GeneratedMessageV3, Req extends GeneratedMessageV3, Resp extends GeneratedMessageV3> {
    public Pb get(String request);

    public Pb create(Pb id);

    public Pb update(Pb request);

    public Resp search(Req request);
}
