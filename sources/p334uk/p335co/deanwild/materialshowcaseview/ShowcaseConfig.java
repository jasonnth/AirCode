package p334uk.p335co.deanwild.materialshowcaseview;

import p334uk.p335co.deanwild.materialshowcaseview.shape.CircleShape;
import p334uk.p335co.deanwild.materialshowcaseview.shape.Shape;

/* renamed from: uk.co.deanwild.materialshowcaseview.ShowcaseConfig */
public class ShowcaseConfig {
    public static final Shape DEFAULT_SHAPE = new CircleShape();
    private int mContentTextColor;
    private long mDelay;
    private int mDismissTextColor;
    private long mFadeDuration;
    private int mMaskColour;
    private Shape mShape;
    private int mShapePadding;
    private boolean renderOverNav;

    public long getDelay() {
        return this.mDelay;
    }

    public int getMaskColor() {
        return this.mMaskColour;
    }

    public int getContentTextColor() {
        return this.mContentTextColor;
    }

    public int getDismissTextColor() {
        return this.mDismissTextColor;
    }

    public long getFadeDuration() {
        return this.mFadeDuration;
    }

    public Shape getShape() {
        return this.mShape;
    }

    public int getShapePadding() {
        return this.mShapePadding;
    }

    public boolean getRenderOverNavigationBar() {
        return this.renderOverNav;
    }
}
