package com.miteksystems.misnap.barcode.analyzer;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.p000v4.content.LocalBroadcastManager;
import com.manateeworks.BarcodeScanner;
import com.manateeworks.BarcodeScanner.MWResult;
import com.manateeworks.BarcodeScanner.MWResults;
import com.miteksystems.misnap.analyzer.IAnalyzeResponse;
import com.miteksystems.misnap.analyzer.IAnalyzeResponse.ExtraInfo;
import com.miteksystems.misnap.analyzer.IAnalyzer;
import com.miteksystems.misnap.barcode.C4572R;
import com.miteksystems.misnap.events.OnCapturedBarcodeEvent;
import com.miteksystems.misnap.params.MiSnapAPI;
import com.miteksystems.misnap.params.ParameterManager;
import com.miteksystems.misnap.params.SDKConstants;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import p005cn.jpush.android.JPushConstants;
import p314de.greenrobot.event.EventBus;

public class BarcodeAnalyzer implements IAnalyzer {
    public static final int BARCODE_SPEED_FAST = 1;
    public static final int BARCODE_SPEED_MEDIUM = 2;
    public static final int BARCODE_SPEED_SLOW = 3;
    private BroadcastReceiver focusModeChanged = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(SDKConstants.CAMERA_MANAGER_BROADCASTER) && intent.getIntExtra(SDKConstants.CAM_BROADCAST_MESSAGE_ID, 0) == 20015) {
                BarcodeScanner.MWBsetLevel(BarcodeAnalyzer.this.getSpeedMode(1));
            }
        }
    };
    protected WeakReference<Context> mActivityContext;
    protected Context mAppContext;
    protected IAnalyzeResponse mCallback;
    protected boolean mIsAnalyzingFrame;
    protected boolean mIsInitialized;
    protected ParameterManager mParamMgr;
    protected int mPreviewHeight;
    protected int mPreviewWidth;
    protected byte[] mPreviewYuv;

    class FrameAnalyzer extends AsyncTask<Void, Void, MWResult> {
        FrameAnalyzer() {
        }

        /* access modifiers changed from: protected */
        public MWResult doInBackground(Void... params) {
            byte[] rawResult = BarcodeScanner.MWBscanGrayscaleImage(BarcodeAnalyzer.this.mPreviewYuv, BarcodeAnalyzer.this.mPreviewWidth, BarcodeAnalyzer.this.mPreviewHeight);
            if (rawResult != null && BarcodeScanner.MWBgetResultType() == 2) {
                MWResults results = new MWResults(rawResult);
                if (results.count <= 0) {
                    return null;
                }
                MWResult mwResult = results.getResult(0);
                byte[] rawResult2 = mwResult.bytes;
                return mwResult;
            } else if (rawResult == null || BarcodeScanner.MWBgetResultType() != 1) {
                return null;
            } else {
                MWResult mwResult2 = new MWResult();
                mwResult2.bytes = rawResult;
                mwResult2.text = rawResult.toString();
                mwResult2.type = BarcodeScanner.MWBgetLastType();
                mwResult2.bytesLength = rawResult.length;
                return mwResult2;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(MWResult result) {
            String s;
            super.onPostExecute(result);
            if (result == null || result.bytes == null) {
                BarcodeAnalyzer.this.mCallback.onAnalyzeFail(1, null);
                BarcodeAnalyzer.this.mIsAnalyzingFrame = false;
                return;
            }
            byte[] rawResult = result.bytes;
            try {
                s = new String(rawResult, JPushConstants.ENCODING_UTF_8);
            } catch (UnsupportedEncodingException e) {
                s = "";
                for (byte b : rawResult) {
                    s = s + ((char) b);
                }
                e.printStackTrace();
            }
            int bcType = result.type;
            String typeName = "";
            switch (bcType) {
                case 0:
                    typeName = "None";
                    break;
                case 1:
                    typeName = "Datamatrix";
                    break;
                case 2:
                    typeName = "Code 39";
                    break;
                case 3:
                    typeName = "Databar 14";
                    break;
                case 4:
                    typeName = "Databar 14 Stacked";
                    break;
                case 5:
                    typeName = "Databar Limited";
                    break;
                case 6:
                    typeName = "Databar Expanded";
                    break;
                case 7:
                    typeName = "EAN 13";
                    break;
                case 8:
                    typeName = "EAN 8";
                    break;
                case 9:
                    typeName = "UPC A";
                    break;
                case 10:
                    typeName = "UPC E";
                    break;
                case 11:
                    typeName = "Code 128";
                    break;
                case 12:
                    typeName = "PDF417";
                    break;
                case 13:
                    typeName = "QR";
                    break;
                case 14:
                    typeName = "AZTEC";
                    break;
                case 15:
                    typeName = "Code 25 Interleaved";
                    break;
                case 16:
                    typeName = "Code 25 Standard";
                    break;
                case 17:
                    typeName = "Code 93";
                    break;
                case 18:
                    typeName = "Codabar";
                    break;
                case 20:
                    typeName = "Code 128 GS1";
                    break;
                case 21:
                    typeName = "ITF 14";
                    break;
                case 22:
                    typeName = "Code 11";
                    break;
                case 23:
                    typeName = "MSI Plessey";
                    break;
                case 24:
                    typeName = "IATA Code 25";
                    break;
            }
            if (result.isGS1) {
                String typeName2 = typeName + " (GS1)";
            }
            if (bcType >= 0) {
                Intent intent = new Intent();
                intent.putExtra(MiSnapAPI.RESULT_PDF417_DATA, s);
                intent.putExtra(MiSnapAPI.RESULT_CODE, MiSnapAPI.RESULT_SUCCESS_PDF417);
                EventBus.getDefault().post(new OnCapturedBarcodeEvent(intent));
                BarcodeAnalyzer.this.mCallback.onAnalyzeSuccess(0, new ExtraInfo(BarcodeAnalyzer.this.mPreviewYuv));
                BarcodeAnalyzer.this.mIsAnalyzingFrame = false;
            }
        }
    }

    public BarcodeAnalyzer(Context activityContext, ParameterManager paramMgr) {
        this.mActivityContext = new WeakReference<>(activityContext);
        this.mAppContext = activityContext.getApplicationContext();
        this.mParamMgr = paramMgr;
    }

    public boolean init() {
        this.mIsInitialized = false;
        register();
        LocalBroadcastManager.getInstance(this.mAppContext).registerReceiver(this.focusModeChanged, new IntentFilter(SDKConstants.CAMERA_MANAGER_BROADCASTER));
        BarcodeScanner.MWBsetLevel(getSpeedMode(this.mParamMgr.getmFocusMode()));
        BarcodeScanner.MWBsetResultType(2);
        BarcodeScanner.MWBsetMinLength(256, 5);
        BarcodeScanner.MWBsetMinLength(8192, 5);
        BarcodeScanner.MWBsetMinLength(8, 5);
        BarcodeScanner.MWBsetMinLength(1024, 5);
        BarcodeScanner.MWBsetMinLength(4096, 5);
        this.mIsInitialized = true;
        return true;
    }

    public void deinit() {
        this.mIsInitialized = false;
        LocalBroadcastManager.getInstance(this.mAppContext).unregisterReceiver(this.focusModeChanged);
    }

    public void analyze(IAnalyzeResponse callbackToCall, byte[] imageBytes, int previewWidth, int previewHeight, int previewImageType) {
        if (!this.mIsInitialized) {
            callbackToCall.onAnalyzeFail(2, null);
        } else if (this.mIsAnalyzingFrame) {
            callbackToCall.onAnalyzeFail(3, null);
        } else {
            this.mIsAnalyzingFrame = true;
            this.mCallback = callbackToCall;
            this.mPreviewWidth = previewWidth;
            this.mPreviewHeight = previewHeight;
            this.mPreviewYuv = imageBytes;
            new FrameAnalyzer().execute(new Void[0]);
        }
    }

    private void register() {
        BarcodeScanner.MWBregisterSDK(this.mAppContext.getResources().getString(C4572R.string.manatee_sdk_key), (Activity) this.mActivityContext.get());
        int activeBarcodes = this.mParamMgr.getBarCodeTypes();
        BarcodeScanner.MWBsetActiveCodes(activeBarcodes);
        int[] barcodeScanRegion = this.mParamMgr.getBarCodeScanRegion();
        BarcodeScanner.MWBsetScanningRect(activeBarcodes, (float) barcodeScanRegion[0], (float) barcodeScanRegion[1], (float) barcodeScanRegion[2], (float) barcodeScanRegion[3]);
        BarcodeScanner.MWBsetDirection(this.mParamMgr.getBarCodeDirections());
    }

    /* access modifiers changed from: private */
    public int getSpeedMode(int focusMode) {
        switch (focusMode) {
            case 1:
                return 3;
            default:
                return 2;
        }
    }
}
