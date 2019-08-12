package p005cn.jpush.android.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import com.airbnb.android.airmapview.AirMapInterface;
import com.airbnb.android.lib.fragments.verifiedid.OfficialIdPhotoSelectionFragment;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;
import org.jmrtd.lds.LDSFile;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.data.PkEntity.AShowInfo;
import p005cn.jpush.android.helpers.ProtocolHelper;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.webview.bridge.CustomChromeClient;
import p005cn.jpush.android.webview.bridge.HostJsScope;
import p005cn.jpush.android.webview.bridge.WebViewHelper;

/* renamed from: cn.jpush.android.data.JPushView */
public class JPushView extends LinearLayout {
    private static final String TAG = "JPushView";
    public static WebViewHelper webViewHelper = null;
    private Button btn_cancel;
    private Button btn_down;
    private float density;
    /* access modifiers changed from: private */
    public Bitmap iconBitmap;
    /* access modifiers changed from: private */
    public Bitmap imageBitmap;
    /* access modifiers changed from: private */
    public ImageView iv_1;
    /* access modifiers changed from: private */
    public ImageView iv_top_icon;
    private LinearLayout ll_body;
    private LinearLayout ll_bottom;
    private LinearLayout ll_center;
    private LinearLayout ll_image;
    private LinearLayout ll_info_right;
    private LinearLayout ll_top;
    private Context mContext;
    private Entity mEntity;
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                if (JPushView.this.iv_top_icon != null && JPushView.this.iconBitmap != null) {
                    JPushView.this.iv_top_icon.setImageBitmap(JPushView.this.iconBitmap);
                }
            } else if (msg.what == 1 && JPushView.this.iv_1 != null && JPushView.this.imageBitmap != null) {
                JPushView.this.iv_1.setImageBitmap(JPushView.this.imageBitmap);
            }
        }
    };
    private WebView mWebView;
    private ScrollView scrollView;
    int[][] starIds = {new int[]{0, 0}, new int[]{1, 1}, new int[]{0, 1}};
    private Bitmap star_0 = null;
    private Bitmap star_1 = null;
    private int tmpStarId = 0;
    private TextView tv_center_bar;
    private TextView tv_info;
    private TextView tv_info_title;
    private TextView tv_size;
    private TextView tv_top_title;
    private TextView tv_type;
    private TextView tv_version;
    private int width;

    public JPushView(Context context, Entity entity) {
        super(context, null);
        Logger.m1416d(TAG, "OnCreate JPushView");
        this.mContext = context;
        this.mEntity = entity;
        this.width = this.mContext.getResources().getDisplayMetrics().widthPixels;
        this.density = this.mContext.getResources().getDisplayMetrics().density;
        try {
            this.star_0 = BitmapFactory.decodeStream(this.mContext.getAssets().open("star_0"));
            this.star_1 = BitmapFactory.decodeStream(this.mContext.getAssets().open("star_1"));
        } catch (IOException e) {
        }
        this.tmpStarId = new Random().nextInt(this.starIds.length);
        initThisLayoutParam();
        initTopView();
        initCenterView();
        initBottomView();
    }

    private void initThisLayoutParam() {
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        setLayoutParams(layoutParams);
        setOrientation(1);
        setPadding(5, 5, 5, 5);
        this.ll_body = new LinearLayout(this.mContext);
        this.ll_body.setOrientation(1);
        this.ll_body.setGravity(17);
        this.ll_body.setPadding(1, 1, 1, 1);
        this.ll_body.setBackgroundColor(Color.rgb(LDSFile.EF_DG2_TAG, LDSFile.EF_DG2_TAG, LDSFile.EF_DG2_TAG));
        addView(this.ll_body, layoutParams);
    }

    private void initTopView() {
        LayoutParams topParams = new LayoutParams(-1, 75);
        this.ll_top = new LinearLayout(this.mContext);
        this.ll_top.setBackgroundColor(Color.rgb(0, 160, 200));
        this.ll_top.setPadding(5, 5, 5, 5);
        this.ll_top.setOrientation(0);
        this.ll_top.setGravity(17);
        this.ll_body.addView(this.ll_top, topParams);
        LayoutParams iconParams = new LayoutParams(72, 72);
        this.iv_top_icon = new ImageView(this.mContext);
        this.iv_top_icon.setScaleType(ScaleType.FIT_XY);
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(this.mContext.getAssets().open("def_icon"));
            if (bitmap != null) {
                this.iv_top_icon.setImageBitmap(bitmap);
            }
        } catch (IOException e) {
        }
        this.ll_top.addView(this.iv_top_icon, iconParams);
        LayoutParams titleParams = new LayoutParams(-1, -2);
        this.tv_top_title = new TextView(this.mContext);
        this.tv_top_title.setMaxEms(10);
        this.tv_top_title.setMaxLines(1);
        this.tv_top_title.setSingleLine(true);
        this.tv_top_title.setTextColor(AirMapInterface.CIRCLE_BORDER_COLOR);
        this.tv_top_title.setPadding(15, 0, 0, 0);
        this.tv_top_title.setTextSize(20.0f);
        this.ll_top.addView(this.tv_top_title, titleParams);
    }

    private void initCenterView() {
        LayoutParams centerParams = new LayoutParams(-1, -2);
        this.scrollView = new ScrollView(this.mContext);
        centerParams.weight = 1.0f;
        this.ll_body.addView(this.scrollView, centerParams);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        this.ll_center = new LinearLayout(this.mContext);
        this.ll_center.setOrientation(1);
        this.scrollView.addView(this.ll_center, layoutParams);
        LayoutParams tvBarParams = new LayoutParams(-1, -2);
        tvBarParams.setMargins(0, 1, 0, 0);
        this.tv_center_bar = new TextView(this.mContext);
        this.tv_center_bar.setPadding(20, 5, 0, 5);
        this.tv_center_bar.setBackgroundColor(Color.rgb(229, 229, 229));
        this.tv_center_bar.setText("热销软件");
        this.tv_center_bar.setTextColor(AirMapInterface.CIRCLE_BORDER_COLOR);
        this.tv_center_bar.setTextSize(15.0f);
        this.ll_center.addView(this.tv_center_bar, tvBarParams);
        LayoutParams barInfoParams = new LayoutParams(-1, -2);
        barInfoParams.setMargins(0, 1, 0, 0);
        LinearLayout ll_info = new LinearLayout(this.mContext);
        ll_info.setBackgroundColor(Color.rgb(247, 248, 249));
        ll_info.setGravity(17);
        this.ll_center.addView(ll_info, barInfoParams);
        LayoutParams tvParams = new LayoutParams(-2, -2);
        LayoutParams infoLeftParams = new LayoutParams(-2, -2);
        infoLeftParams.weight = 1.0f;
        LinearLayout ll_info_left = new LinearLayout(this.mContext);
        ll_info_left.setOrientation(1);
        ll_info.addView(ll_info_left, infoLeftParams);
        this.tv_type = new TextView(this.mContext);
        this.tv_type.setPadding(20, 10, 5, 5);
        this.tv_type.setTextColor(AirMapInterface.CIRCLE_BORDER_COLOR);
        this.tv_type.setTextSize(12.0f);
        ll_info_left.addView(this.tv_type, tvParams);
        this.tv_version = new TextView(this.mContext);
        this.tv_version.setPadding(20, 5, 5, 5);
        this.tv_version.setTextColor(AirMapInterface.CIRCLE_BORDER_COLOR);
        this.tv_version.setTextSize(12.0f);
        ll_info_left.addView(this.tv_version, tvParams);
        this.tv_size = new TextView(this.mContext);
        this.tv_size.setPadding(20, 5, 5, 10);
        this.tv_size.setTextColor(AirMapInterface.CIRCLE_BORDER_COLOR);
        this.tv_size.setTextSize(12.0f);
        ll_info_left.addView(this.tv_size, tvParams);
        LayoutParams infoRightParams = new LayoutParams(-2, -2);
        infoLeftParams.weight = 1.0f;
        this.ll_info_right = new LinearLayout(this.mContext);
        this.ll_info_right.setOrientation(1);
        this.ll_info_right.setGravity(17);
        this.ll_info_right.setPadding(0, 0, 100, 0);
        ll_info.addView(this.ll_info_right, infoRightParams);
        initStarAndPingf(this.ll_info_right);
        this.tv_info_title = new TextView(this.mContext);
        this.tv_info_title.setBackgroundColor(Color.rgb(229, 229, 229));
        this.tv_center_bar.setTextSize(15.0f);
        this.tv_info_title.setPadding(20, 5, 5, 5);
        this.tv_info_title.setTextColor(AirMapInterface.CIRCLE_BORDER_COLOR);
        this.tv_info_title.setText("软件介绍");
        this.ll_center.addView(this.tv_info_title, tvBarParams);
        LayoutParams tvInfoParams = new LayoutParams(-1, -2);
        tvInfoParams.setMargins(0, 1, 0, 0);
        this.tv_info = new TextView(this.mContext);
        this.tv_info.setBackgroundColor(Color.rgb(247, 248, 249));
        this.tv_info.setTextSize(12.0f);
        this.tv_info.setPadding(20, 20, 20, 20);
        this.tv_info.setTextColor(AirMapInterface.CIRCLE_BORDER_COLOR);
        this.ll_center.addView(this.tv_info, tvInfoParams);
        LayoutParams ll_img_Params = new LayoutParams(-1, -2);
        this.ll_image = new LinearLayout(this.mContext);
        this.ll_image.setOrientation(1);
        this.ll_image.setGravity(17);
        this.ll_image.setPadding(0, 20, 0, 0);
        this.ll_center.addView(this.ll_image, ll_img_Params);
        LayoutParams ivParams = new LayoutParams((int) (220.0f * this.density), (int) (300.0f * this.density));
        this.iv_1 = new ImageView(this.mContext);
        this.iv_1.setScaleType(ScaleType.FIT_XY);
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(this.mContext.getAssets().open("def_img"));
            if (bitmap != null) {
                this.iv_1.setImageBitmap(bitmap);
            }
        } catch (IOException e) {
        }
        this.ll_image.addView(this.iv_1, ivParams);
    }

    private void initBottomView() {
        LayoutParams bottomParams = new LayoutParams(-1, -2);
        this.ll_bottom = new LinearLayout(this.mContext);
        this.ll_bottom.setBackgroundColor(Color.rgb(0, 160, 200));
        this.ll_bottom.setPadding(5, 5, 5, 5);
        this.ll_bottom.setOrientation(0);
        this.ll_bottom.setGravity(17);
        this.ll_body.addView(this.ll_bottom, bottomParams);
        LayoutParams btnParams = new LayoutParams(this.width / 2, -2);
        this.btn_down = new Button(this.mContext);
        this.btn_down.setText("下载安装");
        this.ll_bottom.addView(this.btn_down, btnParams);
        this.btn_cancel = new Button(this.mContext);
        this.btn_cancel.setText("取消");
        this.ll_bottom.addView(this.btn_cancel, btnParams);
    }

    private void initStarAndPingf(LinearLayout layout) {
        LayoutParams infoRightParams = new LayoutParams(-2, -2);
        LinearLayout ll = new LinearLayout(this.mContext);
        ll.setOrientation(0);
        ll.setGravity(17);
        layout.addView(ll, infoRightParams);
        if (this.star_0 != null) {
            for (int i = 0; i < 3; i++) {
                ImageView iv_star_0 = new ImageView(this.mContext);
                iv_star_0.setImageBitmap(this.star_0);
                ll.addView(iv_star_0, infoRightParams);
            }
        }
        int[] id = this.starIds[this.tmpStarId];
        if (id.length == 2) {
            ImageView iv_star_02 = new ImageView(this.mContext);
            iv_star_02.setImageBitmap(id[0] == 0 ? this.star_0 : this.star_1);
            ll.addView(iv_star_02, infoRightParams);
            ImageView iv_star_03 = new ImageView(this.mContext);
            iv_star_03.setImageBitmap(id[1] == 0 ? this.star_0 : this.star_1);
            ll.addView(iv_star_03, infoRightParams);
        } else {
            for (int i2 = 0; i2 < 2; i2++) {
                ImageView iv_star_04 = new ImageView(this.mContext);
                iv_star_04.setImageBitmap(this.star_1);
                ll.addView(iv_star_04, infoRightParams);
            }
        }
        Random random = new Random();
        TextView pingf = new TextView(this.mContext);
        pingf.setPadding(10, 5, 5, 5);
        pingf.setTextSize(15.0f);
        pingf.setText(String.valueOf(random.nextInt(OfficialIdPhotoSelectionFragment.MAX_IMAGE_SIZE) + 200) + " 人评分");
        layout.addView(pingf, infoRightParams);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public boolean setLVTitleIcon(AShowInfo showInfo) {
        if (this.iv_top_icon == null) {
            return false;
        }
        this.iv_top_icon.setVisibility(0);
        if (!TextUtils.isEmpty(showInfo._iconPath)) {
            Bitmap bitmap = BitmapFactory.decodeFile(showInfo._iconPath);
            if (bitmap == null) {
                return true;
            }
            this.iv_top_icon.setImageBitmap(bitmap);
            return true;
        } else if (!ProtocolHelper.checkValidUrl(showInfo.iconUrl)) {
            return false;
        } else {
            UpdateViewwImage(showInfo.iconUrl, 0);
            return true;
        }
    }

    public void setLVTitleText(String content) {
        if (this.tv_top_title != null) {
            if (!TextUtils.isEmpty(content)) {
                this.tv_top_title.setVisibility(0);
            } else {
                this.tv_top_title.setVisibility(8);
            }
            this.tv_top_title.setText(content);
        }
    }

    public void setLVSoftType(String content) {
        String string = "类型 : %s";
        if (this.tv_type != null) {
            if (!TextUtils.isEmpty(content)) {
                this.tv_type.setVisibility(0);
            } else {
                this.tv_type.setVisibility(8);
            }
            this.tv_type.setText(String.format(string, new Object[]{content}));
        }
    }

    public void setLVSoftVerstion(String content) {
        String string = "版本 : %s";
        if (this.tv_version != null) {
            if (!TextUtils.isEmpty(content)) {
                this.tv_version.setVisibility(0);
            } else {
                this.tv_version.setVisibility(8);
            }
            this.tv_version.setText(String.format(string, new Object[]{content}));
        }
    }

    public void setLVSoftSize(String content) {
        String string = "大小 : %s";
        if (this.tv_size != null) {
            if (!TextUtils.isEmpty(content)) {
                this.tv_size.setVisibility(0);
            } else {
                this.tv_size.setVisibility(8);
            }
            this.tv_size.setText(String.format(string, new Object[]{content}));
        }
    }

    public void setLVSoftInfo(String content) {
        if (this.tv_info != null) {
            if (!TextUtils.isEmpty(content)) {
                this.tv_info.setVisibility(0);
                if (this.tv_info_title != null) {
                    this.tv_info_title.setVisibility(0);
                }
            } else {
                this.tv_info.setVisibility(8);
                if (this.tv_info_title != null) {
                    this.tv_info_title.setVisibility(8);
                }
            }
            this.tv_info.setText(content);
        }
    }

    public boolean setLVSoftImage(AShowInfo showInfo) {
        if (this.iv_1 == null) {
            return false;
        }
        this.iv_1.setVisibility(0);
        if (!TextUtils.isEmpty(showInfo._imagePath)) {
            Bitmap bitmap = BitmapFactory.decodeFile(showInfo._imagePath);
            if (bitmap == null) {
                return true;
            }
            this.iv_1.setImageBitmap(bitmap);
            return true;
        } else if (!ProtocolHelper.checkValidUrl(showInfo.screenShotUrl)) {
            return false;
        } else {
            UpdateViewwImage(showInfo.screenShotUrl, 1);
            return true;
        }
    }

    public void setLVShowStarAndPingf(boolean flag) {
        if (this.ll_info_right != null) {
            this.ll_info_right.setVisibility(flag ? 0 : 8);
        }
    }

    @SuppressLint({"NewApi"})
    public void setLVWebUrl(String url, boolean showDownloadButton, boolean showCancelButton) {
        int i;
        int i2 = 0;
        Logger.m1416d(TAG, "loadUrl:" + url);
        if (this.ll_center != null) {
            this.ll_top.setVisibility(8);
            this.ll_center.removeAllViews();
            LayoutParams layoutParams = new LayoutParams(-1, -2);
            layoutParams.weight = 1.0f;
            this.mWebView = new WebView(this.mContext);
            this.mWebView.clearCache(true);
            WebSettings webSettings = this.mWebView.getSettings();
            webSettings.setDomStorageEnabled(true);
            AndroidUtil.webSettings(webSettings);
            webViewHelper = new WebViewHelper(this.mContext, this.mEntity);
            this.mWebView.removeJavascriptInterface("searchBoxJavaBridge_");
            this.mWebView.setWebChromeClient(new CustomChromeClient(JPushConstants.PARAM_JS_MODULE, HostJsScope.class));
            this.mWebView.loadUrl(url);
            this.mWebView.requestFocus();
            this.ll_center.addView(this.mWebView, layoutParams);
            Button button = this.btn_down;
            if (showDownloadButton) {
                i = 0;
            } else {
                i = 8;
            }
            button.setVisibility(i);
            Button button2 = this.btn_cancel;
            if (!showCancelButton) {
                i2 = 8;
            }
            button2.setVisibility(i2);
        }
    }

    public void onDestroy() {
        if (this.mWebView != null) {
            this.mWebView.destroy();
        }
    }

    public void setDownButtonOnClickListener(OnClickListener onClickListener) {
        if (this.btn_down != null) {
            this.btn_down.setOnClickListener(onClickListener);
        }
    }

    public void setCancelButtonOnClickListener(OnClickListener onClickListener) {
        if (this.btn_cancel != null) {
            this.btn_cancel.setOnClickListener(onClickListener);
        }
    }

    private void UpdateViewwImage(final String url, final int wait) {
        new Thread() {
            public void run() {
                if (!TextUtils.isEmpty(url)) {
                    InputStream is = null;
                    try {
                        is = (InputStream) new URL(url).getContent();
                        if (wait == 0) {
                            JPushView.this.iconBitmap = BitmapFactory.decodeStream(is);
                            JPushView.this.mHandler.sendEmptyMessage(0);
                        } else if (wait == 1) {
                            JPushView.this.imageBitmap = BitmapFactory.decodeStream(is);
                            JPushView.this.mHandler.sendEmptyMessage(1);
                        }
                        if (is != null) {
                            try {
                                is.close();
                            } catch (IOException e) {
                                Logger.m1417d(JPushView.TAG, "", e);
                            }
                        }
                    } catch (Exception e2) {
                        Logger.m1421e(JPushView.TAG, "later view ", e2);
                        if (is != null) {
                            try {
                                is.close();
                            } catch (IOException e3) {
                                Logger.m1417d(JPushView.TAG, "", e3);
                            }
                        }
                    } finally {
                        if (is != null) {
                            try {
                                is.close();
                            } catch (IOException e4) {
                                Logger.m1417d(JPushView.TAG, "", e4);
                            }
                        }
                    }
                }
            }
        }.start();
    }
}
