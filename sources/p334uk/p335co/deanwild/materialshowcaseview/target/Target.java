package p334uk.p335co.deanwild.materialshowcaseview.target;

import android.graphics.Point;
import android.graphics.Rect;

/* renamed from: uk.co.deanwild.materialshowcaseview.target.Target */
public interface Target {
    public static final Target NONE = new Target() {
        public Point getPoint() {
            return new Point(1000000, 1000000);
        }

        public Rect getBounds() {
            Point p = getPoint();
            return new Rect(p.x - 190, p.y - 190, p.x + 190, p.y + 190);
        }
    };

    Rect getBounds();

    Point getPoint();
}
