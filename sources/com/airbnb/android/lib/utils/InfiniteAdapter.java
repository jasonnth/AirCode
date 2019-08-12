package com.airbnb.android.lib.utils;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequest;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestExecutor;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.airrequest.UniqueTagRequestExecutor;
import com.airbnb.android.core.requests.AirRequestFactory;
import com.airbnb.android.core.requests.AirRequestFactory.Provider;
import com.airbnb.android.utils.AdapterWrapper;
import com.airbnb.android.utils.ListUtils;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

public class InfiniteAdapter<TRequest extends BaseRequest<TResponse>, TDataModel, TResponse extends Provider<TDataModel>> extends AdapterWrapper<TDataModel> {
    private static final int ITEMS_BEFORE_END_TO_BEGIN_LOAD = 2;
    private final AirRequestFactory<TRequest, TResponse> mFactory;
    /* access modifiers changed from: private */
    public final AtomicBoolean mKeepOnAppending = new AtomicBoolean(true);
    private int mPendingResource = -1;
    /* access modifiers changed from: private */
    public View mPendingView = null;
    /* access modifiers changed from: private */
    public TRequest mRequest;
    /* access modifiers changed from: private */
    public AdapterRequestListener<TResponse> mRequestListener;
    private final RequestExecutor requestExecutor;

    public interface AdapterRequestListener<TResponse> {
        void onErrorResponse(NetworkException networkException);

        void onResponse(TResponse tresponse);
    }

    public InfiniteAdapter(ArrayAdapter<TDataModel> wrapped, int pendingResource, AirRequestFactory<TRequest, TResponse> factory, RequestManager requestManager) {
        super(wrapped);
        this.mPendingResource = pendingResource;
        this.mFactory = factory;
        this.requestExecutor = new UniqueTagRequestExecutor(requestManager);
    }

    public int getCount() {
        int count = super.getCount();
        if ((count != 0 || this.mKeepOnAppending.get()) && this.mKeepOnAppending.get()) {
            return count + 1;
        }
        return count;
    }

    public int getItemViewType(int position) {
        if (position == getWrappedAdapter().getCount()) {
            return -1;
        }
        return super.getItemViewType(position);
    }

    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    public boolean isEnabled(int position) {
        return position < getWrappedAdapter().getCount() && getWrappedAdapter().isEnabled(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (position >= super.getCount() - 2 && this.mKeepOnAppending.get()) {
            if (this.mRequest == null) {
                this.mRequest = this.mFactory.getNextOffset(super.getCount(), generateAutoListener());
                this.requestExecutor.execute(this.mRequest);
            }
            if (position == super.getCount()) {
                if (this.mPendingView == null) {
                    this.mPendingView = getPendingView(parent);
                }
                return this.mPendingView;
            }
        }
        return super.getView(position, convertView, parent);
    }

    public NonResubscribableRequestListener<TResponse> generateAutoListener() {
        return new NonResubscribableRequestListener<TResponse>() {
            public void onResponse(TResponse response) {
                InfiniteAdapter.this.mPendingView = null;
                Collection<TDataModel> data = response.provide();
                if (ListUtils.isEmpty(data)) {
                    InfiniteAdapter.this.mKeepOnAppending.set(false);
                } else {
                    InfiniteAdapter.this.getWrappedAdapter().addAll(data);
                }
                InfiniteAdapter.this.getWrappedAdapter().notifyDataSetChanged();
                InfiniteAdapter.this.mRequest = null;
                if (InfiniteAdapter.this.mRequestListener != null) {
                    InfiniteAdapter.this.mRequestListener.onResponse(response);
                }
            }

            public void onErrorResponse(AirRequestNetworkException error) {
                InfiniteAdapter.this.onException(error);
                InfiniteAdapter.this.mRequest = null;
                if (InfiniteAdapter.this.mRequestListener != null) {
                    InfiniteAdapter.this.mRequestListener.onErrorResponse(error);
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public boolean onException(Exception e) {
        Log.e(InfiniteAdapter.class.getSimpleName(), "Exception in InfiniteAdapter background", e);
        return false;
    }

    /* access modifiers changed from: protected */
    public View getPendingView(ViewGroup parent) {
        if (parent != null) {
            return ((LayoutInflater) parent.getContext().getSystemService("layout_inflater")).inflate(this.mPendingResource, parent, false);
        }
        throw new RuntimeException("You must either override getPendingView() or supply a pending View resource via the constructor");
    }

    public ArrayAdapter<TDataModel> getWrappedAdapter() {
        return super.getWrappedAdapter();
    }

    public void setRequestListener(AdapterRequestListener<TResponse> listener) {
        this.mRequestListener = listener;
    }
}
