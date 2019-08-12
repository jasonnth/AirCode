package p005cn.jpush.android.p006ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.appevents.AppEventsConstants;
import java.io.IOException;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.ui.MediaView */
public class MediaView extends RelativeLayout implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener, Callback {
    private static final int VERSION = VERSION.SDK_INT;
    float density;
    String filePath;
    ImageButton ib_close;
    ImageButton ib_play;
    boolean isPlay;
    boolean isPrepared;
    boolean isRun;
    boolean isSetMediaViewCallBack;
    LinearLayout layout;
    LayoutParams layoutParams;
    Context mContext;
    Handler mHandler;
    MediaPlayer mediaPlayer;
    MediaViewCallBack mediaViewCallBack;
    ProgressBar progressBar;
    ProgressBar progressBar2;
    SurfaceHolder surfaceHolder;
    SurfaceView surfaceView;
    TextView tv_msg;
    TextView tv_time1;
    TextView tv_time2;
    int videoHeight;
    int videoWidth;

    /* renamed from: cn.jpush.android.ui.MediaView$MediaViewCallBack */
    public interface MediaViewCallBack {
        void onClick();

        void onClose();

        void onCompletion();

        void onError();
    }

    public MediaView(Context context) {
        this(context, null);
    }

    public MediaView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MediaView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.isPrepared = false;
        this.density = 1.0f;
        this.mHandler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    MediaView.this.tv_time1.setText(MediaView.this.getTimeStr(msg.arg1));
                    MediaView.this.progressBar.setProgress((int) ((((float) msg.arg1) / ((float) msg.arg2)) * 100.0f));
                }
            }
        };
        this.mContext = context;
    }

    public void setMediaViewCallBack(MediaViewCallBack mediaViewCallBack2) {
        this.mediaViewCallBack = mediaViewCallBack2;
        this.isSetMediaViewCallBack = mediaViewCallBack2 != null;
    }

    public void setPlayPath(String arg0) {
        this.filePath = arg0;
    }

    public void initView() {
        this.layoutParams = new LayoutParams(-1, -1);
        this.density = this.mContext.getResources().getDisplayMetrics().density;
        LayoutParams surfaceParams = new LayoutParams(-1, -1);
        surfaceParams.addRule(13);
        this.surfaceView = new SurfaceView(this.mContext);
        addView(this.surfaceView, surfaceParams);
        this.surfaceHolder = this.surfaceView.getHolder();
        this.surfaceHolder.addCallback(this);
        this.surfaceHolder.setType(3);
        this.surfaceView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MediaView.this.stopPlayVideo();
                if (MediaView.this.isSetMediaViewCallBack) {
                    MediaView.this.mediaViewCallBack.onClick();
                }
            }
        });
        LayoutParams infoParams = new LayoutParams(-1, -2);
        infoParams.addRule(10);
        this.tv_msg = new TextView(this.mContext);
        this.tv_msg.setText("点击屏幕跳转到信息页面");
        this.tv_msg.setVisibility(8);
        this.tv_msg.setPadding(20, 5, 20, 5);
        this.tv_msg.setGravity(17);
        this.tv_msg.setBackgroundColor(Color.argb(80, 255, 255, 255));
        this.tv_msg.setTextColor(-1);
        addView(this.tv_msg, infoParams);
        LayoutParams closeParams = new LayoutParams((int) (30.0f * this.density), (int) (30.0f * this.density));
        closeParams.addRule(11);
        this.ib_close = new ImageButton(this.mContext);
        this.ib_close.setPadding(10, 15, 15, 10);
        this.ib_close.setBackgroundColor(0);
        try {
            Bitmap delBitmap = BitmapFactory.decodeStream(this.mContext.getAssets().open("delete"));
            if (delBitmap != null) {
                this.ib_close.setImageBitmap(delBitmap);
            } else {
                this.ib_close.setImageResource(17301533);
            }
        } catch (IOException e) {
            this.ib_close.setImageResource(17301533);
        }
        this.ib_close.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (MediaView.this.isSetMediaViewCallBack) {
                    MediaView.this.mediaViewCallBack.onClose();
                }
                MediaView.this.stopPlayVideo();
            }
        });
        addView(this.ib_close, closeParams);
        LayoutParams params = new LayoutParams(-1, -2);
        params.addRule(12);
        this.layout = new LinearLayout(this.mContext);
        this.layout.setOrientation(0);
        this.layout.setGravity(16);
        this.layout.setBackgroundColor(Color.argb(55, 0, 0, 0));
        addView(this.layout, params);
        LinearLayout.LayoutParams playParams = new LinearLayout.LayoutParams(-2, -2);
        playParams.setMargins(10, 0, 10, 0);
        this.ib_play = new ImageButton(this.mContext);
        this.ib_play.setEnabled(false);
        this.ib_play.setBackgroundColor(0);
        this.ib_play.setImageResource(17301540);
        this.ib_play.setPadding(10, 0, 10, 0);
        this.ib_play.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (MediaView.this.isPlay) {
                    MediaView.this.pauseVideo();
                } else {
                    MediaView.this.startPlayVideo();
                }
            }
        });
        this.layout.addView(this.ib_play, playParams);
        LinearLayout.LayoutParams tv1Params = new LinearLayout.LayoutParams(-2, -2);
        this.tv_time1 = new TextView(this.mContext);
        this.tv_time1.setTextColor(-1);
        this.layout.addView(this.tv_time1, tv1Params);
        LinearLayout.LayoutParams pbParams = new LinearLayout.LayoutParams(-2, (int) (11.0f * this.density));
        pbParams.weight = 1.0f;
        pbParams.setMargins(10, 10, 10, 10);
        this.progressBar = new ProgressBar(this.mContext, null, 16842872);
        this.progressBar.setIndeterminate(false);
        this.progressBar.setBackgroundColor(0);
        this.progressBar.setMinimumHeight(20);
        this.progressBar.setMax(100);
        this.layout.addView(this.progressBar, pbParams);
        LinearLayout.LayoutParams tv2Params = new LinearLayout.LayoutParams(-2, -2);
        tv2Params.setMargins(0, 0, 10, 0);
        this.tv_time2 = new TextView(this.mContext);
        this.tv_time2.setTextColor(-1);
        this.layout.addView(this.tv_time2, tv2Params);
        LayoutParams params2 = new LayoutParams(-2, -2);
        params2.addRule(13, -1);
        this.progressBar2 = new ProgressBar(this.mContext);
        addView(this.progressBar2, params2);
    }

    public void setInfoMsg(boolean flag) {
        if (this.tv_msg != null) {
            this.tv_msg.setVisibility(flag ? 0 : 8);
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            if (!TextUtils.isEmpty(this.filePath)) {
                Logger.m1417d("MediaPlay", "file path is -- " + this.filePath, null);
                initMedia(this.filePath);
                return;
            }
            Logger.m1417d("MediaPlay", "file path is error -- " + this.filePath, null);
            if (this.isSetMediaViewCallBack) {
                this.mediaViewCallBack.onError();
            }
        } catch (Exception e) {
            Logger.m1417d("MediaPlay", " error ", e);
            stopPlayVideo();
            if (this.isSetMediaViewCallBack) {
                this.mediaViewCallBack.onError();
            }
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        stopPlayVideo();
    }

    private void initMedia(String path) throws IllegalStateException, IOException {
        if (this.mediaPlayer == null) {
            this.mediaPlayer = new MediaPlayer();
        } else {
            this.mediaPlayer.reset();
        }
        this.mediaPlayer.setDataSource(path);
        this.mediaPlayer.setDisplay(this.surfaceHolder);
        this.mediaPlayer.setOnCompletionListener(this);
        this.mediaPlayer.setOnBufferingUpdateListener(this);
        this.mediaPlayer.setOnPreparedListener(this);
        this.mediaPlayer.setAudioStreamType(3);
        this.mediaPlayer.setOnErrorListener(this);
        this.mediaPlayer.setOnInfoListener(this);
        this.mediaPlayer.prepareAsync();
        this.ib_play.setImageResource(17301539);
    }

    public void pauseVideo() {
        if (this.mediaPlayer != null && this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.pause();
            this.isPlay = false;
            if (this.ib_play != null) {
                this.ib_play.setImageResource(17301540);
            }
        }
    }

    public void startPlayVideo() {
        if (this.mediaPlayer != null && !this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.start();
            this.isPlay = true;
            this.isRun = true;
            if (this.ib_play != null) {
                this.ib_play.setImageResource(17301539);
            }
        }
    }

    public void stopPlayVideo() {
        this.isRun = false;
        this.isPlay = false;
        if (this.mediaPlayer != null) {
            this.mediaPlayer.stop();
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
    }

    public void setToPosition(int position) {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.pause();
            this.mediaPlayer.seekTo(position);
            this.mediaPlayer.start();
        }
    }

    /* access modifiers changed from: private */
    public String getTimeStr(int arg0) {
        String timeStr;
        String str = "";
        int mm = (arg0 % JPushConstants.ONE_HOUR) / JPushConstants.ONE_MINUTE;
        int ss = ((arg0 % JPushConstants.ONE_HOUR) % JPushConstants.ONE_MINUTE) / 1000;
        if (mm == 0) {
            timeStr = "00";
        } else if (mm <= 0 || mm >= 10) {
            timeStr = "" + mm;
        } else {
            timeStr = AppEventsConstants.EVENT_PARAM_VALUE_NO + mm;
        }
        String timeStr2 = timeStr + ":";
        if (ss == 0) {
            return timeStr2 + "00";
        }
        if (ss <= 0 || ss >= 10) {
            return timeStr2 + "" + ss;
        }
        return timeStr2 + AppEventsConstants.EVENT_PARAM_VALUE_NO + ss;
    }

    public int getMediaPlayPosition() {
        if (this.mediaPlayer == null) {
            return 0;
        }
        return this.mediaPlayer.getCurrentPosition();
    }

    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        if (VERSION > 8) {
            if (what == 701) {
                this.progressBar2.setVisibility(0);
            } else if (what == 702) {
                this.progressBar2.setVisibility(8);
            }
        }
        return false;
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        stopPlayVideo();
        if (this.isSetMediaViewCallBack) {
            this.mediaViewCallBack.onError();
        }
        return true;
    }

    public void onCompletion(MediaPlayer mp) {
        stopPlayVideo();
        if (this.isSetMediaViewCallBack) {
            this.mediaViewCallBack.onCompletion();
        }
    }

    public void onPrepared(MediaPlayer mp) {
        this.isPrepared = true;
        this.ib_play.setEnabled(true);
        this.progressBar2.setVisibility(8);
        this.videoWidth = mp.getVideoWidth();
        this.videoHeight = mp.getVideoHeight();
        this.surfaceHolder.setFixedSize(this.videoWidth, this.videoHeight);
        startPlayVideo();
        final int len = this.mediaPlayer.getDuration();
        this.tv_time2.setText(getTimeStr(len));
        new Thread(new Runnable() {
            public void run() {
                while (MediaView.this.isRun && MediaView.this.mediaPlayer != null) {
                    if (MediaView.this.mediaPlayer.isPlaying()) {
                        try {
                            MediaView.this.mHandler.obtainMessage(1, MediaView.this.mediaPlayer.getCurrentPosition(), len).sendToTarget();
                        } catch (Exception e) {
                            if (MediaView.this.isSetMediaViewCallBack) {
                                MediaView.this.mediaViewCallBack.onError();
                            }
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e2) {
                        Logger.m1417d("MediaPlay", " Thread error  ", e2);
                    }
                }
            }
        }).start();
    }

    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        this.progressBar.setSecondaryProgress(percent);
    }
}
