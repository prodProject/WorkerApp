package com.prod.app.Interfaces;

import android.content.Context;

import com.google.protobuf.GeneratedMessageV3;

public interface IClientServices<Pb extends GeneratedMessageV3, Req extends GeneratedMessageV3, Resp extends GeneratedMessageV3> {
    public Pb get(String id);

    public Pb create(Pb request);

    public Pb update(Pb request);

    public Resp search(Req request);
}
