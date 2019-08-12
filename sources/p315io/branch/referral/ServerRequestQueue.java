package p315io.branch.referral;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import org.json.JSONArray;
import org.json.JSONException;
import p315io.branch.referral.Branch.BranchReferralInitListener;
import p315io.branch.referral.Defines.RequestPath;

/* renamed from: io.branch.referral.ServerRequestQueue */
class ServerRequestQueue {
    private static ServerRequestQueue SharedInstance;
    /* access modifiers changed from: private */
    public Editor editor = this.sharedPref.edit();
    /* access modifiers changed from: private */
    public final List<ServerRequest> queue;
    private SharedPreferences sharedPref;

    public static ServerRequestQueue getInstance(Context c) {
        if (SharedInstance == null) {
            synchronized (ServerRequestQueue.class) {
                if (SharedInstance == null) {
                    SharedInstance = new ServerRequestQueue(c);
                }
            }
        }
        return SharedInstance;
    }

    @SuppressLint({"CommitPrefEdits"})
    private ServerRequestQueue(Context c) {
        this.sharedPref = c.getSharedPreferences("BNC_Server_Request_Queue", 0);
        this.queue = retrieve(c);
    }

    private void persist() {
        new Thread(new Runnable() {
            /* JADX WARNING: Unknown top exception splitter block from list: {B:19:0x005b=Splitter:B:19:0x005b, B:34:0x00ac=Splitter:B:34:0x00ac} */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r10 = this;
                    io.branch.referral.ServerRequestQueue r5 = p315io.branch.referral.ServerRequestQueue.this
                    java.util.List r6 = r5.queue
                    monitor-enter(r6)
                    org.json.JSONArray r3 = new org.json.JSONArray     // Catch:{ all -> 0x002c }
                    r3.<init>()     // Catch:{ all -> 0x002c }
                    io.branch.referral.ServerRequestQueue r5 = p315io.branch.referral.ServerRequestQueue.this     // Catch:{ all -> 0x002c }
                    java.util.List r5 = r5.queue     // Catch:{ all -> 0x002c }
                    java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x002c }
                L_0x0016:
                    boolean r7 = r5.hasNext()     // Catch:{ all -> 0x002c }
                    if (r7 == 0) goto L_0x002f
                    java.lang.Object r0 = r5.next()     // Catch:{ all -> 0x002c }
                    io.branch.referral.ServerRequest r0 = (p315io.branch.referral.ServerRequest) r0     // Catch:{ all -> 0x002c }
                    org.json.JSONObject r2 = r0.toJSON()     // Catch:{ all -> 0x002c }
                    if (r2 == 0) goto L_0x0016
                    r3.put(r2)     // Catch:{ all -> 0x002c }
                    goto L_0x0016
                L_0x002c:
                    r5 = move-exception
                    monitor-exit(r6)     // Catch:{ all -> 0x002c }
                    throw r5
                L_0x002f:
                    r4 = 0
                    io.branch.referral.ServerRequestQueue r5 = p315io.branch.referral.ServerRequestQueue.this     // Catch:{ ConcurrentModificationException -> 0x005d }
                    android.content.SharedPreferences$Editor r5 = r5.editor     // Catch:{ ConcurrentModificationException -> 0x005d }
                    java.lang.String r7 = "BNCServerRequestQueue"
                    java.lang.String r8 = r3.toString()     // Catch:{ ConcurrentModificationException -> 0x005d }
                    android.content.SharedPreferences$Editor r5 = r5.putString(r7, r8)     // Catch:{ ConcurrentModificationException -> 0x005d }
                    r5.commit()     // Catch:{ ConcurrentModificationException -> 0x005d }
                    r4 = 1
                    if (r4 != 0) goto L_0x005b
                    io.branch.referral.ServerRequestQueue r5 = p315io.branch.referral.ServerRequestQueue.this     // Catch:{ ConcurrentModificationException -> 0x00af }
                    android.content.SharedPreferences$Editor r5 = r5.editor     // Catch:{ ConcurrentModificationException -> 0x00af }
                    java.lang.String r7 = "BNCServerRequestQueue"
                    java.lang.String r8 = r3.toString()     // Catch:{ ConcurrentModificationException -> 0x00af }
                    android.content.SharedPreferences$Editor r5 = r5.putString(r7, r8)     // Catch:{ ConcurrentModificationException -> 0x00af }
                    r5.commit()     // Catch:{ ConcurrentModificationException -> 0x00af }
                L_0x005b:
                    monitor-exit(r6)     // Catch:{ all -> 0x002c }
                    return
                L_0x005d:
                    r1 = move-exception
                    java.lang.String r5 = "Persisting Queue: "
                    java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0095 }
                    r7.<init>()     // Catch:{ all -> 0x0095 }
                    java.lang.String r8 = "Failed to persit queue "
                    java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ all -> 0x0095 }
                    java.lang.String r8 = r1.getMessage()     // Catch:{ all -> 0x0095 }
                    java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ all -> 0x0095 }
                    java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0095 }
                    p315io.branch.referral.PrefHelper.Debug(r5, r7)     // Catch:{ all -> 0x0095 }
                    if (r4 != 0) goto L_0x005b
                    io.branch.referral.ServerRequestQueue r5 = p315io.branch.referral.ServerRequestQueue.this     // Catch:{ ConcurrentModificationException -> 0x0093 }
                    android.content.SharedPreferences$Editor r5 = r5.editor     // Catch:{ ConcurrentModificationException -> 0x0093 }
                    java.lang.String r7 = "BNCServerRequestQueue"
                    java.lang.String r8 = r3.toString()     // Catch:{ ConcurrentModificationException -> 0x0093 }
                    android.content.SharedPreferences$Editor r5 = r5.putString(r7, r8)     // Catch:{ ConcurrentModificationException -> 0x0093 }
                    r5.commit()     // Catch:{ ConcurrentModificationException -> 0x0093 }
                    goto L_0x005b
                L_0x0093:
                    r5 = move-exception
                    goto L_0x005b
                L_0x0095:
                    r5 = move-exception
                    if (r4 != 0) goto L_0x00ac
                    io.branch.referral.ServerRequestQueue r7 = p315io.branch.referral.ServerRequestQueue.this     // Catch:{ ConcurrentModificationException -> 0x00ad }
                    android.content.SharedPreferences$Editor r7 = r7.editor     // Catch:{ ConcurrentModificationException -> 0x00ad }
                    java.lang.String r8 = "BNCServerRequestQueue"
                    java.lang.String r9 = r3.toString()     // Catch:{ ConcurrentModificationException -> 0x00ad }
                    android.content.SharedPreferences$Editor r7 = r7.putString(r8, r9)     // Catch:{ ConcurrentModificationException -> 0x00ad }
                    r7.commit()     // Catch:{ ConcurrentModificationException -> 0x00ad }
                L_0x00ac:
                    throw r5     // Catch:{ all -> 0x002c }
                L_0x00ad:
                    r7 = move-exception
                    goto L_0x00ac
                L_0x00af:
                    r5 = move-exception
                    goto L_0x005b
                */
                throw new UnsupportedOperationException("Method not decompiled: p315io.branch.referral.ServerRequestQueue.C48681.run():void");
            }
        }).start();
    }

    private List<ServerRequest> retrieve(Context context) {
        List<ServerRequest> result = Collections.synchronizedList(new LinkedList());
        String jsonStr = this.sharedPref.getString("BNCServerRequestQueue", null);
        if (jsonStr != null) {
            try {
                JSONArray jsonArr = new JSONArray(jsonStr);
                for (int i = 0; i < Math.min(jsonArr.length(), 25); i++) {
                    ServerRequest req = ServerRequest.fromJSON(jsonArr.getJSONObject(i), context);
                    if (req != null && !(req instanceof ServerRequestRegisterClose) && !(req instanceof ServerRequestLogout)) {
                        result.add(req);
                    }
                }
            } catch (JSONException e) {
            }
        }
        return result;
    }

    public int getSize() {
        return this.queue.size();
    }

    public void enqueue(ServerRequest request) {
        if (request != null) {
            this.queue.add(request);
            if (getSize() >= 25) {
                this.queue.remove(1);
            }
            persist();
        }
    }

    public ServerRequest dequeue() {
        ServerRequest req = null;
        try {
            req = (ServerRequest) this.queue.remove(0);
            persist();
            return req;
        } catch (IndexOutOfBoundsException | NoSuchElementException e) {
            return req;
        }
    }

    public ServerRequest peek() {
        ServerRequest req = null;
        try {
            return (ServerRequest) this.queue.get(0);
        } catch (IndexOutOfBoundsException | NoSuchElementException e) {
            return req;
        }
    }

    public ServerRequest peekAt(int index) {
        ServerRequest req = null;
        try {
            return (ServerRequest) this.queue.get(index);
        } catch (IndexOutOfBoundsException | NoSuchElementException e) {
            return req;
        }
    }

    public void insert(ServerRequest request, int index) {
        try {
            if (this.queue.size() < index) {
                index = this.queue.size();
            }
            this.queue.add(index, request);
            persist();
        } catch (IndexOutOfBoundsException e) {
        }
    }

    public boolean remove(ServerRequest request) {
        boolean isRemoved = false;
        try {
            isRemoved = this.queue.remove(request);
            persist();
            return isRemoved;
        } catch (UnsupportedOperationException e) {
            return isRemoved;
        }
    }

    public void clear() {
        try {
            this.queue.clear();
            persist();
        } catch (UnsupportedOperationException e) {
        }
    }

    public boolean containsClose() {
        synchronized (this.queue) {
            for (ServerRequest req : this.queue) {
                if (req != null && req.getRequestPath().equals(RequestPath.RegisterClose.getPath())) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean containsInstallOrOpen() {
        synchronized (this.queue) {
            for (ServerRequest req : this.queue) {
                if (req != null && ((req instanceof ServerRequestRegisterInstall) || (req instanceof ServerRequestRegisterOpen))) {
                    return true;
                }
            }
            return false;
        }
    }

    public void moveInstallOrOpenToFront(ServerRequest request, int networkCount, BranchReferralInitListener callback) {
        synchronized (this.queue) {
            Iterator<ServerRequest> iter = this.queue.iterator();
            while (true) {
                if (!iter.hasNext()) {
                    break;
                }
                ServerRequest req = (ServerRequest) iter.next();
                if (req == null || (!(req instanceof ServerRequestRegisterInstall) && !(req instanceof ServerRequestRegisterOpen))) {
                }
            }
            iter.remove();
        }
        if (networkCount == 0) {
            insert(request, 0);
        } else {
            insert(request, 1);
        }
    }

    public void setInstallOrOpenCallback(BranchReferralInitListener callback) {
        synchronized (this.queue) {
            for (ServerRequest req : this.queue) {
                if (req != null) {
                    if (req instanceof ServerRequestRegisterInstall) {
                        ((ServerRequestRegisterInstall) req).setInitFinishedCallback(callback);
                    } else if (req instanceof ServerRequestRegisterOpen) {
                        ((ServerRequestRegisterOpen) req).setInitFinishedCallback(callback);
                    }
                }
            }
        }
    }

    public void unlockProcessWait(PROCESS_WAIT_LOCK lock) {
        synchronized (this.queue) {
            for (ServerRequest req : this.queue) {
                if (req != null) {
                    req.removeProcessWaitLock(lock);
                }
            }
        }
    }

    public void setStrongMatchWaitLock() {
        synchronized (this.queue) {
            for (ServerRequest req : this.queue) {
                if (req != null && (req instanceof ServerRequestInitSession)) {
                    req.addProcessWaitLock(PROCESS_WAIT_LOCK.STRONG_MATCH_PENDING_WAIT_LOCK);
                }
            }
        }
    }
}
