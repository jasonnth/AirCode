package com.airbnb.p027n2.transitions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.p000v4.app.ActivityOptionsCompat;
import android.support.p000v4.app.SharedElementCallback;
import android.support.p000v4.util.Pair;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.animation.FastOutSlowInInterpolator;
import android.support.p002v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.airbnb.n2.transitions.AutoSharedElementCallback */
public class AutoSharedElementCallback extends SharedElementCallback {
    private static final String TAG = AutoSharedElementCallback.class.getSimpleName();
    /* access modifiers changed from: private */
    public final AppCompatActivity activity;
    private final List<TransitionName> asyncTransitionViews;
    private final Runnable cancelAsyncViewsRunnable;
    private final Runnable checkForAsyncViewsRunnable;
    private AutoSharedElementCallbackDelegate delegate;
    private boolean endCalledSinceOnMap;
    private long enterBackgroundFadeDuration = 350;
    private final int[] pos = new int[2];
    private long returnBackgroundFadeDuration = 200;
    private Transition sharedElementEnterTransition;
    private Transition sharedElementReturnTransition;

    /* renamed from: com.airbnb.n2.transitions.AutoSharedElementCallback$AutoSharedElementCallbackDelegate */
    public static class AutoSharedElementCallbackDelegate {
        public boolean onPreMapSharedElements(List<String> list, Map<String, View> map) {
            return false;
        }

        public void onPostMapSharedElements(List<String> list, Map<String, View> map) {
        }
    }

    public static Bundle getActivityOptionsBundle(Activity activity2, View view) {
        return getActivityOptions(activity2, view).toBundle();
    }

    public static ActivityOptionsCompat getActivityOptions(Activity activity2, View view) {
        return getActivityOptions(activity2, view, true);
    }

    public static ActivityOptionsCompat getActivityOptions(Activity activity2, View view, boolean includeSystemUi) {
        List<Pair<View, String>> transitionViews = new ArrayList<>();
        if (VERSION.SDK_INT >= 23) {
            ViewLibUtils.findTransitionViews(view, transitionViews);
            if (includeSystemUi) {
                addSystemUi(activity2, transitionViews);
            }
        }
        return ActivityOptionsCompat.makeSceneTransitionAnimation(activity2, (Pair[]) transitionViews.toArray(new Pair[transitionViews.size()]));
    }

    @TargetApi(23)
    private static void addSystemUi(Activity activity2, List<Pair<View, String>> transitionViews) {
        View decor = activity2.getWindow().getDecorView();
        View statusBar = decor.findViewById(16908335);
        if (statusBar != null) {
            transitionViews.add(Pair.create(statusBar, ViewCompat.getTransitionName(statusBar)));
        }
        View navBar = decor.findViewById(16908336);
        if (navBar != null) {
            transitionViews.add(Pair.create(navBar, ViewCompat.getTransitionName(navBar)));
        }
    }

    public AutoSharedElementCallback(AppCompatActivity activity2) {
        this.activity = activity2;
        this.checkForAsyncViewsRunnable = null;
        this.cancelAsyncViewsRunnable = null;
        this.asyncTransitionViews = null;
    }

    public AutoSharedElementCallback(AppCompatActivity activity2, TransitionName... asyncTransitionViews2) {
        this.activity = activity2;
        if (VERSION.SDK_INT >= 23) {
            this.asyncTransitionViews = new LinkedList(Arrays.asList(asyncTransitionViews2));
            activity2.supportPostponeEnterTransition();
            startPostponedTransitionsIfReady();
            this.checkForAsyncViewsRunnable = AutoSharedElementCallback$$Lambda$1.lambdaFactory$(this);
            this.cancelAsyncViewsRunnable = AutoSharedElementCallback$$Lambda$2.lambdaFactory$(this);
            startPostponedTransitionsIfReady();
            getDecorView().postDelayed(this.cancelAsyncViewsRunnable, 500);
            return;
        }
        this.checkForAsyncViewsRunnable = null;
        this.cancelAsyncViewsRunnable = null;
        this.asyncTransitionViews = null;
    }

    static /* synthetic */ void lambda$new$0(AutoSharedElementCallback autoSharedElementCallback) {
        if (!autoSharedElementCallback.hasActivityStopped()) {
            autoSharedElementCallback.scheduleStartPostponedTransition();
            Log.w(TAG, "Timed out waiting for async views to load!");
        }
    }

