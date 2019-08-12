package p334uk.p335co.deanwild.materialshowcaseview.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import p334uk.p335co.deanwild.materialshowcaseview.target.Target;

/* renamed from: uk.co.deanwild.materialshowcaseview.shape.Shape */
public interface Shape {
    void draw(Canvas canvas, Paint paint, int i, int i2, int i3);

    int getHeight();

    void updateTarget(Target target);
}
