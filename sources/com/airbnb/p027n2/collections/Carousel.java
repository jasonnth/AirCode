package com.airbnb.p027n2.collections;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.n2.R;
import com.airbnb.p027n2.epoxy.TypedAirEpoxyController;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.utils.AutoScrollingController;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.List;

/* renamed from: com.airbnb.n2.collections.Carousel */
public class Carousel extends AirRecyclerView implements DividerView {
    private AutoScrollingController autoScrollingController;
    private CarouselLayoutManager defaultLayoutManager;
    private CarouselEpoxyController epoxyController;
    private OnSnapToPositionListener snapToPositionListener;

    /* renamed from: com.airbnb.n2.collections.Carousel$CarouselEpoxyController */
    private static class CarouselEpoxyController extends TypedAirEpoxyController<List<? extends EpoxyModel<?>>> {
        public CarouselEpoxyController() {
            disableAutoDividers();
            setFilterDuplicates(true);
        }

        /* access modifiers changed from: protected */
        public void buildModels(List<? extends EpoxyModel<?>> models) {
            add(models);
        }
    }

    /* renamed from: com.airbnb.n2.collections.Carousel$OnSnapToPositionListener */
    public interface OnSnapToPositionListener {
        void onSnappedToPosition(int i, boolean z, boolean z2);
    }

    public static int getCarouselCardWidth(Context context) {
        return getCarouselCardWidth(context, context.getResources().getInteger(R.integer.n2_carousel_cards_on_screen));
    }

    public static int getCarouselCardWidth(Context context, int cardsOnScreen) {
        return getCarouselCardWidth(context, (float) cardsOnScreen, context.getResources().getDimensionPixelSize(R.dimen.n2_carousel_card_padding));
    }

    public static int getCarouselCardWidth(Context context, float cardsOnScreen, int cardMargin) {
        return getCarouselCardWidth(context, cardsOnScreen, cardMargin, context.getResources().getDimensionPixelSize(R.dimen.n2_carousel_horizontal_padding));
    }

    public static int getCarouselCardWidth(Context context, float cardsOnScreen, int cardMargin, int carouselPadding) {
        return Math.round(Math.min((((float) (ViewLibUtils.getScreenWidth(context) - (carouselPadding * 2))) - ((2.0f * cardsOnScreen) * ((float) cardMargin))) / cardsOnScreen, (float) context.getResources().getDimensionPixelSize(R.dimen.n2_carousel_max_width)));
    }

    public Carousel(Context context) {
        super(context);
        init(null);
    }

    public Carousel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Carousel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        setHasFixedSize(true);
        setRemoveAdapterWhenDetachedFromWindow(false);
        setFocusableInTouchMode(false);
        this.defaultLayoutManager = new CarouselLayoutManager(getContext(), this);
        this.defaultLayoutManager.setOnSnapToPositionListener(Carousel$$Lambda$1.lambdaFactory$(this));
        this.autoScrollingController = new AutoScrollingController(Carousel$$Lambda$2.lambdaFactory$(this));
        super.setLayoutManager(this.defaultLayoutManager);
        TypedArray attrArray = getContext().obtainStyledAttributes(attrs, R.styleable.n2_Carousel);
        if (attrArray.getBoolean(R.styleable.n2_Carousel_n2_autoScroll, false)) {
            this.autoScrollingController.start();
        }
        this.defaultLayoutManager.setIsSingleScroll(attrArray.getBoolean(R.styleable.n2_Carousel_n2_singleScroll, false));
        attrArray.recycle();
    }

    static /* synthetic */ void lambda$init$0(Carousel carousel, int position, boolean userSwipedLeft) {
        if (carousel.snapToPositionListener != null) {
            carousel.snapToPositionListener.onSnappedToPosition(position, userSwipedLeft, !carousel.autoScrollingController.isCancelled());
        }
    }

    public void scrollTo(int x, int y) {
    }

    public boolean fling(int velocityX, int velocityY) {
        if (!(getLayoutManager() instanceof CarouselLayoutManager)) {
            return super.fling(velocityX, velocityY);
        }
        if (ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity() <= Math.abs(velocityX)) {
            CarouselLayoutManager layoutManager = (CarouselLayoutManager) getLayoutManager();
            int positionForVelocity = layoutManager.getPositionForVelocity(velocityX);
            int currentPosition = layoutManager.getPositionAtStartOfDrag();
            if (!(positionForVelocity == -1 || positionForVelocity == currentPosition)) {
                smoothScrollToPosition(positionForVelocity);
                return true;
            }
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent e) {
        this.autoScrollingController.cancel();
        return super.onInterceptTouchEvent(e);
    }

    public boolean onTouchEvent(MotionEvent e) {
        boolean ret = super.onTouchEvent(e);
        if (getLayoutManager() instanceof CarouselLayoutManager) {
            CarouselLayoutManager lm = (CarouselLayoutManager) getLayoutManager();
            if ((e.getAction() == 1 || e.getAction() == 3) && getScrollState() == 0) {
                int closestPosition = lm.getClosestPosition();
                if (closestPosition != -1) {
                    smoothScrollToPosition(closestPosition);
                }
            }
        }
        return ret;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.autoScrollingController.cancel();
    }

    public int getClosestPosition() {
        return this.defaultLayoutManager.getClosestPosition();
    }

    public void setDefaultLayoutManager() {
        setLayoutManager(this.defaultLayoutManager);
    }

    public void scrollToPosition(int position) {
        this.defaultLayoutManager.scrollToPositionWithOffset(position, 0);
        this.autoScrollingController.cancel();
    }

    public void smoothScrollToPosition(int position) {
        super.smoothScrollToPosition(position);
        this.autoScrollingController.cancel();
    }

    public void cancelAutoScrolling() {
        this.autoScrollingController.cancel();
    }

    public void showDivider(boolean showDivider) {
    }

    public void setSnapToPositionListener(OnSnapToPositionListener listener) {
        this.snapToPositionListener = listener;
    }

    /* access modifiers changed from: private */
    public boolean autoScrollToPosition(int position) {
        boolean canScroll = getAdapter() != null && getAdapter().getItemCount() > position;
        if (canScroll) {
            super.smoothScrollToPosition(position);
        }
        return canScroll;
    }

    public void setModels(List<? extends EpoxyModel<?>> models) {
        if (this.epoxyController == null) {
            this.epoxyController = new CarouselEpoxyController();
        }
        this.epoxyController.setData(models);
        if (getAdapter() != this.epoxyController.getAdapter()) {
            setAdapter(this.epoxyController.getAdapter());
        }
    }

    public void clearModels() {
        this.epoxyController.cancelPendingModelBuild();
        this.epoxyController = null;
        swapAdapter(null, true);
    }
}
