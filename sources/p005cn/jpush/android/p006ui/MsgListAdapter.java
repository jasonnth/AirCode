package p005cn.jpush.android.p006ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.NinePatch;
import android.graphics.drawable.NinePatchDrawable;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import p005cn.jpush.android.data.Entity;
import p005cn.jpush.android.data.PkEntity;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.ui.MsgListAdapter */
public class MsgListAdapter extends ArrayAdapter<Entity> {
    private Context mContext = null;

    public MsgListAdapter(Context context, int textViewResourceId, List<Entity> objects) {
        super(context, textViewResourceId, objects);
        this.mContext = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view != null) {
            return view;
        }
        Entity entity = (Entity) getItem(position);
        int padding = AndroidUtil.dip2px(this.mContext, 5.0f);
        FrameLayout fl = new FrameLayout(this.mContext);
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        linearLayout.setPadding(padding, padding, padding, padding);
        linearLayout.setOrientation(1);
        try {
            InputStream is = this.mContext.getResources().getAssets().open("border_bg.9.png");
            Bitmap bp = BitmapFactory.decodeStream(is);
            if (bp == null) {
                throw new RuntimeException("get bitmap failed - border_bg.9.png");
            }
            Resources resources = this.mContext.getResources();
            NinePatch ninePatch = new NinePatch(bp, bp.getNinePatchChunk(), null);
            fl.setBackgroundDrawable(new NinePatchDrawable(resources, ninePatch));
            is.close();
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            LinearLayout linearLayout2 = new LinearLayout(this.mContext);
            linearLayout2.setOrientation(0);
            linearLayout.addView(linearLayout2, layoutParams);
            ImageView imageView = new ImageView(this.mContext);
            imageView.setBackgroundColor(-16711936);
            imageView.setScaleType(ScaleType.CENTER_CROP);
            Bitmap bm = BitmapFactory.decodeFile(((PkEntity) entity).aShowInfo._iconPath);
            if (bm != null) {
                imageView.setImageBitmap(bm);
            }
            int dip45 = AndroidUtil.dip2px(this.mContext, 45.0f);
            LayoutParams layoutParams2 = new LayoutParams(dip45, dip45);
            linearLayout2.addView(imageView, layoutParams2);
            LinearLayout linearLayout3 = new LinearLayout(this.mContext);
            linearLayout3.setOrientation(1);
            TextView textView = new TextView(this.mContext);
            textView.setTextSize(22.0f);
            textView.setSingleLine();
            textView.setEllipsize(TruncateAt.END);
            textView.setText(entity.messageId);
            linearLayout3.addView(textView);
            TextView textView2 = new TextView(this.mContext);
            textView2.setSingleLine();
            textView2.setEllipsize(TruncateAt.END);
            textView2.setText(entity.messageId);
            linearLayout3.addView(textView2);
            LayoutParams layoutParams3 = new LayoutParams(-2, -2);
            layoutParams3.gravity = 16;
            layoutParams3.leftMargin = AndroidUtil.dip2px(this.mContext, 5.0f);
            layoutParams3.rightMargin = AndroidUtil.dip2px(this.mContext, 5.0f);
            linearLayout2.addView(linearLayout3, layoutParams3);
            RelativeLayout bottomRL = new RelativeLayout(this.mContext);
            int dip18 = AndroidUtil.dip2px(this.mContext, 18.0f);
            LayoutParams layoutParams4 = new LayoutParams(dip18, dip18);
            LinearLayout bottomLeftLL = new LinearLayout(this.mContext);
            bottomLeftLL.setOrientation(0);
            for (int i = 0; i < 3; i++) {
                ImageView imageView2 = new ImageView(this.mContext);
                try {
                    imageView2.setImageBitmap(BitmapFactory.decodeStream(this.mContext.getAssets().open("full_star.png")));
                } catch (IOException e) {
                    Logger.m1420e(MsgListAdapter.class.getSimpleName(), e.getMessage());
                }
                bottomLeftLL.addView(imageView2, layoutParams4);
            }
            RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams5.addRule(9, -1);
            layoutParams5.addRule(15, -1);
            bottomRL.addView(bottomLeftLL, layoutParams5);
            RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams6.addRule(11, -1);
            layoutParams6.addRule(15, -1);
            TextView textView3 = new TextView(this.mContext);
            bottomRL.addView(textView3, layoutParams6);
            LayoutParams bottomLLLp = new LayoutParams(-1, -2);
            bottomLLLp.topMargin = AndroidUtil.dip2px(this.mContext, 5.0f);
            linearLayout.addView(bottomRL, bottomLLLp);
            FrameLayout.LayoutParams layoutParams7 = new FrameLayout.LayoutParams(-1, -2);
            layoutParams7.gravity = 17;
            layoutParams7.topMargin = AndroidUtil.dip2px(this.mContext, 2.0f);
            layoutParams7.leftMargin = layoutParams7.topMargin;
            layoutParams7.bottomMargin = layoutParams7.topMargin;
            layoutParams7.rightMargin = layoutParams7.topMargin;
            fl.addView(linearLayout, layoutParams7);
            return fl;
        } catch (IOException e2) {
        }
    }
}
