package com.prod.app.BasicCache;

import com.prod.app.Interfaces.IDoneCallback;
import com.prod.app.clientServices.RegistrationClientService;
import com.prod.app.protobuff.Registration;
import com.prod.basic.common.cache.ACache;
import com.prod.basic.common.cache.CacheWithRefresh;

public class LoginCache extends ACache<CacheWithRefresh, String, Registration.RegistrationResponsePb> {

    private final RegistrationClientService m_service;

    public LoginCache(CacheWithRefresh cache) {
        super("Registration", cache);
        m_service = new RegistrationClientService();
    }


    @Override
    protected Registration.RegistrationResponsePb load(final String key) {
        final Registration.RegistrationResponsePb[] future = {null};
        new IDoneCallback() {
            @Override
            public void done() {
                try {
                    future[0] = m_service.get();
                } catch (Throwable t) {
                    invalidate(key);
                }
            }
        };
        return future[0];
    }
}
