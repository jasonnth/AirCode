package p005cn.jpush.android.data;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import java.io.InputStream;
import java.util.ArrayList;

/* renamed from: cn.jpush.android.data.SystemFullScreenView */
public class SystemFullScreenView extends RelativeLayout {
    /* access modifiers changed from: private */
    public int currentIndex = 0;
    /* access modifiers changed from: private */
    public Dialog dialog = null;
    /* access modifiers changed from: private */
    public Button left;
    /* access modifiers changed from: private */
    public OnSystemViewClickListener onSystemViewClickListener;
    /* access modifiers changed from: private */
    public Button right;
    /* access modifiers changed from: private */
    public int size = 0;

    /* renamed from: cn.jpush.android.data.SystemFullScreenView$GalleryAdapter */
    private class GalleryAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<String> imagePaths;

        public GalleryAdapter(Context context2, ArrayList<String> imagePaths2) {
            this.context = context2;
            this.imagePaths = imagePaths2;
        }

        public int getCount() {
            return SystemFullScreenView.this.size;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView != null) {
                return convertView;
            }
            Bitmap bitmap = BitmapFactory.decodeFile((String) this.imagePaths.get(position));
            ImageView iv = new ImageView(this.context);
            iv.setScaleType(ScaleType.CENTER_CROP);
            iv.setLayoutParams(new LayoutParams(-1, -1));
            if (bitmap != null) {
                iv.setImageBitmap(bitmap);
            }
            return iv;
        }
    }

    /* renamed from: cn.jpush.android.data.SystemFullScreenView$MyGallery */
    private class MyGallery extends Gallery {
        public MyGallery(Context context) {
            super(context);
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }

    /* renamed from: cn.jpush.android.data.SystemFullScreenView$OnSystemViewClickListener */
    public interface OnSystemViewClickListener {
        void onBottomButtonClick(View view, int i);

        void onCloseButtonClick(View view, int i);

        void onItemClick(DialogInterface dialogInterface, int i, boolean z);
    }

    public void setOnSystemViewClickListener(OnSystemViewClickListener onButtonClickListener) {
        this.onSystemViewClickListener = onButtonClickListener;
    }

    public SystemFullScreenView(Context context, ArrayList<String> imagePaths, boolean isAllowClose, final boolean isDownload) throws Exception {
        super(context);
        this.size = imagePaths.size();
        int padding = dip2px(context, 15.0f);
        RelativeLayout.LayoutParams rParams = new RelativeLayout.LayoutParams(-1, -1);
        final MyGallery gallery = new MyGallery(context);
        gallery.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                SystemFullScreenView.this.currentIndex = position;
                if (position <= 0) {
                    SystemFullScreenView.this.left.setVisibility(8);
                } else {
                    SystemFullScreenView.this.left.setVisibility(0);
                }
                if (position >= SystemFullScreenView.this.size - 1) {
                    SystemFullScreenView.this.right.setVisibility(8);
                } else {
                    SystemFullScreenView.this.right.setVisibility(0);
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        gallery.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (SystemFullScreenView.this.onSystemViewClickListener != null) {
                    SystemFullScreenView.this.onSystemViewClickListener.onItemClick(SystemFullScreenView.this.dialog, position, isDownload);
                }
            }
        });
        gallery.setAdapter(new GalleryAdapter(context, imagePaths));
        gallery.setSpacing(dip2px(context, 20.0f));
        addView(gallery, rParams);
        if (isAllowClose) {
            ImageView topRightButton = new ImageView(context);
            InputStream is = context.getResources().getAssets().open("jpush_close.png");
            topRightButton.setImageDrawable(new BitmapDrawable(is));
            is.close();
            topRightButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (SystemFullScreenView.this.onSystemViewClickListener != null) {
                        SystemFullScreenView.this.onSystemViewClickListener.onCloseButtonClick(v, SystemFullScreenView.this.currentIndex);
                    }
                    SystemFullScreenView.this.dialog.dismiss();
                }
            });
            RelativeLayout.LayoutParams rParams2 = new RelativeLayout.LayoutParams(dip2px(context, 40.0f), dip2px(context, 40.0f));
            rParams2.addRule(10, -1);
            rParams2.addRule(11, -1);
            rParams2.topMargin = dip2px(context, 8.0f);
            rParams2.rightMargin = dip2px(context, 8.0f);
            addView(topRightButton, rParams2);
        }
        if (isDownload) {
            Button bottomButton = new Button(context);
            bottomButton.setText("下载安装");
            bottomButton.setTextColor(-1);
            InputStream is2 = context.getResources().getAssets().open("download.png");
            bottomButton.setBackgroundDrawable(new BitmapDrawable(is2));
            is2.close();
            bottomButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (SystemFullScreenView.this.onSystemViewClickListener != null) {
                        SystemFullScreenView.this.onSystemViewClickListener.onBottomButtonClick(v, SystemFullScreenView.this.currentIndex);
                    }
                    SystemFullScreenView.this.dialog.dismiss();
                }
            });
            RelativeLayout.LayoutParams rParams3 = new RelativeLayout.LayoutParams(-2, -2);
            rParams3.addRule(12, -1);
            rParams3.addRule(14, -1);
            rParams3.bottomMargin = dip2px(context, 8.0f);
            addView(bottomButton, rParams3);
        }
        this.left = new Button(context);
        this.left.setPadding(0, padding, padding, padding);
        InputStream is3 = context.getResources().getAssets().open("left.png");
        this.left.setBackgroundDrawable(new BitmapDrawable(is3));
        is3.close();
        RelativeLayout.LayoutParams rParams4 = new RelativeLayout.LayoutParams(dip2px(context, 30.0f), dip2px(context, 30.0f));
        rParams4.addRule(9, -1);
        rParams4.addRule(15, -1);
        this.left.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (SystemFullScreenView.this.currentIndex - 1 >= 0) {
                    gallery.setSelection(SystemFullScreenView.this.currentIndex - 1, true);
                }
            }
        });
        addView(this.left, rParams4);
        this.right = new Button(context);
        this.right.setPadding(padding, padding, 0, padding);
        InputStream is4 = context.getResources().getAssets().open("right.png");
        this.right.setBackgroundDrawable(new BitmapDrawable(is4));
        is4.close();
        RelativeLayout.LayoutParams rParams5 = new RelativeLayout.LayoutParams(dip2px(context, 30.0f), dip2px(context, 30.0f));
        rParams5.addRule(11, -1);
        rParams5.addRule(15, -1);
        this.right.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (SystemFullScreenView.this.currentIndex + 1 <= SystemFullScreenView.this.size - 1) {
                    gallery.setSelection(SystemFullScreenView.this.currentIndex + 1);
                }
            }
        });
        addView(this.right, rParams5);
        this.dialog = new Dialog(context, 16973834);
        Window window = this.dialog.getWindow();
        window.addFlags(1024);
        window.setType(2003);
        window.setFormat(-1);
    }

    private int dip2px(Context context, float dpValue) {
        return (int) (dpValue * context.getResources().getDisplayMetrics().density);
    }

    public void show() {
        this.dialog.show();
        this.dialog.setContentView(this, new RelativeLayout.LayoutParams(-1, -1));
    }
}
