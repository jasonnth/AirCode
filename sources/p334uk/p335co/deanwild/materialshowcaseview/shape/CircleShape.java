package p334uk.p335co.deanwild.materialshowcaseview.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import p334uk.p335co.deanwild.materialshowcaseview.target.Target;

/* renamed from: uk.co.deanwild.materialshowcaseview.shape.CircleShape */
public class CircleShape implements Shape {
    private boolean adjustToTarget;
    private int radius;

    public CircleShape() {
        this.radius = 200;
        this.adjustToTarget = true;
    }

    public CircleShape(int radius2) {
        this.radius = 200;
        this.adjustToTarget = true;
        this.radius = radius2;
    }

    public CircleShape(Rect bounds) {
        this(getPreferredRadius(bounds));
    }

    public CircleShape(Target target) {
        this(target.getBounds());
    }

    public void draw(Canvas canvas, Paint paint, int x, int y, int padding) {
        if (this.radius > 0) {
            canvas.drawCircle((float) x, (float) y, (float) (this.radius + padding), paint);
        }
    }

    public void updateTarget(Target target) {
        if (this.adjustToTarget) {
            this.radius = getPreferredRadius(target.getBounds());
        }
    }

    public int getHeight() {
        return this.radius * 2;
    }

    public static int getPreferredRadius(Rect bounds) {
        return Math.max(bounds.width(), bounds.height()) / 2;
    }
}
