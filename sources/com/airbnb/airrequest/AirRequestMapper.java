package com.airbnb.airrequest;

import p032rx.Observable;
import p032rx.functions.Func1;

interface AirRequestMapper extends Func1<AirRequest, Observable<? extends AirResponse<?>>> {
}
