package com.airbnb.android.lib.adapters;

import android.support.p002v7.widget.RecyclerView.Adapter;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequest;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestExecutor;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.airrequest.UniqueTagRequestExecutor;
import com.airbnb.android.core.requests.AirRequestFactory;
import com.airbnb.android.core.requests.AirRequestFactory.Consumer;
import com.airbnb.android.core.requests.AirRequestFactory.Provider;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ListUtils;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration.State;
import java.util.Collection;

public class RecyclerInfiniteAdapter<TRequest extends BaseRequest<TResponse>, TDataModel, TResponse extends Provider<TDataModel>> extends RecyclerAdapterWrapper<ViewHolder> implements StickyRecyclerHeadersAdapter<ViewHolder> {
    public static final int ALLOW_PARTIAL_RESPONSES = 1;
    private static final int VIEW_TYPE_LOADING = C0880R.C0882id.recycler_view_type_loading;
    private final int loadingLayout;
    private final AirRequestFactory<TRequest, TResponse> mFactory;
    private boolean mFinishedLoading;
    private boolean mLoadMoreData;
    /* access modifiers changed from: private */
    public TRequest mRequest;
    /* access modifiers changed from: private */
    public final int minItemsPerFetch;
    private final RequestExecutor requestExecutor;

    private class LoadingItemViewHolder extends ViewHolder {
        LoadingItemViewHolder(ViewGroup parent, int layout) {
            super(LayoutInflater.from(parent.getContext()).inflate(layout, parent, false));
        }
    }

    public RecyclerInfiniteAdapter(Adapter adapter, int minItemsPerFetch2, AirRequestFactory<TRequest, TResponse> factory, RequestManager requestManager, int loadingLayout2) {
        super(adapter);
        this.requestExecutor = new UniqueTagRequestExecutor(requestManager);
        this.mLoadMoreData = true;
        this.mFactory = factory;
        this.minItemsPerFetch = minItemsPerFetch2;
        this.loadingLayout = loadingLayout2;
    }

    public RecyclerInfiniteAdapter(Adapter adapter, int minItemsPerFetch2, AirRequestFactory<TRequest, TResponse> factory, RequestManager requestManager) {
        this(adapter, minItemsPerFetch2, factory, requestManager, C0880R.layout.list_item_loading);
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_LOADING) {
            return new LoadingItemViewHolder(parent, this.loadingLayout);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_LOADING) {
            loadMoreData();
        } else {
            super.onBindViewHolder(holder, position);
        }
    }

    public long getHeaderId(int position) {
        if (getWrappedAdapter() instanceof StickyRecyclerHeadersAdapter) {
            return ((StickyRecyclerHeadersAdapter) getWrappedAdapter()).getHeaderId(position);
        }
        return -1;
    }

    public ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int position) {
        if (getWrappedAdapter() instanceof StickyRecyclerHeadersAdapter) {
            return ((StickyRecyclerHeadersAdapter) getWrappedAdapter()).onCreateHeaderViewHolder(parent, position);
        }
        return null;
    }

    public void onBindHeaderViewHolder(ViewHolder holder, int position) {
        if (getWrappedAdapter() instanceof StickyRecyclerHeadersAdapter) {
            ((StickyRecyclerHeadersAdapter) getWrappedAdapter()).onBindHeaderViewHolder(holder, position);
        }
    }

    public int getItemCount() {
        return ((!this.mLoadMoreData || super.getItemCount() <= 0) ? 0 : 1) + super.getItemCount();
    }

    public void onStickyHeaderStateChange(ViewHolder viewHolder, State state, int position) {
        if (getWrappedAdapter() instanceof StickyRecyclerHeadersAdapter) {
            ((StickyRecyclerHeadersAdapter) getWrappedAdapter()).onStickyHeaderStateChange(viewHolder, state, position);
        }
    }

    public int getItemViewType(int position) {
        if (position == super.getItemCount()) {
            return VIEW_TYPE_LOADING;
        }
        return super.getItemViewType(position);
    }

    private void loadMoreData() {
        if (this.mRequest == null) {
            this.mRequest = this.mFactory.getNextOffset(super.getItemCount(), new NonResubscribableRequestListener<TResponse>() {
                public void onErrorResponse(AirRequestNetworkException error) {
                    RecyclerInfiniteAdapter.this.mRequest = null;
                    RecyclerInfiniteAdapter.this.stopLoadingMore();
                }

                public void onResponse(TResponse response) {
                    if (!(RecyclerInfiniteAdapter.this.getWrappedAdapter() instanceof Consumer)) {
                        throw new IllegalStateException("Adapter " + RecyclerInfiniteAdapter.this.getWrappedAdapter().getClass().getSimpleName() + " must implement Consumer.");
                    }
                    Consumer<TDataModel> consumingAdapter = (Consumer) RecyclerInfiniteAdapter.this.getWrappedAdapter();
                    Collection<TDataModel> data = response.provide();
                    if (ListUtils.isEmpty(data)) {
                        RecyclerInfiniteAdapter.this.stopLoadingMore();
                    } else {
                        consumingAdapter.addAll(data);
                        if (data.size() < RecyclerInfiniteAdapter.this.minItemsPerFetch) {
                            RecyclerInfiniteAdapter.this.stopLoadingMore();
                        }
                    }
                    RecyclerInfiniteAdapter.this.mRequest = null;
                }
            });
            this.mRequest.execute(this.requestExecutor);
        }
    }

    public void startLoadingMore() {
        if (!this.mLoadMoreData && !this.mFinishedLoading && super.getItemCount() >= this.minItemsPerFetch) {
            this.mLoadMoreData = true;
            getWrappedAdapter().notifyItemInserted(getItemCount() - 1);
        }
    }

    public void stopLoadingMore() {
        if (this.mLoadMoreData) {
            this.mLoadMoreData = false;
            getWrappedAdapter().notifyItemRemoved(getItemCount());
            this.mFinishedLoading = true;
        }
    }

    public void refresh() {
        stopLoadingMore();
        this.mFinishedLoading = false;
    }
}
