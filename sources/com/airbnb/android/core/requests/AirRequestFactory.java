package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequest;
import com.airbnb.airrequest.BaseRequestListener;
import java.util.Collection;

public abstract class AirRequestFactory<T extends BaseRequest<Q>, Q> {

    public interface Consumer<S> {
        void addAll(Collection<? extends S> collection);

        void notifyDataSetChanged();
    }

    public interface Provider<S> {
        Collection<S> provide();
    }

    public abstract T getNextOffset(int i, BaseRequestListener<Q> baseRequestListener);
}
