package com.apollographql.apollo.internal.interceptor;

import com.apollographql.apollo.api.C3107Operation;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.interceptor.ApolloInterceptor;
import com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack;
import com.apollographql.apollo.interceptor.ApolloInterceptorChain;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;

public final class RealApolloInterceptorChain implements ApolloInterceptorChain {
    private final int interceptorIndex;
    private final List<ApolloInterceptor> interceptors;
    private final C3107Operation operation;

    public RealApolloInterceptorChain(C3107Operation operation2, List<ApolloInterceptor> interceptors2) {
        this(operation2, interceptors2, 0);
    }

    private RealApolloInterceptorChain(C3107Operation operation2, List<ApolloInterceptor> interceptors2, int interceptorIndex2) {
        if (interceptorIndex2 > interceptors2.size()) {
            throw new IllegalArgumentException();
        }
        this.operation = (C3107Operation) Utils.checkNotNull(operation2, "operation == null");
        this.interceptors = new ArrayList((Collection) Utils.checkNotNull(interceptors2, "interceptors == null"));
        this.interceptorIndex = interceptorIndex2;
    }

    public void proceedAsync(ExecutorService dispatcher, CallBack callBack) {
        if (this.interceptorIndex >= this.interceptors.size()) {
            throw new IllegalStateException();
        }
        ((ApolloInterceptor) this.interceptors.get(this.interceptorIndex)).interceptAsync(this.operation, new RealApolloInterceptorChain(this.operation, this.interceptors, this.interceptorIndex + 1), dispatcher, callBack);
    }
}
