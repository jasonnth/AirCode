package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View.MeasureSpec;
import com.airbnb.android.core.communitycommitment.CommunityCommitmentManager;
import com.airbnb.android.lib.fragments.GenderSelectionFragment;
import com.airbnb.android.utils.AirbnbConstants;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.internal.zzds;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpo;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@zzme
@TargetApi(14)
public class zzd extends zzj implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener, OnVideoSizeChangedListener, SurfaceTextureListener {
    private static final Map<Integer, String> zzMK = new HashMap();
    private final zzz zzML;
    private final boolean zzMM;
    private int zzMN = 0;
    private int zzMO = 0;
    private MediaPlayer zzMP;
    private Uri zzMQ;
    private int zzMR;
    private int zzMS;
    private int zzMT;
    private int zzMU;
    private int zzMV;
    private zzy zzMW;
    private boolean zzMX;
    private int zzMY;
    /* access modifiers changed from: private */
    public zzi zzMZ;

    static {
        if (VERSION.SDK_INT >= 17) {
            zzMK.put(Integer.valueOf(-1004), "MEDIA_ERROR_IO");
            zzMK.put(Integer.valueOf(-1007), "MEDIA_ERROR_MALFORMED");
            zzMK.put(Integer.valueOf(-1010), "MEDIA_ERROR_UNSUPPORTED");
            zzMK.put(Integer.valueOf(AirbnbConstants.SUPERHERO_COUPON_ID), "MEDIA_ERROR_TIMED_OUT");
            zzMK.put(Integer.valueOf(3), "MEDIA_INFO_VIDEO_RENDERING_START");
        }
        zzMK.put(Integer.valueOf(100), "MEDIA_ERROR_SERVER_DIED");
        zzMK.put(Integer.valueOf(1), "MEDIA_ERROR_UNKNOWN");
        zzMK.put(Integer.valueOf(1), "MEDIA_INFO_UNKNOWN");
        zzMK.put(Integer.valueOf(700), "MEDIA_INFO_VIDEO_TRACK_LAGGING");
        zzMK.put(Integer.valueOf(GenderSelectionFragment.REQUEST_CODE_GENDER), "MEDIA_INFO_BUFFERING_START");
        zzMK.put(Integer.valueOf(CommunityCommitmentManager.REQUEST_CODE_ACCEPT_COMMUNITY_COMMITMENT), "MEDIA_INFO_BUFFERING_END");
        zzMK.put(Integer.valueOf(800), "MEDIA_INFO_BAD_INTERLEAVING");
        zzMK.put(Integer.valueOf(801), "MEDIA_INFO_NOT_SEEKABLE");
        zzMK.put(Integer.valueOf(802), "MEDIA_INFO_METADATA_UPDATE");
        if (VERSION.SDK_INT >= 19) {
            zzMK.put(Integer.valueOf(901), "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
            zzMK.put(Integer.valueOf(902), "MEDIA_INFO_SUBTITLE_TIMED_OUT");
        }
    }

    public zzd(Context context, boolean z, boolean z2, zzz zzz) {
        super(context);
        setSurfaceTextureListener(this);
        this.zzML = zzz;
        this.zzMX = z;
        this.zzMM = z2;
        this.zzML.zza((zzj) this);
    }

    private void zzK(int i) {
        if (i == 3) {
            this.zzML.zzix();
            this.zzNS.zzix();
        } else if (this.zzMN == 3) {
            this.zzML.zziy();
            this.zzNS.zziy();
        }
        this.zzMN = i;
    }

    private void zzL(int i) {
        this.zzMO = i;
    }

    private void zza(float f) {
        if (this.zzMP != null) {
            try {
                this.zzMP.setVolume(f, f);
            } catch (IllegalStateException e) {
            }
        } else {
            zzpk.zzbh("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
        }
    }

    private void zzhA() {
        if (this.zzMM && zzhB() && this.zzMP.getCurrentPosition() > 0 && this.zzMO != 3) {
            zzpk.m1279v("AdMediaPlayerView nudging MediaPlayer");
            zza(0.0f);
            this.zzMP.start();
            int currentPosition = this.zzMP.getCurrentPosition();
            long currentTimeMillis = zzw.zzcS().currentTimeMillis();
            while (zzhB() && this.zzMP.getCurrentPosition() == currentPosition) {
                if (zzw.zzcS().currentTimeMillis() - currentTimeMillis > 250) {
                    break;
                }
            }
            this.zzMP.pause();
            zzhC();
        }
    }

    private boolean zzhB() {
        return (this.zzMP == null || this.zzMN == -1 || this.zzMN == 0 || this.zzMN == 1) ? false : true;
    }

    private void zzhz() {
        SurfaceTexture surfaceTexture;
        zzpk.m1279v("AdMediaPlayerView init MediaPlayer");
        SurfaceTexture surfaceTexture2 = getSurfaceTexture();
        if (this.zzMQ != null && surfaceTexture2 != null) {
            zzx(false);
            try {
                this.zzMP = zzw.zzdd().zzik();
                this.zzMP.setOnBufferingUpdateListener(this);
                this.zzMP.setOnCompletionListener(this);
                this.zzMP.setOnErrorListener(this);
                this.zzMP.setOnInfoListener(this);
                this.zzMP.setOnPreparedListener(this);
                this.zzMP.setOnVideoSizeChangedListener(this);
                this.zzMT = 0;
                if (this.zzMX) {
                    this.zzMW = new zzy(getContext());
                    this.zzMW.zza(surfaceTexture2, getWidth(), getHeight());
                    this.zzMW.start();
                    surfaceTexture = this.zzMW.zzim();
                    if (surfaceTexture == null) {
                        this.zzMW.zzil();
                        this.zzMW = null;
                    }
                    this.zzMP.setDataSource(getContext(), this.zzMQ);
                    this.zzMP.setSurface(zzw.zzde().zza(surfaceTexture));
                    this.zzMP.setAudioStreamType(3);
                    this.zzMP.setScreenOnWhilePlaying(true);
                    this.zzMP.prepareAsync();
                    zzK(1);
                }
                surfaceTexture = surfaceTexture2;
                this.zzMP.setDataSource(getContext(), this.zzMQ);
                this.zzMP.setSurface(zzw.zzde().zza(surfaceTexture));
                this.zzMP.setAudioStreamType(3);
                this.zzMP.setScreenOnWhilePlaying(true);
                this.zzMP.prepareAsync();
                zzK(1);
            } catch (IOException | IllegalArgumentException | IllegalStateException e) {
                String valueOf = String.valueOf(this.zzMQ);
                zzpk.zzc(new StringBuilder(String.valueOf(valueOf).length() + 36).append("Failed to initialize MediaPlayer at ").append(valueOf).toString(), e);
                onError(this.zzMP, 1, 0);
            }
        }
    }

    private void zzx(boolean z) {
        zzpk.m1279v("AdMediaPlayerView release");
        if (this.zzMW != null) {
            this.zzMW.zzil();
            this.zzMW = null;
        }
        if (this.zzMP != null) {
            this.zzMP.reset();
            this.zzMP.release();
            this.zzMP = null;
            zzK(0);
            if (z) {
                this.zzMO = 0;
                zzL(0);
            }
        }
    }

    public int getCurrentPosition() {
        if (zzhB()) {
            return this.zzMP.getCurrentPosition();
        }
        return 0;
    }

    public int getDuration() {
        if (zzhB()) {
            return this.zzMP.getDuration();
        }
        return -1;
    }

    public int getVideoHeight() {
        if (this.zzMP != null) {
            return this.zzMP.getVideoHeight();
        }
        return 0;
    }

    public int getVideoWidth() {
        if (this.zzMP != null) {
            return this.zzMP.getVideoWidth();
        }
        return 0;
    }

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.zzMT = i;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        zzpk.m1279v("AdMediaPlayerView completion");
        zzK(5);
        zzL(5);
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                if (zzd.this.zzMZ != null) {
                    zzd.this.zzMZ.zzhW();
                }
            }
        });
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        final String str = (String) zzMK.get(Integer.valueOf(i));
        final String str2 = (String) zzMK.get(Integer.valueOf(i2));
        zzpk.zzbh(new StringBuilder(String.valueOf(str).length() + 38 + String.valueOf(str2).length()).append("AdMediaPlayerView MediaPlayer error: ").append(str).append(":").append(str2).toString());
        zzK(-1);
        zzL(-1);
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                if (zzd.this.zzMZ != null) {
                    zzd.this.zzMZ.zzl(str, str2);
                }
            }
        });
        return true;
    }

    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        String str = (String) zzMK.get(Integer.valueOf(i));
        String str2 = (String) zzMK.get(Integer.valueOf(i2));
        zzpk.m1279v(new StringBuilder(String.valueOf(str).length() + 37 + String.valueOf(str2).length()).append("AdMediaPlayerView MediaPlayer info: ").append(str).append(":").append(str2).toString());
        return true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(this.zzMR, i);
        int defaultSize2 = getDefaultSize(this.zzMS, i2);
        if (this.zzMR > 0 && this.zzMS > 0 && this.zzMW == null) {
            int mode = MeasureSpec.getMode(i);
            int size = MeasureSpec.getSize(i);
            int mode2 = MeasureSpec.getMode(i2);
            defaultSize2 = MeasureSpec.getSize(i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                if (this.zzMR * defaultSize2 < this.zzMS * size) {
                    defaultSize = (this.zzMR * defaultSize2) / this.zzMS;
                } else if (this.zzMR * defaultSize2 > this.zzMS * size) {
                    defaultSize2 = (this.zzMS * size) / this.zzMR;
                    defaultSize = size;
                } else {
                    defaultSize = size;
                }
            } else if (mode == 1073741824) {
                int i3 = (this.zzMS * size) / this.zzMR;
                if (mode2 != Integer.MIN_VALUE || i3 <= defaultSize2) {
                    defaultSize2 = i3;
                    defaultSize = size;
                } else {
                    defaultSize = size;
                }
            } else if (mode2 == 1073741824) {
                defaultSize = (this.zzMR * defaultSize2) / this.zzMS;
                if (mode == Integer.MIN_VALUE && defaultSize > size) {
                    defaultSize = size;
                }
            } else {
                int i4 = this.zzMR;
                int i5 = this.zzMS;
                if (mode2 != Integer.MIN_VALUE || i5 <= defaultSize2) {
                    defaultSize2 = i5;
                    defaultSize = i4;
                } else {
                    defaultSize = (this.zzMR * defaultSize2) / this.zzMS;
                }
                if (mode == Integer.MIN_VALUE && defaultSize > size) {
                    defaultSize2 = (this.zzMS * size) / this.zzMR;
                    defaultSize = size;
                }
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
        if (this.zzMW != null) {
            this.zzMW.zzj(defaultSize, defaultSize2);
        }
        if (VERSION.SDK_INT == 16) {
            if ((this.zzMU > 0 && this.zzMU != defaultSize) || (this.zzMV > 0 && this.zzMV != defaultSize2)) {
                zzhA();
            }
            this.zzMU = defaultSize;
            this.zzMV = defaultSize2;
        }
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        zzpk.m1279v("AdMediaPlayerView prepared");
        zzK(2);
        this.zzML.zzhU();
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                if (zzd.this.zzMZ != null) {
                    zzd.this.zzMZ.zzhU();
                }
            }
        });
        this.zzMR = mediaPlayer.getVideoWidth();
        this.zzMS = mediaPlayer.getVideoHeight();
        if (this.zzMY != 0) {
            seekTo(this.zzMY);
        }
        zzhA();
        int i = this.zzMR;
        zzpk.zzbg("AdMediaPlayerView stream dimensions: " + i + " x " + this.zzMS);
        if (this.zzMO == 3) {
            play();
        }
        zzhC();
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        zzpk.m1279v("AdMediaPlayerView surface created");
        zzhz();
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                if (zzd.this.zzMZ != null) {
                    zzd.this.zzMZ.zzhT();
                }
            }
        });
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        zzpk.m1279v("AdMediaPlayerView surface destroyed");
        if (this.zzMP != null && this.zzMY == 0) {
            this.zzMY = this.zzMP.getCurrentPosition();
        }
        if (this.zzMW != null) {
            this.zzMW.zzil();
        }
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                if (zzd.this.zzMZ != null) {
                    zzd.this.zzMZ.onPaused();
                    zzd.this.zzMZ.zzhX();
                }
            }
        });
        zzx(true);
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, final int i, final int i2) {
        boolean z = true;
        zzpk.m1279v("AdMediaPlayerView surface changed");
        boolean z2 = this.zzMO == 3;
        if (!(this.zzMR == i && this.zzMS == i2)) {
            z = false;
        }
        if (this.zzMP != null && z2 && z) {
            if (this.zzMY != 0) {
                seekTo(this.zzMY);
            }
            play();
        }
        if (this.zzMW != null) {
            this.zzMW.zzj(i, i2);
        }
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                if (zzd.this.zzMZ != null) {
                    zzd.this.zzMZ.zzg(i, i2);
                }
            }
        });
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zzML.zzb(this);
        this.zzNR.zza(surfaceTexture, this.zzMZ);
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        zzpk.m1279v("AdMediaPlayerView size changed: " + i + " x " + i2);
        this.zzMR = mediaPlayer.getVideoWidth();
        this.zzMS = mediaPlayer.getVideoHeight();
        if (this.zzMR != 0 && this.zzMS != 0) {
            requestLayout();
        }
    }

    public void pause() {
        zzpk.m1279v("AdMediaPlayerView pause");
        if (zzhB() && this.zzMP.isPlaying()) {
            this.zzMP.pause();
            zzK(4);
            zzpo.zzXC.post(new Runnable() {
                public void run() {
                    if (zzd.this.zzMZ != null) {
                        zzd.this.zzMZ.onPaused();
                    }
                }
            });
        }
        zzL(4);
    }

    public void play() {
        zzpk.m1279v("AdMediaPlayerView play");
        if (zzhB()) {
            this.zzMP.start();
            zzK(3);
            this.zzNR.zzhV();
            zzpo.zzXC.post(new Runnable() {
                public void run() {
                    if (zzd.this.zzMZ != null) {
                        zzd.this.zzMZ.zzhV();
                    }
                }
            });
        }
        zzL(3);
    }

    public void seekTo(int i) {
        zzpk.m1279v("AdMediaPlayerView seek " + i);
        if (zzhB()) {
            this.zzMP.seekTo(i);
            this.zzMY = 0;
            return;
        }
        this.zzMY = i;
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        zzds zze = zzds.zze(uri);
        if (zze != null) {
            uri = Uri.parse(zze.url);
        }
        this.zzMQ = uri;
        this.zzMY = 0;
        zzhz();
        requestLayout();
        invalidate();
    }

    public void stop() {
        zzpk.m1279v("AdMediaPlayerView stop");
        if (this.zzMP != null) {
            this.zzMP.stop();
            this.zzMP.release();
            this.zzMP = null;
            zzK(0);
            zzL(0);
        }
        this.zzML.onStop();
    }

    public String toString() {
        String valueOf = String.valueOf(getClass().getName());
        String valueOf2 = String.valueOf(Integer.toHexString(hashCode()));
        return new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length()).append(valueOf).append("@").append(valueOf2).toString();
    }

    public void zza(float f, float f2) {
        if (this.zzMW != null) {
            this.zzMW.zzb(f, f2);
        }
    }

    public void zza(zzi zzi) {
        this.zzMZ = zzi;
    }

    public void zzhC() {
        zza(this.zzNS.zziA());
    }

    public String zzhy() {
        String str = "MediaPlayer";
        String valueOf = String.valueOf(this.zzMX ? " spherical" : "");
        return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
    }
}
