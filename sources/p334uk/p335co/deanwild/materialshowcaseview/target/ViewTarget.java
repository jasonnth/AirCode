package p334uk.p335co.deanwild.materialshowcaseview.target;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

/* renamed from: uk.co.deanwild.materialshowcaseview.target.ViewTarget */
public class ViewTarget implements Target {
    private final View mView;

    public ViewTarget(View view) {
        this.mView = view;
    }

    public Point getPoint() {
        int[] location = new int[2];
        this.mView.getLocationInWindow(location);
        return new Point(location[0] + (this.mView.getWidth() / 2), location[1] + (this.mView.getHeight() / 2));
    }

    public Rect getBounds() {
        int[] location = new int[2];
        this.mView.getLocationInWindow(location);
        return new Rect(location[0], location[1], location[0] + this.mView.getMeasuredWidth(), location[1] + this.mView.getMeasuredHeight());
    }
}
