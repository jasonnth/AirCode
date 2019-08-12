package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.internal.zzac;
import java.util.List;

@zzme
public class zzgt extends RelativeLayout {
    private static final float[] zzGl = {5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f};
    private AnimationDrawable zzGm;

    public zzgt(Context context, zzgs zzgs, LayoutParams layoutParams) {
        super(context);
        zzac.zzw(zzgs);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(zzGl, null, null));
        shapeDrawable.getPaint().setColor(zzgs.getBackgroundColor());
        setLayoutParams(layoutParams);
        zzw.zzcO().zza((View) this, (Drawable) shapeDrawable);
        LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        if (!TextUtils.isEmpty(zzgs.getText())) {
            LayoutParams layoutParams3 = new LayoutParams(-2, -2);
            TextView textView = new TextView(context);
            textView.setLayoutParams(layoutParams3);
            textView.setId(1195835393);
            textView.setTypeface(Typeface.DEFAULT);
            textView.setText(zzgs.getText());
            textView.setTextColor(zzgs.getTextColor());
            textView.setTextSize((float) zzgs.getTextSize());
            textView.setPadding(zzel.zzeT().zzb(context, 4), 0, zzel.zzeT().zzb(context, 4), 0);
            addView(textView);
            layoutParams2.addRule(1, textView.getId());
        }
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams2);
        imageView.setId(1195835394);
        List<Drawable> zzfL = zzgs.zzfL();
        if (zzfL.size() > 1) {
            this.zzGm = new AnimationDrawable();
            for (Drawable addFrame : zzfL) {
                this.zzGm.addFrame(addFrame, zzgs.zzfM());
            }
            zzw.zzcO().zza((View) imageView, (Drawable) this.zzGm);
        } else if (zzfL.size() == 1) {
            imageView.setImageDrawable((Drawable) zzfL.get(0));
        }
        addView(imageView);
    }

    public void onAttachedToWindow() {
        if (this.zzGm != null) {
            this.zzGm.start();
        }
        super.onAttachedToWindow();
    }
}
