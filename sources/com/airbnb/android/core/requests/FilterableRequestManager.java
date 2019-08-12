package com.airbnb.android.core.requests;

import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.airrequest.BaseRequest;
import com.airbnb.rxgroups.GroupLifecycleManager;
import com.airbnb.rxgroups.RequestSubscription;
import java.util.HashSet;
import java.util.Set;

public class FilterableRequestManager extends ObservableRequestManager {
    private final Set<Filter> filters = new HashSet();

    public interface Filter {
        RequestSubscription executeWithTag(BaseRequest<?> baseRequest, String str);

        boolean shouldFilter(BaseRequest<?> baseRequest, String str);
    }

    public static FilterableRequestManager from(ObservableRequestManager requestManager) {
        return new FilterableRequestManager(requestManager.requestInitializer(), requestManager.lifecycleManager());
    }

    protected FilterableRequestManager(AirRequestInitializer initializer, GroupLifecycleManager lifecycleManager) {
        super(initializer, lifecycleManager);
    }

    public void addInterceptor(Filter filter) {
        this.filters.add(filter);
    }

    public void removeInterceptor(Filter filter) {
        this.filters.remove(filter);
    }

    public <T> RequestSubscription executeWithTag(BaseRequest<T> request, String tag) {
        for (Filter filter : this.filters) {
            if (filter.shouldFilter(request, tag)) {
                return filter.executeWithTag(request, tag);
            }
        }
        return super.executeWithTag(request, tag);
    }
}
