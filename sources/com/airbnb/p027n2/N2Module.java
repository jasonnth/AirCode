package com.airbnb.p027n2;

import android.content.Context;
import com.airbnb.p027n2.C0977N2.Callbacks;
import com.airbnb.p027n2.N2Component.Builder;
import com.danikula.videocache.HttpProxyCacheServer;

/* renamed from: com.airbnb.n2.N2Module */
public class N2Module {
    public N2Context provideN2Context(Builder n2ComponentBuilder) {
        return N2Context.create(n2ComponentBuilder.build());
    }

    public C0977N2 provideN2(Callbacks callbacks) {
        return new C0977N2(callbacks);
    }

    public HttpProxyCacheServer provideVideoCache(Context context) {
        return new HttpProxyCacheServer(context);
    }
}
