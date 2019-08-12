package com.airbnb.p027n2.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.n2.R;
import com.airbnb.p027n2.DLSComponentType;
import com.airbnb.p027n2.components.DLSComponent;
import com.airbnb.p027n2.components.DLSComponents;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import icepick.Icepick;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.airbnb.n2.internal.ComponentVisitor */
public class ComponentVisitor {
    private final List<Component> componentBuffer = new ArrayList();
    private TraversalHandler handler;
    private ImmutableSet<String> ignoredNamespaces = ImmutableSet.m1298of();
    private OnComponentDisplayListener listener;
    private ImmutableSet<String> whitelistedContainers = ImmutableSet.m1298of();

    /* renamed from: com.airbnb.n2.internal.ComponentVisitor$OnComponentDisplayListener */
    public interface OnComponentDisplayListener {
        void onComponentDisplay(List<Component> list);
    }

    /* renamed from: com.airbnb.n2.internal.ComponentVisitor$TraversalHandler */
    private class TraversalHandler extends Handler {
        public TraversalHandler(Looper looper) {
            super(looper);
        }

        /* access modifiers changed from: private */
        public void startTraversalDelayed(View view, long delayMillis) {
            cancel();
            sendMessageDelayed(obtainMessage(0, view), delayMillis);
        }

        /* access modifiers changed from: private */
        public void traverseView(View view) {
            sendMessage(obtainMessage(0, view));
        }

        private void endTraversal() {
            sendMessage(obtainMessage(1));
        }

        private boolean isTraverseViewQueued() {
            return hasMessages(0);
        }

        /* access modifiers changed from: private */
        public void cancel() {
            removeMessages(0);
            removeMessages(1);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    ComponentVisitor.this.traverse((View) msg.obj);
                    if (!isTraverseViewQueued()) {
                        endTraversal();
                        return;
                    }
                    return;
                case 1:
                    ComponentVisitor.this.flush();
                    return;
                default:
                    return;
            }
        }
    }

    ComponentVisitor(Context context) {
        this.handler = new TraversalHandler(context.getMainLooper());
    }

    /* access modifiers changed from: 0000 */
    public void setWhitelistedContainers(ImmutableSet<String> canonicalNames) {
        this.whitelistedContainers = canonicalNames;
    }

    /* access modifiers changed from: 0000 */
    public void setIgnoredNamespaces(ImmutableSet<String> namespaces) {
        this.ignoredNamespaces = namespaces;
    }

    /* access modifiers changed from: 0000 */
    public void setOnComponentDisplayListener(OnComponentDisplayListener listener2) {
        this.listener = listener2;
    }

    /* access modifiers changed from: 0000 */
    public void startTraversalDebounced(View view, long debounceMillis) {
        this.handler.startTraversalDelayed(view, debounceMillis);
    }

    /* access modifiers changed from: 0000 */
    public void destroy() {
        this.handler.cancel();
        flush();
        this.handler = null;
        this.listener = null;
    }

    /* access modifiers changed from: private */
    public void traverse(View view) {
        boolean isDLSComponent;
        boolean shouldIgnore;
        String simpleName;
        DLSComponentType type;
        boolean isComponent = true;
        if (view.getVisibility() == 0) {
            Class<? extends View> viewClass = view.getClass();
            DLSComponent<?> component = DLSComponents.fromClass(viewClass);
            if (component != null) {
                isDLSComponent = true;
            } else {
                isDLSComponent = false;
            }
            if (isDLSComponent || !shouldIgnore(viewClass.getCanonicalName())) {
                shouldIgnore = false;
            } else {
                shouldIgnore = true;
            }
            if (shouldIgnore || (!isDLSComponent && !isExternalComponent(viewClass.getCanonicalName()))) {
                isComponent = false;
            }
            if (isComponent && !isCounted(view)) {
                if (isDLSComponent) {
                    simpleName = component.name();
                    type = component.type();
                } else {
                    simpleName = viewClass.getSimpleName();
                    type = null;
                }
                this.componentBuffer.add(Component.create(simpleName, type));
                markCounted(view);
            }
            if (!shouldIgnore && !isComponent && (view instanceof ViewGroup)) {
                ViewGroup group = (ViewGroup) view;
                int N = group.getChildCount();
                for (int i = 0; i < N; i++) {
                    this.handler.traverseView(group.getChildAt(i));
                }
            }
        }
    }

    private boolean shouldIgnore(String canonicalName) {
        UnmodifiableIterator it = this.ignoredNamespaces.iterator();
        while (it.hasNext()) {
            if (canonicalName.startsWith((String) it.next())) {
                return true;
            }
        }
        return false;
    }

    private boolean isExternalComponent(String canonicalName) {
        return !canonicalName.startsWith(Icepick.ANDROID_PREFIX) && !canonicalName.startsWith("com.airbnb.n2.") && !this.whitelistedContainers.contains(canonicalName);
    }

    private static void markCounted(View view) {
        view.setTag(R.id.n2_counted, Boolean.TRUE);
    }

    private static boolean isCounted(View view) {
        return view.getTag(R.id.n2_counted) != null;
    }

    /* access modifiers changed from: private */
    public void flush() {
        if (this.componentBuffer.size() > 0) {
            this.listener.onComponentDisplay(new ArrayList(this.componentBuffer));
            this.componentBuffer.clear();
        }
    }
}