    private boolean hasActivityStopped() {
        return this.activity.getWindow() == null || this.activity.isFinishing();
    }

    /* access modifiers changed from: private */
    public void startPostponedTransitionsIfReady() {
        List<Pair<View, String>> transitionViewPairs = new ArrayList<>();
        ViewLibUtils.findTransitionViews(getDecorView(), transitionViewPairs);
        for (Pair<View, String> p : transitionViewPairs) {
            if (((View) p.first).getParent() == null) {
                return;
            }
        }
        Iterator<TransitionName> it = this.asyncTransitionViews.iterator();
        while (it.hasNext()) {
            TransitionName tn = (TransitionName) it.next();
            Iterator it2 = transitionViewPairs.iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (tn.partialEquals(TransitionName.parse((String) ((Pair) it2.next()).second))) {
                        it.remove();
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        if (this.asyncTransitionViews.isEmpty()) {
            getDecorView().removeCallbacks(this.checkForAsyncViewsRunnable);
            getDecorView().removeCallbacks(this.cancelAsyncViewsRunnable);
            scheduleStartPostponedTransition();
            return;
        }
        getDecorView().post(this.checkForAsyncViewsRunnable);
    }

    public void scheduleStartPostponedTransition() {
        this.activity.getWindow().getDecorView().getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
            public boolean onPreDraw() {
                AutoSharedElementCallback.this.activity.getWindow().getDecorView().getViewTreeObserver().removeOnPreDrawListener(this);
                AutoSharedElementCallback.this.activity.supportStartPostponedEnterTransition();
                return true;
            }
        });
    }

    public AutoSharedElementCallback setDelegate(AutoSharedElementCallbackDelegate delegate2) {
        this.delegate = delegate2;
        return this;
    }

    public Parcelable onCaptureSharedElementSnapshot(View sharedElement, Matrix viewToGlobalMatrix, RectF screenBounds) {
        if (sharedElement instanceof ImageView) {
            ImageView imageView = (ImageView) sharedElement;
            Drawable drawable = ((ImageView) sharedElement).getDrawable();
            if (drawable instanceof TransitionDrawable) {
                TransitionDrawable transitionDrawable = (TransitionDrawable) drawable;
                if (transitionDrawable.getNumberOfLayers() > 0) {
                    drawable = transitionDrawable.getDrawable(transitionDrawable.getNumberOfLayers() - 1);
                }
            }
            if (drawable != null && (drawable instanceof BitmapDrawable)) {
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                Bundle bundle = new Bundle();
                bundle.putParcelable("sharedElement:snapshot:bitmap", bitmap);
                bundle.putString("sharedElement:snapshot:imageScaleType", imageView.getScaleType().toString());
                if (imageView.getScaleType() != ScaleType.MATRIX) {
                    return bundle;
                }
                float[] values = new float[9];
                imageView.getImageMatrix().getValues(values);
                bundle.putFloatArray("sharedElement:snapshot:imageMatrix", values);
                return bundle;
            }
        }
        return null;
    }

