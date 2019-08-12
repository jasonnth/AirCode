package com.airbnb.airrequest;

import p032rx.functions.Action1;

/* renamed from: com.airbnb.airrequest.RL */
public class C0699RL<T> {
    /* access modifiers changed from: private */
    public Action1<Boolean> completeAction;
    /* access modifiers changed from: private */
    public Action1<AirRequestNetworkException> errorAction;
    /* access modifiers changed from: private */
    public Action1<T> responseAction;

    /* renamed from: com.airbnb.airrequest.RL$Listener */
    private class Listener extends RequestListener<T> {
        private Listener() {
        }

        public void onResponse(T data) {
            if (C0699RL.this.responseAction != null) {
                C0699RL.this.responseAction.call(data);
            }
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            if (C0699RL.this.errorAction != null) {
                C0699RL.this.errorAction.call(e);
            }
        }

        public void onRequestCompleted(boolean successful) {
            if (C0699RL.this.completeAction != null) {
                C0699RL.this.completeAction.call(Boolean.valueOf(successful));
            }
        }
    }

    /* renamed from: com.airbnb.airrequest.RL$NonResubscribableListener */
    private class NonResubscribableListener extends NonResubscribableRequestListener<T> {
        private NonResubscribableListener() {
        }

        public void onResponse(T data) {
            if (C0699RL.this.responseAction != null) {
                C0699RL.this.responseAction.call(data);
            }
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            if (C0699RL.this.errorAction != null) {
                C0699RL.this.errorAction.call(e);
            }
        }

        public void onRequestCompleted(boolean successful) {
            if (C0699RL.this.completeAction != null) {
                C0699RL.this.completeAction.call(Boolean.valueOf(successful));
            }
        }
    }

    public C0699RL<T> onResponse(Action1<T> onResponseAction) {
        this.responseAction = onResponseAction;
        return this;
    }

    public C0699RL<T> onError(Action1<AirRequestNetworkException> onErrorAction) {
        this.errorAction = onErrorAction;
        return this;
    }

    public C0699RL<T> onComplete(Action1<Boolean> onCompleteAction) {
        this.completeAction = onCompleteAction;
        return this;
    }

    public RequestListener<T> build() {
        return new Listener();
    }

    public NonResubscribableRequestListener<T> buildWithoutResubscription() {
        return new NonResubscribableListener();
    }
}
