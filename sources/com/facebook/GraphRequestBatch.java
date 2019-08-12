package com.facebook;

import android.os.Handler;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GraphRequestBatch extends AbstractList<GraphRequest> {
    private static AtomicInteger idGenerator = new AtomicInteger();
    private String batchApplicationId;
    private Handler callbackHandler;
    private List<Callback> callbacks;

    /* renamed from: id */
    private final String f3082id;
    private List<GraphRequest> requests;
    private int timeoutInMilliseconds;

    public interface Callback {
        void onBatchCompleted(GraphRequestBatch graphRequestBatch);
    }

    public interface OnProgressCallback extends Callback {
        void onBatchProgress(GraphRequestBatch graphRequestBatch, long j, long j2);
    }

    public GraphRequestBatch() {
        this.requests = new ArrayList();
        this.timeoutInMilliseconds = 0;
        this.f3082id = Integer.valueOf(idGenerator.incrementAndGet()).toString();
        this.callbacks = new ArrayList();
        this.requests = new ArrayList();
    }

    public GraphRequestBatch(Collection<GraphRequest> requests2) {
        this.requests = new ArrayList();
        this.timeoutInMilliseconds = 0;
        this.f3082id = Integer.valueOf(idGenerator.incrementAndGet()).toString();
        this.callbacks = new ArrayList();
        this.requests = new ArrayList(requests2);
    }

    public GraphRequestBatch(GraphRequest... requests2) {
        this.requests = new ArrayList();
        this.timeoutInMilliseconds = 0;
        this.f3082id = Integer.valueOf(idGenerator.incrementAndGet()).toString();
        this.callbacks = new ArrayList();
        this.requests = Arrays.asList(requests2);
    }

    public GraphRequestBatch(GraphRequestBatch requests2) {
        this.requests = new ArrayList();
        this.timeoutInMilliseconds = 0;
        this.f3082id = Integer.valueOf(idGenerator.incrementAndGet()).toString();
        this.callbacks = new ArrayList();
        this.requests = new ArrayList(requests2);
        this.callbackHandler = requests2.callbackHandler;
        this.timeoutInMilliseconds = requests2.timeoutInMilliseconds;
        this.callbacks = new ArrayList(requests2.callbacks);
    }

    public int getTimeout() {
        return this.timeoutInMilliseconds;
    }

    public void setTimeout(int timeoutInMilliseconds2) {
        if (timeoutInMilliseconds2 < 0) {
            throw new IllegalArgumentException("Argument timeoutInMilliseconds must be >= 0.");
        }
        this.timeoutInMilliseconds = timeoutInMilliseconds2;
    }

    public void addCallback(Callback callback) {
        if (!this.callbacks.contains(callback)) {
            this.callbacks.add(callback);
        }
    }

    public void removeCallback(Callback callback) {
        this.callbacks.remove(callback);
    }

    public final boolean add(GraphRequest request) {
        return this.requests.add(request);
    }

    public final void add(int location, GraphRequest request) {
        this.requests.add(location, request);
    }

    public final void clear() {
        this.requests.clear();
    }

    public final GraphRequest get(int i) {
        return (GraphRequest) this.requests.get(i);
    }

    public final GraphRequest remove(int location) {
        return (GraphRequest) this.requests.remove(location);
    }

    public final GraphRequest set(int location, GraphRequest request) {
        return (GraphRequest) this.requests.set(location, request);
    }

    public final int size() {
        return this.requests.size();
    }

    /* access modifiers changed from: 0000 */
    public final String getId() {
        return this.f3082id;
    }

    /* access modifiers changed from: 0000 */
    public final Handler getCallbackHandler() {
        return this.callbackHandler;
    }

    /* access modifiers changed from: 0000 */
    public final void setCallbackHandler(Handler callbackHandler2) {
        this.callbackHandler = callbackHandler2;
    }

    /* access modifiers changed from: 0000 */
    public final List<GraphRequest> getRequests() {
        return this.requests;
    }

    /* access modifiers changed from: 0000 */
    public final List<Callback> getCallbacks() {
        return this.callbacks;
    }

    public final String getBatchApplicationId() {
        return this.batchApplicationId;
    }

    public final void setBatchApplicationId(String batchApplicationId2) {
        this.batchApplicationId = batchApplicationId2;
    }

    public final List<GraphResponse> executeAndWait() {
        return executeAndWaitImpl();
    }

    public final GraphRequestAsyncTask executeAsync() {
        return executeAsyncImpl();
    }

    /* access modifiers changed from: 0000 */
    public List<GraphResponse> executeAndWaitImpl() {
        return GraphRequest.executeBatchAndWait(this);
    }

    /* access modifiers changed from: 0000 */
    public GraphRequestAsyncTask executeAsyncImpl() {
        return GraphRequest.executeBatchAsync(this);
    }
}
