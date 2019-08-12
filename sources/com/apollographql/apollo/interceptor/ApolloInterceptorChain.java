package com.apollographql.apollo.interceptor;

import com.apollographql.apollo.interceptor.ApolloInterceptor.CallBack;
import java.util.concurrent.ExecutorService;

public interface ApolloInterceptorChain {
    void proceedAsync(ExecutorService executorService, CallBack callBack);
}
