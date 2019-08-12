package p334uk.p335co.deanwild.materialshowcaseview.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import p334uk.p335co.deanwild.materialshowcaseview.target.Target;

/* renamed from: uk.co.deanwild.materialshowcaseview.shape.RectangleShape */
public class RectangleShape implements Shape {
    private boolean adjustToTarget = true;
    private boolean fullWidth = false;
    private int height = 0;
    private Rect rect;
    private int width = 0;

    public RectangleShape(Rect bounds, boolean fullWidth2) {
        this.fullWidth = fullWidth2;
        this.height = bounds.height();
        if (fullWidth2) {
            this.width = Integer.MAX_VALUE;
        } else {
            this.width = bounds.width();
        }
        init();
    }

    private void init() {
        this.rect = new Rect((-this.width) / 2, (-this.height) / 2, this.width / 2, this.height / 2);
    }

    public void draw(Canvas canvas, Paint paint, int x, int y, int padding) {
        if (!this.rect.isEmpty()) {
            canvas.drawRect((float) ((this.rect.left + x) - padding), (float) ((this.rect.top + y) - padding), (float) (this.rect.right + x + padding), (float) (this.rect.bottom + y + padding), paint);
        }
    }

    public void updateTarget(Target target) {
        if (this.adjustToTarget) {
            Rect bounds = target.getBounds();
            this.height = bounds.height();
            if (this.fullWidth) {
                this.width = Integer.MAX_VALUE;
            } else {
                this.width = bounds.width();
            }
            init();
        }
    }

    public int getHeight() {
        return this.height;
    }
}