    public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
        boolean handled = false;
        getDecorView().removeCallbacks(this.cancelAsyncViewsRunnable);
        this.endCalledSinceOnMap = false;
        if (this.delegate != null && this.delegate.onPreMapSharedElements(names, sharedElements)) {
            handled = true;
        }
        if (!handled) {
            mapBestPartialMatches(names, sharedElements);
        }
        if (this.delegate != null) {
            this.delegate.onPostMapSharedElements(names, sharedElements);
        }
    }

    private void mapBestPartialMatches(List<String> names, Map<String, View> sharedElements) {
        List<Pair<View, String>> allTransitionViews = new ArrayList<>();
        ViewLibUtils.findTransitionViews(getDecorView(), allTransitionViews);
        List<View> partialMatches = new ArrayList<>();
        for (String name : names) {
            View view = (View) sharedElements.get(name);
            if (view == null || !ViewLibUtils.isOnScreen(view)) {
                findAllPartialMatches(TransitionName.parse(name), allTransitionViews, partialMatches);
                if (partialMatches.isEmpty()) {
                    sharedElements.put(name, null);
                } else {
                    sharedElements.put(name, ViewLibUtils.getMostVisibleView(partialMatches));
                }
            }
        }
        Iterator<Entry<String, View>> it = sharedElements.entrySet().iterator();
        while (it.hasNext()) {
            if (((Entry) it.next()).getValue() == null) {
                it.remove();
            }
        }
        if (this.delegate != null) {
            this.delegate.onPostMapSharedElements(names, sharedElements);
        }
    }

    private void findAllPartialMatches(TransitionName tn, List<Pair<View, String>> transitionViews, List<View> partialMatches) {
        partialMatches.clear();
        for (Pair<View, String> p : transitionViews) {
            if (tn.partialEquals(TransitionName.parse((String) p.second))) {
                partialMatches.add(p.first);
            }
        }
    }

    @TargetApi(23)
    public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
        Transition enterTransition = this.sharedElementEnterTransition == null ? getDefaultSharedElementEnterTransition() : this.sharedElementEnterTransition;
        Transition returnTransition = this.sharedElementReturnTransition == null ? getDefaultSharedElementReturnTransition() : this.sharedElementReturnTransition;
        crossFadePartialMatchImageViews(sharedElementNames, sharedElements, sharedElementSnapshots, (int) returnTransition.getDuration());
        setTargetViews(enterTransition, sharedElements);
        setTargetViews(returnTransition, sharedElements);
        Window window = this.activity.getWindow();
        window.setSharedElementEnterTransition(enterTransition);
        window.setSharedElementReturnTransition(returnTransition);
        window.setTransitionBackgroundFadeDuration(!this.endCalledSinceOnMap ? this.enterBackgroundFadeDuration : this.returnBackgroundFadeDuration);
    }

    @TargetApi(23)
    private void setTargetViews(Transition transition, List<View> targets) {
        if (transition instanceof TransitionSet) {
            TransitionSet set = (TransitionSet) transition;
            for (int i = set.getTransitionCount() - 1; i >= 0; i--) {
                set.getTransitionAt(i).setMatchOrder(new int[]{1});
            }
        }
        transition.setMatchOrder(new int[]{1});
        for (View target : targets) {
            transition.addTarget(target);
        }
    }

    public void onSharedElementEnd(List<String> list, List<View> list2, List<View> list3) {
        this.endCalledSinceOnMap = true;
    }

    private void crossFadePartialMatchImageViews(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots, int duration) {
        if (sharedElementNames != null && sharedElementSnapshots != null && sharedElementNames.size() == sharedElementSnapshots.size()) {
            for (int i = sharedElementNames.size() - 1; i >= 0; i--) {
                View snapshotView = (View) sharedElementSnapshots.get(i);
                if (snapshotView != null && (snapshotView instanceof ImageView)) {
                    TransitionName tn1 = TransitionName.parse((String) sharedElementNames.get(i));
                    for (View se : sharedElements) {
                        if (se instanceof ImageView) {
                            TransitionName tn2 = TransitionName.parse(ViewCompat.getTransitionName(se));
                            if (tn1.partialEquals(tn2) && tn1.subId() != tn2.subId()) {
                                Drawable sharedElementDrawable = ((ImageView) se).getDrawable();
                                if (sharedElementDrawable == null) {
                                    sharedElementDrawable = new ColorDrawable(0);
                                }
                                Drawable sharedElementSnapshotDrawable = ((ImageView) snapshotView).getDrawable();
                                if (sharedElementSnapshotDrawable == null) {
                                    sharedElementSnapshotDrawable = new ColorDrawable(0);
                                }
                                TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{sharedElementDrawable, sharedElementSnapshotDrawable});
                                ((ImageView) se).setImageDrawable(transitionDrawable);
                                transitionDrawable.startTransition(duration);
                            }
                        }
                    }
                }
            }
        }
    }

    @TargetApi(23)
    private Transition getDefaultSharedElementEnterTransition() {
        return buildDefaultTransition().setDuration(300);
    }

    @TargetApi(23)
    private Transition getDefaultSharedElementReturnTransition() {
        return buildDefaultTransition().setDuration(200);
    }

    @TargetApi(23)
    private Transition buildDefaultTransition() {
        TransitionSet set = new TransitionSet();
        set.addTransition(new ChangeBounds());
        set.addTransition(new Fade());
        set.addTransition(new ChangeImageTransform());
        set.setInterpolator(new FastOutSlowInInterpolator());
        return set;
    }

    private View getDecorView() {
        return this.activity.getWindow().getDecorView();
    }
}
