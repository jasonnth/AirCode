package jumio.p317nv.core;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.Html;
import android.util.Pair;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jumio.commons.log.Log;
import com.jumio.commons.utils.ScreenUtil;
import com.jumio.commons.view.SVGImageView;
import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.data.document.ScanSide;
import com.jumio.core.overlay.Overlay;
import com.jumio.p311nv.C4430R;
import com.jumio.p311nv.data.NVStrings;
import com.jumio.p311nv.gui.PieProgressView;
import com.jumio.sdk.extraction.ExtractionClient.ExtractionUpdate;
import java.util.Stack;
import org.spongycastle.crypto.tls.CipherSuite;

/* renamed from: jumio.nv.core.ae */
/* compiled from: LivenessOverlay */
public class C4894ae extends FrameLayout implements Overlay {

    /* renamed from: a */
    public static final Stack<String> f4729a = new Stack<String>() {
        {
            add("M299.3,586.1c0,0,1.4-4.2,3.8-11.7c0.6-1.8,1.3-3.9,2-6.2c0.7-2.3,1.7-4.6,2.5-7.2c1.8-5.1,3.8-10.8,6.5-17c1.3-3.1,2.5-6.3,3.9-9.7c1.3-3.4,3-6.8,4.5-10.3c1.5-3.5,3.1-7.2,4.8-10.7c1.7-3.7,3.5-7.3,5.4-11.1c1.8-3.8,3.8-7.5,5.8-11.3c1-2,2-3.8,3.1-5.8l1.5-3l1.7-2.8c2.1-3.8,4.4-7.6,6.6-11.6c2.3-3.8,4.6-7.6,7-11.4l3.5-5.6c1.3-1.8,2.5-3.7,3.7-5.5c1.3-1.8,2.5-3.7,3.7-5.5c1.3-1.8,2.4-3.7,3.8-5.5c2.5-3.5,5.1-7,7.6-10.6c2.5-3.5,5.2-6.8,7.7-10c1.3-1.7,2.5-3.2,3.8-4.9c1.3-1.5,2.7-3.1,3.9-4.6c2.7-3.1,5.2-6.1,7.6-9c2.5-2.8,5.1-5.5,7.6-8.2c1.3-1.3,2.4-2.7,3.7-3.9c1.3-1.3,2.4-2.4,3.7-3.7c2.4-2.4,4.6-4.6,6.8-6.8c2.1-2.1,4.4-4.1,6.3-5.9c4.1-3.7,7.5-6.9,10.4-9.4c5.9-5.1,9.3-7.9,9.3-7.9s-3.4,3-9.2,8c-3,2.5-6.3,5.8-10.3,9.6c-2,1.8-4.1,3.8-6.2,6.1c-2.1,2.1-4.4,4.5-6.6,6.9c-1.1,1.1-2.4,2.4-3.5,3.7c-1.1,1.3-2.4,2.5-3.5,3.9c-2.4,2.7-4.9,5.5-7.5,8.3c-2.4,3-4.9,5.9-7.6,9c-1.3,1.5-2.5,3.1-3.9,4.6c-1.3,1.5-2.5,3.2-3.8,4.9c-2.5,3.2-5.2,6.6-7.7,10c-2.5,3.5-5.1,7-7.6,10.6c-1.3,1.7-2.5,3.7-3.8,5.5c-1.3,1.8-2.4,3.7-3.7,5.5c-1.3,1.8-2.4,3.7-3.7,5.5l-3.5,5.6c-2.4,3.8-4.8,7.5-7,11.4c-2.3,3.8-4.4,7.6-6.6,11.4l-1.7,2.8l-1.5,2.8c-1,2-2,3.8-3.1,5.8c-2,3.8-4.1,7.5-5.8,11.3c-1.8,3.8-3.7,7.5-5.5,11c-1.8,3.7-3.4,7.2-4.9,10.7c-1.5,3.5-3.2,6.9-4.5,10.1c-1.4,3.4-2.7,6.5-3.9,9.6c-2.7,6.2-4.6,11.8-6.8,16.9c-1,2.5-2,4.9-2.7,7c-0.8,2.3-1.5,4.2-2.1,6.1C300.7,581.9,299.3,586.1,299.3,586.1z");
            add("M247.9,18c0,0,1.4,0.3,4.2,0.7c1.4,0.3,3.1,0.4,5.1,0.8c2,0.4,4.4,1,7,1.5c2.7,0.6,5.5,1.4,8.7,2.3c3.2,0.8,6.6,2.1,10.4,3.4c1.8,0.6,3.8,1.4,5.6,2.3c2,0.8,3.9,1.7,6.1,2.5c4.1,2,8.5,3.9,12.8,6.6c8.9,4.9,18.3,11.1,27.7,19c9.4,7.9,19,17.2,28.2,28c9.2,10.8,18,23.1,26.1,36.9c4.1,6.9,7.9,14.1,11.6,21.6c1,1.8,1.8,3.8,2.7,5.8c0.8,2,1.7,3.9,2.5,5.8c1.7,3.9,3.2,8,4.9,12c12.5,32.7,20.6,70,22.7,110c0.1,2.5,0.3,5.1,0.3,7.5c0,2.5,0.1,5.1,0.1,7.6c0,0.7,0,1.1,0,2v1.8v3.8c0,2.5-0.1,5.1-0.1,7.6c-0.3,5.1-0.6,10.3-0.8,15.5c-0.6,5.2-0.8,10.4-1.5,15.6c-2.4,20.8-6.6,42.1-13,63.1c-0.7,2.7-1.7,5.2-2.5,7.9c-0.8,2.5-1.7,5.2-2.7,7.7c-2,5.2-3.8,10.4-6.1,15.5c-4.2,10.3-9.3,20.3-14.6,30.3c-2.8,4.9-5.6,9.9-8.7,14.5c-3,4.8-6.3,9.4-9.6,14.1c-6.9,9.2-14.2,18-22.3,26.3c-8.2,8.2-16.9,15.9-26.2,22.8c-9.4,6.9-19.4,13-30.1,18c-10.6,5.1-21.8,9-33.2,11.7c-11.6,2.7-23.2,4.1-35.1,4.1c-11.7,0-23.7-1.3-35.2-3.8c-2.8-0.7-5.8-1.3-8.6-2.1l-8.5-2.7c-5.5-2-11-4.1-16.3-6.6l-3.9-1.8l-3.9-2.1c-2.5-1.4-5.2-2.7-7.6-4.2c-2.5-1.5-5.1-3.1-7.5-4.6l-7.2-4.9c-9.4-6.9-18.2-14.5-26.3-22.7c-8-8.3-15.5-17-22.4-26.2c-6.8-9.3-13-18.7-18.5-28.6c-1.4-2.4-2.7-4.9-3.9-7.5c-1.3-2.5-2.7-4.9-3.8-7.5c-2.4-5.1-4.8-10.1-6.9-15.2c-4.5-10.1-8-20.7-11.4-31c-6.3-21-10.7-42.1-13.2-63c-0.1-1.3-0.4-2.5-0.6-3.9l-0.4-3.9c-0.3-2.5-0.4-5.2-0.7-7.7c-0.4-5.2-0.6-10.3-1-15.5c-0.1-5.2-0.1-10.1-0.3-15.4c0.1-5.1,0.1-10,0.4-14.9c2-40.1,9.9-77.5,22.3-110.2c12.3-32.7,29.2-60.4,47.3-82.3c9.2-10.8,18.6-20.3,27.9-28.2c9.3-7.9,18.7-14.2,27.6-19.3c4.4-2.7,8.7-4.6,12.7-6.8c2-1,4.1-1.8,6.1-2.7c2-0.8,3.8-1.7,5.6-2.3c3.7-1.3,7-2.7,10.3-3.5c3.2-1,6.1-1.8,8.7-2.4c2.7-0.6,4.9-1.1,7-1.5c2-0.3,3.8-0.6,5.1-0.8c2.8-0.4,4.2-0.7,4.2-0.7v0.4c0,0-1.4,0.3-4.2,0.7c-1.4,0.3-3.1,0.6-5.1,1c-2,0.4-4.4,1-6.9,1.7c-2.7,0.6-5.5,1.5-8.6,2.5c-3.2,0.8-6.5,2.3-10.1,3.7c-1.8,0.7-3.7,1.5-5.6,2.4c-2,0.8-3.9,1.7-5.9,2.7c-3.9,2.1-8.3,4.2-12.5,6.9c-8.6,5.2-17.9,11.6-27,19.4s-18.5,17.3-27.3,28.2c-17.7,21.7-33.9,49.4-45.9,81.8s-19.6,69.4-21.1,109c-1.5,39.6,3,81.6,15.6,122.8c3.4,10.3,6.9,20.6,11.3,30.6c2,5.1,4.5,10,6.8,15.1c1.1,2.5,2.5,4.9,3.8,7.3c1.3,2.4,2.5,4.9,3.9,7.3c1.4,2.4,2.8,4.8,4.2,7.2c1.4,2.4,3,4.6,4.5,7l2.3,3.5l2.4,3.4c1.5,2.3,3.2,4.6,4.8,6.9c6.8,9,14.1,17.6,21.8,25.8c8,8,16.6,15.5,25.8,22.3l7,4.9c2.4,1.5,4.8,3,7.3,4.5c2.4,1.5,4.9,2.8,7.5,4.2l3.8,2l3.9,1.8c20.7,9.9,43.7,14.9,66.6,14.9c11.6,0,23-1.5,34.1-4.1c11.1-2.7,22.1-6.5,32.4-11.4c10.3-4.9,20.1-11,29.3-17.6c9.2-6.9,17.6-14.4,25.6-22.5c7.9-8.2,15.1-16.9,21.7-25.9c3.2-4.6,6.5-9.2,9.4-13.9c3.1-4.6,5.8-9.6,8.6-14.4c5.2-9.7,10.3-19.6,14.4-29.7c2.3-5.1,4.1-10.1,5.9-15.2c1-2.5,1.7-5.2,2.7-7.7c0.8-2.5,1.7-5.1,2.5-7.7c6.2-20.7,10.4-41.6,13-62.1s3.1-41,2.3-60.7c-1.7-39.6-9.4-76.6-21.6-108.9c-12.1-32.4-28.3-60-46.2-81.6c-17.9-21.7-37.2-37.3-54.5-47.3c-4.2-2.7-8.6-4.6-12.5-6.8c-2-1-4.1-1.8-5.9-2.7c-2-0.8-3.8-1.7-5.6-2.4c-3.7-1.4-7-2.7-10.3-3.5c-3.2-1-6.1-2-8.7-2.4c-2.7-0.6-4.9-1.1-6.9-1.7c-2-0.3-3.8-0.7-5.1-0.8c-2.8-0.4-4.2-0.7-4.2-0.7L247.9,18z");
            add("M153.1,586.1c0,0-1.4-4.2-4.1-11.6c-0.7-1.8-1.4-3.9-2.1-6.1c-0.8-2.3-1.7-4.6-2.7-7.2c-2-5.1-4.1-10.8-6.8-17c-1.3-3.1-2.7-6.3-4.1-9.7s-3-6.8-4.5-10.3c-1.5-3.5-3.2-7-4.9-10.7c-1.8-3.7-3.7-7.3-5.5-11.1c-1.8-3.8-3.8-7.5-5.9-11.3c-1-1.8-2-3.8-3.1-5.8c-1-2-2.1-3.8-3.2-5.8c-2.3-3.8-4.4-7.6-6.6-11.6c-2.3-3.8-4.6-7.6-7-11.4L89,451c-1.3-1.8-2.5-3.7-3.7-5.5c-1.3-1.8-2.5-3.7-3.7-5.5c-1.3-1.8-2.4-3.7-3.8-5.5c-2.5-3.5-5.1-7-7.6-10.6c-2.5-3.5-5.2-6.8-7.7-10.1c-1.3-1.7-2.5-3.2-3.8-4.9c-1.3-1.5-2.7-3.1-3.9-4.6c-2.5-3.1-5.1-6.1-7.6-9c-2.5-2.8-5.1-5.6-7.5-8.3c-1.3-1.4-2.4-2.7-3.5-3.9c-1.3-1.3-2.4-2.5-3.5-3.7c-2.4-2.4-4.6-4.6-6.8-6.9s-4.2-4.1-6.2-6.1c-3.9-3.8-7.3-7-10.4-9.6c-5.9-5.2-9.3-8-9.3-8s3.4,2.8,9.4,7.9c3.1,2.4,6.5,5.8,10.6,9.4c2,1.8,4.2,3.8,6.3,5.9s4.5,4.4,6.8,6.8c1.1,1.1,2.4,2.4,3.7,3.7c1.1,1.3,2.4,2.5,3.7,3.9c2.4,2.7,5.1,5.4,7.6,8.2c2.5,3,5.1,5.9,7.7,9c1.3,1.5,2.7,3.1,3.9,4.6c1.3,1.7,2.5,3.2,3.8,4.9c2.5,3.4,5.4,6.6,7.9,10.1c2.5,3.5,5.1,7,7.7,10.6c1.3,1.8,2.5,3.7,3.8,5.5c1.3,1.8,2.5,3.7,3.8,5.5c1.3,1.8,2.5,3.7,3.8,5.5l3.5,5.6c2.4,3.8,4.8,7.6,7,11.4c2.3,3.8,4.4,7.7,6.6,11.6c1.1,1.8,2.1,3.8,3.2,5.8c1,2,2,3.8,3.1,5.8c2,3.8,4.1,7.6,5.9,11.4c1.8,3.8,3.7,7.5,5.4,11.1c1.7,3.7,3.2,7.3,4.8,10.8s3.1,6.9,4.5,10.3c1.4,3.4,2.7,6.6,3.9,9.7c2.7,6.2,4.6,12,6.5,17.2c1,2.5,1.8,4.9,2.5,7.2c0.7,2.3,1.4,4.4,2,6.2C151.7,581.8,153.1,586.1,153.1,586.1z");
            add("M15.6,207.2c0,0,2.3-3.8,6.2-10.6c2-3.4,4.5-7.3,7.3-12c2.8-4.6,6.1-9.9,9.7-15.4c3.5-5.6,7.6-11.7,11.8-17.9c4.4-6.3,9-12.8,14.1-19.4c5.1-6.6,10.6-13.4,16.3-19.9c5.9-6.5,12.1-13,18.6-19c6.6-6.1,13.5-11.6,20.7-16.8c7-5.2,14.5-9.7,21.7-13.8c7.3-4.1,14.6-7.5,21.7-10.6c3.5-1.4,6.9-3,10.4-4.1c1.7-0.6,3.4-1.3,4.9-1.8c1.7-0.6,3.2-1.1,4.8-1.5c3.1-1.1,6.2-2,9.2-2.8c1.4-0.4,2.8-0.8,4.2-1.3c1.4-0.4,2.8-0.7,4.1-1c5.2-1.4,9.9-2.4,13.7-3.2c3.8-0.8,6.8-1.4,8.9-1.8c2.1-0.4,3.1-0.6,3.1-0.6s-1.1,0.3-3.1,0.7c-2,0.4-5.1,1.1-8.7,2.1c-3.8,1-8.3,2.1-13.5,3.7c-1.3,0.4-2.7,0.7-4.1,1.1s-2.8,0.8-4.2,1.4c-3,0.8-5.9,1.8-9,3c-1.5,0.6-3.1,1.1-4.8,1.7c-1.5,0.6-3.2,1.3-4.9,2c-3.4,1.3-6.8,2.8-10.3,4.2c-6.9,3.1-14.2,6.6-21.4,10.7c-7.2,4.1-14.5,8.6-21.6,13.8c-7,5.1-13.8,10.7-20.4,16.8c-6.5,5.9-12.7,12.3-18.6,18.7s-11.3,13.1-16.3,19.6c-10.1,13.1-19,25.9-26.5,36.9c-1.8,2.8-3.7,5.5-5.2,8c-1.7,2.5-3.2,4.9-4.8,7.2c-3,4.5-5.5,8.5-7.6,11.8C17.9,203.4,15.6,207.2,15.6,207.2z");
            add("M437.7,207.2c0,0-2.4-3.8-6.6-10.3c-2.1-3.2-4.6-7.2-7.6-11.8c-1.5-2.3-3.1-4.6-4.8-7.2c-1.7-2.5-3.4-5.2-5.2-8c-7.3-11.1-16.2-23.8-26.5-36.9c-5.1-6.5-10.6-13.2-16.3-19.6c-5.9-6.5-12-12.8-18.6-18.7c-6.5-5.9-13.4-11.6-20.4-16.8c-7-5.2-14.2-9.7-21.6-13.8c-7.2-4.1-14.4-7.6-21.4-10.7c-3.5-1.5-6.9-3-10.3-4.2c-1.7-0.7-3.2-1.3-4.9-2c-1.7-0.6-3.2-1.1-4.8-1.7c-3.1-1.1-6.2-2.1-9-3c-1.4-0.4-2.8-0.8-4.2-1.4c-1.4-0.4-2.7-0.8-4.1-1.1c-5.2-1.5-9.7-2.7-13.5-3.7c-3.8-1-6.8-1.5-8.7-2.1c-2-0.4-3.1-0.7-3.1-0.7s1.1,0.1,3.1,0.6c2,0.4,5.1,1,8.9,1.8c3.8,0.8,8.5,1.8,13.7,3.2c1.3,0.3,2.7,0.7,4.1,1c1.4,0.4,2.8,0.8,4.2,1.3c3,0.8,5.9,1.7,9.2,2.8c1.5,0.6,3.2,1.1,4.8,1.5c1.7,0.6,3.2,1.3,4.9,1.8c3.4,1.3,6.8,2.7,10.4,4.1c7,3,14.4,6.3,21.7,10.6c7.3,4.1,14.6,8.6,21.7,13.8c7.2,5.1,14.1,10.7,20.7,16.8c6.6,6.1,12.8,12.4,18.6,19c5.9,6.5,11.3,13.2,16.3,19.9c5.1,6.6,9.7,13.2,14.1,19.4c4.4,6.3,8.3,12.3,11.8,17.9c3.7,5.6,6.8,10.8,9.7,15.4c2.8,4.6,5.2,8.6,7.3,12C435.4,203.4,437.7,207.2,437.7,207.2z");
            add("M371.9,281.3c0,0-0.6,0.6-1.7,1.4c-1.1,1-2.8,2.3-5.1,3.8c-2.3,1.5-5.1,3.2-8.5,4.9c-3.4,1.7-7.3,3.5-11.8,4.9c-4.5,1.4-9.4,2.7-14.9,3.1c-5.4,0.6-11.3,0.4-17-0.8c-5.9-1.1-11.7-3.5-17.2-6.6c-1.4-0.8-2.7-1.7-3.9-2.5c-1.3-1-2.5-1.8-3.8-2.8l-1.8-1.5c-0.6-0.4-1.3-1.1-2-2c-0.6-0.8-1-1.7-1.4-2.5c-0.3-1-0.6-1.7-0.6-3.1c0.1-0.7,0.1-1.1,0.3-1.5c0.1-0.4,0.3-0.8,0.6-1.3c0.4-0.8,0.8-1.7,1.4-2.4c0.3-0.4,0.6-0.7,1-1.1l0.8-0.8c0.6-0.6,1.1-1,1.8-1.5c2.4-2.1,4.9-3.9,7.6-5.6c5.4-3.4,11.3-5.6,17.2-6.8c5.9-1.3,11.7-1.3,17.2-0.7c5.4,0.6,10.4,1.8,14.8,3.4c8.9,3.1,15.6,7.2,20,10.3c2.3,1.5,3.9,2.8,5.1,3.8c1.1,1,1.7,1.4,1.7,1.4s-2.5-1.8-7.2-4.5c-4.6-2.7-11.6-6.1-20.3-8.6c-4.4-1.3-9.3-2.3-14.4-2.7c-5.2-0.3-10.6,0-16.1,1.1c-5.5,1.3-10.8,3.5-15.8,6.6c-2.5,1.5-4.9,3.4-7,5.2c-0.6,0.4-1.1,1-1.7,1.5l-0.8,0.7c-0.1,0.1-0.4,0.4-0.6,0.7c-0.4,0.4-0.7,1-1,1.5c-0.1,0.3-0.3,0.6-0.3,0.8c-0.1,0.3-0.1,0.7-0.1,0.7c0,0.1,0.1,0.8,0.3,1.4s0.4,1.1,0.8,1.5c0.3,0.4,0.7,0.8,1.3,1.3l1.7,1.4c1.1,1,2.4,1.8,3.5,2.7c1.3,0.8,2.4,1.7,3.7,2.4c5.1,3.1,10.4,5.4,15.9,6.5c5.5,1.3,11,1.7,16.1,1.3c5.2-0.3,10-1.1,14.5-2.4c4.4-1.3,8.3-2.5,11.8-4.1c3.4-1.5,6.3-3,8.7-4.2c2.4-1.3,4.1-2.4,5.4-3.2C371.3,281.9,371.9,281.3,371.9,281.3z");
            add("M86.9,281.3c0,0,0.6,0.4,1.8,1.3c1.1,0.8,3,2,5.4,3.2c2.4,1.3,5.2,2.8,8.7,4.2c3.4,1.5,7.3,2.8,11.8,4.1c4.4,1.3,9.3,2.1,14.5,2.4c5.2,0.3,10.7-0.1,16.1-1.3c5.5-1.3,10.8-3.5,15.9-6.5c1.3-0.7,2.5-1.5,3.7-2.4c1.1-0.8,2.4-1.7,3.5-2.7l1.7-1.4c0.6-0.6,1.1-0.8,1.3-1.3c0.3-0.4,0.6-1,0.8-1.5c0.1-0.6,0.3-1.3,0.3-1.4c0,0-0.1-0.4-0.1-0.7c-0.1-0.3-0.1-0.6-0.3-0.8c-0.3-0.6-0.6-1.1-1-1.5c-0.1-0.3-0.4-0.4-0.6-0.7l-0.8-0.7c-0.6-0.6-1.1-1-1.7-1.5c-2.3-2-4.6-3.7-7-5.2c-4.9-3.1-10.3-5.4-15.8-6.6c-5.5-1.3-10.8-1.5-16.1-1.1c-5.2,0.4-10,1.4-14.4,2.7c-8.7,2.5-15.6,6.1-20.3,8.6c-4.6,2.7-7.2,4.5-7.2,4.5s0.6-0.6,1.7-1.4c1.1-1,2.8-2.3,5.1-3.8c4.5-3.1,11.1-7.2,20-10.3c4.4-1.5,9.4-2.8,14.8-3.4c5.4-0.6,11.3-0.6,17.2,0.7c5.9,1.1,11.8,3.4,17.2,6.8c2.7,1.7,5.2,3.5,7.6,5.6c0.6,0.6,1.3,1,1.8,1.5l0.8,0.8c0.4,0.3,0.7,0.7,1,1.1c0.6,0.7,1.1,1.5,1.4,2.4c0.1,0.4,0.4,0.8,0.6,1.3c0.1,0.4,0.3,0.8,0.3,1.5c0.1,1.4-0.3,2.3-0.6,3.1c-0.3,0.8-0.8,1.7-1.4,2.5c-0.7,0.8-1.4,1.4-2,2l-1.8,1.5c-1.3,1-2.5,2-3.8,2.8c-1.3,0.8-2.7,1.7-3.9,2.5c-5.5,3.1-11.3,5.5-17.2,6.6c-5.9,1.1-11.7,1.4-17,0.8c-5.4-0.4-10.4-1.7-14.9-3.1c-4.5-1.4-8.5-3.2-11.8-4.9c-3.4-1.7-6.2-3.5-8.5-4.9c-2.3-1.5-3.9-2.8-5.1-3.8C87.5,281.9,86.9,281.3,86.9,281.3z");
            add("M186.5,248.2c0,0-0.4-0.1-1.3-0.7c-0.8-0.4-2.1-1.1-3.7-2c-1.5-0.8-3.4-2-5.5-3.2c-2.1-1.1-4.6-2.5-7.3-3.7c-0.7-0.3-1.4-0.6-2.1-1c-0.7-0.3-1.4-0.6-2.3-0.8c-1.5-0.6-3.1-1.3-4.8-1.7c-0.8-0.3-1.7-0.6-2.5-0.8c-0.8-0.3-1.7-0.4-2.5-0.7c-0.8-0.3-1.7-0.4-2.7-0.7c-0.8-0.1-1.8-0.4-2.7-0.6c-1.8-0.4-3.8-0.6-5.6-1c-2-0.1-3.8-0.4-5.8-0.6c-3.9-0.3-8-0.1-12,0.1c-3.9,0.4-8,0.8-11.8,1.8c-3.8,0.8-7.6,1.8-11.3,3.1c-7.2,2.4-13.7,5.4-18.9,8.3c-5.4,3-9.6,5.6-12.5,7.6c-3,2-4.5,3.2-4.5,3.2s1.5-1.3,4.4-3.4c2.8-2.1,7-5.1,12.3-8c5.2-3,11.7-6.2,18.9-8.7c3.5-1.3,7.3-2.4,11.3-3.4s7.9-1.5,12-2c4.1-0.4,8.2-0.6,12.1-0.3c2,0.1,3.9,0.3,5.9,0.4c2,0.3,3.9,0.4,5.8,0.8c1,0.1,1.8,0.4,2.8,0.6c1,0.1,1.8,0.4,2.7,0.7c0.8,0.3,1.8,0.4,2.7,0.7c0.8,0.3,1.7,0.6,2.5,0.8c1.7,0.6,3.2,1.1,4.8,1.8c0.7,0.3,1.5,0.6,2.3,0.8c0.7,0.3,1.4,0.7,2.1,1c2.8,1.3,5.2,2.7,7.5,3.8c2.1,1.3,3.9,2.4,5.5,3.4c1.4,1.1,2.5,2,3.2,2.7C186.1,247.9,186.5,248.2,186.5,248.2z");
            add("M272.1,248.2c0,0,0.4-0.4,1.1-1c0.7-0.7,1.8-1.5,3.2-2.5s3.4-2.1,5.5-3.4c2.3-1.1,4.6-2.5,7.5-3.8c0.7-0.3,1.4-0.7,2.1-1s1.5-0.6,2.3-0.8c0.7-0.3,1.5-0.6,2.4-0.8c0.8-0.3,1.7-0.6,2.4-0.8c0.8-0.3,1.7-0.6,2.5-0.8c0.8-0.3,1.7-0.4,2.7-0.7c1.8-0.6,3.7-0.8,5.5-1.1c1.8-0.4,3.8-0.6,5.8-0.8c2-0.1,3.9-0.4,5.9-0.4c3.9-0.1,8,0,12.1,0.3c4.1,0.4,8,1.1,12,2c3.9,1,7.7,2,11.3,3.2c7.2,2.5,13.7,5.6,18.9,8.6c2.7,1.4,5.1,3,7,4.2c2.1,1.4,3.8,2.5,5.2,3.7c2.8,2.1,4.4,3.4,4.4,3.4s-1.5-1.3-4.5-3.2c-3-2-7.2-4.6-12.5-7.6c-5.4-2.8-11.8-5.8-18.9-8.2c-3.5-1.3-7.3-2.1-11.3-3c-3.8-0.8-7.9-1.4-11.8-1.7c-3.9-0.3-8-0.4-12-0.1c-2,0-3.9,0.3-5.8,0.4c-2,0.3-3.8,0.4-5.6,0.8c-1.8,0.4-3.7,0.7-5.4,1.3c-0.8,0.3-1.7,0.4-2.5,0.7c-0.8,0.3-1.7,0.6-2.5,0.8c-0.8,0.3-1.7,0.6-2.4,0.7c-0.8,0.3-1.5,0.6-2.3,0.8c-0.7,0.3-1.5,0.6-2.3,0.8c-0.7,0.3-1.4,0.6-2.1,1c-2.8,1.1-5.2,2.5-7.3,3.7c-2.1,1.3-3.9,2.3-5.5,3.1c-1.5,1-2.7,1.5-3.7,2C272.6,248.1,272.1,248.2,272.1,248.2z");
            add("M161,459.6c0,0,0.6,0.1,1.4,0.4c1,0.3,2.4,0.6,4.1,1c1.8,0.4,3.9,0.8,6.5,1.4c2.5,0.6,5.4,1,8.5,1.4c1.5,0.3,3.1,0.4,4.8,0.7c1.7,0.1,3.4,0.6,5.2,0.7c3.5,0.3,7.3,0.8,11.1,1.1c2,0.1,3.9,0.3,5.9,0.4c2,0.1,4.1,0.1,6.1,0.3c4.1,0.3,8.3,0.1,12.4,0.1c2.1,0,4.1,0,6.2-0.1c2.1,0,4.1-0.1,6.2-0.1c4.1-0.3,8-0.6,12-0.8c2-0.1,3.8-0.4,5.8-0.6c1.8-0.1,3.7-0.4,5.5-0.6c1.8-0.1,3.5-0.4,5.2-0.7c1.7-0.3,3.2-0.6,4.8-0.7c1.5-0.3,3-0.4,4.4-0.7c0.7-0.1,1.4-0.1,2.1-0.3s1.3-0.3,2-0.4c2.5-0.6,4.6-1,6.5-1.3c3.5-0.7,5.6-1.1,5.6-1.1l0,0c0.3,0,0.6,0.1,0.6,0.4c0,0.3-0.1,0.4-0.3,0.6c0,0-2,0.7-5.5,1.7c-1.8,0.6-3.9,1.1-6.3,1.8c-2.4,0.8-5.4,1.3-8.3,2c-3.1,0.7-6.5,1.5-10,2c-3.5,0.6-7.3,1.3-11.3,1.7c-2,0.1-3.9,0.4-6.1,0.6c-2,0.1-4.1,0.4-6.2,0.6c-2.1,0.1-4.2,0.1-6.3,0.3c-2.1,0.1-4.2,0.3-6.3,0.1c-4.2-0.1-8.5,0-12.5-0.3c-2.1-0.1-4.1-0.3-6.2-0.4c-2-0.1-4.1-0.4-6.1-0.6c-3.9-0.3-7.7-1-11.3-1.5c-3.5-0.6-6.9-1.3-10-2c-1.5-0.4-3-0.7-4.4-1c-1.4-0.4-2.7-0.7-3.9-1.1c-2.5-0.7-4.6-1.4-6.3-2c-1.7-0.6-3.1-1.1-4.1-1.4c-1-0.4-1.4-0.6-1.4-0.6c-0.3-0.1-0.4-0.4-0.3-0.7C160.4,459.6,160.7,459.5,161,459.6L161,459.6z");
        }
    };

    /* renamed from: b */
    public static final Stack<String> f4730b = new Stack<String>() {
        {
            add("M5.9,310c0,0,6.5,2.4,18.2,6.1c5.8,1.8,13,3.8,21.1,5.9c8.2,2.1,17.5,4.4,27.7,6.6c10.1,2.1,21.3,4.5,33.1,6.5c5.9,1.1,12,2,18.2,3c3.1,0.4,6.2,0.8,9.4,1.3c3.2,0.4,6.3,0.8,9.6,1.3c6.5,0.7,13.1,1.5,19.7,2.1c3.4,0.3,6.8,0.7,10.1,0.8c3.4,0.3,6.8,0.4,10.1,0.7c1.7,0.1,3.4,0.3,5.1,0.3c1.7,0.1,3.4,0.1,5.2,0.3c3.4,0.1,6.9,0.3,10.4,0.4c6.9,0.3,13.9,0.3,20.8,0.4h10.4c3.5,0,6.9,0,10.4-0.1c3.5-0.1,6.9-0.1,10.4-0.3c1.7,0,3.4-0.1,5.2-0.1c1.7-0.1,3.4-0.1,5.1-0.3c6.9-0.4,13.7-0.7,20.3-1.4c6.6-0.6,13.2-1.1,19.7-2c6.5-0.8,12.8-1.4,19-2.4c3.1-0.4,6.2-1,9.2-1.4c1.5-0.3,3-0.4,4.5-0.7c1.5-0.3,3-0.6,4.5-0.8c5.9-1.1,11.6-2.1,17.2-3.2c5.5-1.3,10.8-2.4,15.9-3.5c1.3-0.3,2.5-0.6,3.8-0.8s2.4-0.6,3.7-1c2.4-0.6,4.8-1.3,7-1.8s4.5-1.1,6.8-1.7c1.1-0.3,2.1-0.6,3.2-0.8c1-0.3,2.1-0.6,3.1-0.8c4.1-1.1,7.9-2.3,11.4-3.4c3.5-1,6.8-2,9.6-3c11.6-3.8,18.2-6.1,18.2-6.1s-6.6,2.3-18,6.3c-2.8,1-6.1,2-9.6,3.1c-3.5,1.1-7.3,2.3-11.4,3.5c-1,0.3-2.1,0.6-3.1,1c-1.1,0.3-2.1,0.6-3.2,0.8c-2.1,0.6-4.4,1.1-6.8,1.8c-2.3,0.6-4.6,1.3-7,1.8c-1.3,0.3-2.4,0.7-3.7,1c-1.3,0.3-2.5,0.6-3.8,0.8c-5.1,1.1-10.4,2.4-15.9,3.5c-5.5,1.1-11.3,2.1-17.2,3.2c-1.4,0.3-3,0.6-4.5,0.8c-1.5,0.3-3,0.4-4.5,0.7c-3.1,0.4-6.1,1-9.2,1.4c-6.2,1-12.5,1.7-19,2.5c-6.5,0.8-13.1,1.4-19.9,2c-6.8,0.7-13.5,1-20.3,1.4c-1.7,0.1-3.4,0.3-5.2,0.3c-1.7,0-3.4,0.1-5.2,0.1c-3.5,0.1-6.9,0.3-10.4,0.3c-3.5,0.1-6.9,0.1-10.4,0.1h-10.4c-6.9-0.1-13.9-0.3-20.8-0.6c-3.5-0.1-6.9-0.3-10.4-0.6c-1.7-0.1-3.4-0.1-5.2-0.3c-1.7-0.1-3.4-0.3-5.2-0.4c-3.4-0.3-6.8-0.6-10.3-0.7c-3.4-0.3-6.8-0.6-10.1-1c-6.8-0.6-13.2-1.4-19.7-2.1c-3.2-0.3-6.5-0.8-9.6-1.3c-3.2-0.4-6.3-0.8-9.4-1.3c-6.2-1-12.3-1.8-18.2-3c-11.8-2-22.8-4.4-33.1-6.6c-10.1-2.4-19.4-4.6-27.6-6.9c-8.2-2.3-15.2-4.4-21.1-6.2C12.4,312.6,5.9,310,5.9,310z");
            add("M226.2,0c0,0,0.1,37.2,0.3,93c0,55.8,0.1,130.3,0.3,204.7c-0.1,74.4-0.1,148.9-0.3,204.7s-0.3,93-0.3,93s-0.1-37.2-0.3-93c0-55.8-0.1-130.3-0.3-204.7c0.1-74.4,0.1-148.9,0.3-204.7S226.2,0,226.2,0z");
        }
    };

    /* renamed from: c */
    public static final Stack<String> f4731c = new Stack<String>() {
        {
            add("M222 4341 c-61 -21 -114 -61 -159 -120 -65 -84 -63 -47 -63 -1092 0 -683 3 -962 12 -998 21 -93 125 -199 232 -237 47 -17 100 -19 768 -22 l718 -3 0 -742 c0 -796 0 -799 51 -899 30 -58 119 -150 177 -180 26 -14 72 -31 102 -38 82 -17 1980 -10 2033 8 108 37 233 178 256 288 9 46 11 484 9 1934 l-3 1875 -28 56 c-32 66 -99 132 -165 162 l-47 22 -1920 2 c-1839 2 -1922 2 -1973 -16z m348 -1216 l0 -1075 -116 0 c-64 0 -135 6 -158 12 -52 16 -108 72 -124 124 -9 31 -12 261 -12 946 l0 904 28 55 c22 45 36 59 81 82 51 25 63 27 178 27 l123 0 0 -1075z m3050 0 l0 -1075 -1440 0 -1440 0 0 1075 0 1075 1440 0 1440 0 0 -1075z m471 1048 c45 -23 59 -37 82 -82 l27 -55 0 -904 c0 -685 -3 -915 -12 -946 -16 -52 -72 -108 -124 -124 -23 -6 -94 -12 -158 -12 l-116 0 0 1075 0 1075 123 0 c115 0 127 -2 178 -27z m109 -2838 l0 -595 -1150 0 -1150 0 0 565 0 565 1043 0 c670 0 1065 4 1106 11 35 5 80 18 100 29 20 11 39 19 44 20 4 0 7 -268 7 -595z m0 -848 c0 -120 -21 -180 -84 -244 -88 -88 -26 -84 -1087 -81 l-924 3 -51 27 c-101 53 -143 131 -151 279 l-6 99 1152 0 1151 0 0 -83z");
            add("M312 3335 l-32 -27 0 -109 c0 -103 1 -111 25 -134 33 -34 80 -33 117 2 27 26 28 30 28 129 0 92 -2 105 -22 128 -35 40 -78 43 -116 11z");
            add("M311 2604 c-44 -37 -44 -91 1 -129 84 -71 186 48 109 126 -36 36 -70 37 -110 3z");
            add("M3935 4054 c-25 -26 -25 -30 -25 -202 l0 -176 27 -28 c35 -37 84 -38 118 -3 25 24 25 26 25 205 0 179 0 181 -25 205 -34 35 -88 34 -120 -1z");
            add("M3939 2601 l-29 -29 0 -177 c0 -173 0 -176 25 -200 35 -36 96 -35 124 1 19 25 21 40 21 206 l0 179 -26 24 c-37 34 -78 33 -115 -4z");
            add("M2919 421 c-36 -36 -37 -70 -3 -110 l26 -31 109 0 c103 0 111 1 134 25 34 33 33 80 -2 117 -26 27 -30 28 -131 28 -101 0 -104 -1 -133 -29z");
            add("M3649 421 c-80 -81 33 -199 116 -121 37 35 35 90 -6 124 -40 34 -74 33 -110 -3z");
            add("M155 1578 c-27 -32 -27 -34 -21 -131 29 -418 299 -779 701 -936 103 -41 273 -81 342 -81 70 0 68 -11 -17 -95 -71 -71 -80 -84 -80 -116 0 -29 7 -43 31 -63 57 -48 67 -43 256 147 146 146 173 177 173 202 0 25 -27 58 -171 205 -155 158 -173 174 -204 174 -48 0 -85 -36 -85 -84 0 -32 10 -48 77 -120 l77 -83 -59 7 c-388 47 -702 283 -817 616 -29 84 -39 136 -48 245 -7 90 -11 102 -35 122 -39 35 -87 31 -120 -9z");
        }
    };

    /* renamed from: d */
    private PieProgressView f4732d;

    /* renamed from: e */
    private SVGImageView f4733e;

    /* renamed from: f */
    private SVGImageView f4734f;

    /* renamed from: g */
    private TextView f4735g;

    /* renamed from: h */
    private C4890ad f4736h;

    /* renamed from: i */
    private LayoutParams f4737i;

    /* renamed from: j */
    private Rect f4738j;

    /* renamed from: k */
    private RelativeLayout f4739k;

    public C4894ae(Context context) {
        super(context);
        m2958a(context);
    }

    /* renamed from: a */
    private void m2958a(Context context) {
        int dipToPx = (int) ScreenUtil.dipToPx(context, 75.0f);
        TypedValue typedValue = new TypedValue();
        Theme theme = context.getTheme();
        theme.resolveAttribute(C4430R.attr.netverify_scanOverlayLiveness, typedValue, true);
        int i = typedValue.data;
        theme.resolveAttribute(C4430R.attr.netverify_scanOverlayLivenessLocked, typedValue, true);
        int i2 = typedValue.data;
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        this.f4733e = new SVGImageView(context);
        this.f4733e.setPathStringStack(f4729a);
        this.f4733e.setLayoutParams(layoutParams);
        this.f4733e.setPaintColor(i);
        addView(this.f4733e);
        this.f4734f = new SVGImageView(context);
        this.f4734f.setPathStringStack(f4730b);
        this.f4734f.setLayoutParams(new LayoutParams(-1, -1));
        this.f4734f.setPaintColor(i);
        addView(this.f4734f);
        this.f4736h = new C4890ad(context);
        this.f4736h.setLayoutParams(new LayoutParams(-1, -1));
        addView(this.f4736h);
        this.f4735g = new TextView(context);
        LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.gravity = 1;
        this.f4735g.setLayoutParams(layoutParams2);
        this.f4735g.setTextColor(i);
        this.f4735g.setVisibility(4);
        this.f4735g.setText(NVStrings.getExternalString(context, NVStrings.OVERLAY_LIVENESS_ADVICE));
        addView(this.f4735g);
        this.f4732d = new PieProgressView(context);
        this.f4737i = new LayoutParams(dipToPx, dipToPx);
        this.f4737i.gravity = 1;
        this.f4732d.setLayoutParams(this.f4737i);
        this.f4732d.setBorderWidth(3);
        this.f4732d.setBorderColor(i2);
        this.f4732d.setPieColor(i2);
        this.f4732d.setTextColor(i);
        this.f4732d.setVisibility(4);
        addView(this.f4732d);
        this.f4739k = (RelativeLayout) LayoutInflater.from(context).inflate(C4430R.layout.helpview_land, null);
        this.f4739k.setVisibility(4);
        int dpToPx = ScreenUtil.dpToPx(getContext(), CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256);
        SVGImageView sVGImageView = new SVGImageView(context);
        sVGImageView.setLayoutParams(new RelativeLayout.LayoutParams(dpToPx, dpToPx));
        sVGImageView.setMaxWidth(dpToPx);
        sVGImageView.setMaxHeight(dpToPx);
        sVGImageView.setPaintColor(i);
        sVGImageView.setPathStringStack(f4731c);
        ((LinearLayout) this.f4739k.findViewById(C4430R.C4432id.doctype_container)).addView(sVGImageView);
        ((TextView) this.f4739k.findViewById(C4430R.C4432id.helpview_doctype_title)).setText(Html.fromHtml(NVStrings.getExternalString(context, NVStrings.SCANVIEW_LIVENESS_LANDSCAPE_HEADER)));
        ((TextView) this.f4739k.findViewById(C4430R.C4432id.helpview_scan_instructions)).setText(NVStrings.getExternalString(context, NVStrings.SCANVIEW_LIVENESS_LANDSCAPE_DESCRIPTION));
        this.f4739k.findViewById(C4430R.C4432id.helpview_progress_info).setVisibility(4);
        this.f4739k.findViewById(C4430R.C4432id.helpview_fallback_button).setVisibility(4);
        this.f4739k.findViewById(C4430R.C4432id.helpview_tap_to_continue).setVisibility(4);
        this.f4739k.findViewById(C4430R.C4432id.helpview_progress_bar).setVisibility(4);
        addView(this.f4739k);
    }

    public void calculate(DocumentScanMode documentScanMode, int i, int i2) {
        float f;
        if (i != 0 && i2 != 0) {
            int i3 = (int) ((((float) i) - (((float) i) * 0.9f)) / 2.0f);
            int i4 = (int) ((((float) i2) - (((float) i2) * 0.95f)) / 2.0f);
            this.f4736h.setPadding(i3, -i4, i3, i4 * 3);
            int i5 = (int) ((((float) i) - (((float) i) * 0.8f)) / 2.0f);
            int i6 = (int) ((((float) i2) - (((float) i2) * 0.85f)) / 2.0f);
            this.f4733e.setPadding(i5, i6 - i4, i5, (i4 * 2) + i6);
            this.f4734f.setPadding(i5, i6 - i4, i5, (i4 * 2) + i6);
            if (((float) i) / ((float) i2) < 0.7616f) {
                f = ((float) (i - (i5 * 2))) / 0.7616f;
            } else {
                f = (float) ((i2 - (i6 * 2)) - i4);
            }
            int round = Math.round(f / 5.5f);
            this.f4737i.width = round;
            this.f4737i.height = round;
            this.f4732d.setY((float) Math.round((f / 6.0f) + ((((float) i2) - f) / 2.0f)));
            this.f4735g.setY((float) ((i2 - i6) - (i4 * 2)));
            this.f4738j = new Rect(this.f4736h.getPaddingLeft(), this.f4736h.getPaddingTop(), i - this.f4736h.getPaddingRight(), i2 - this.f4736h.getPaddingBottom());
        }
    }

    public void addViews(ViewGroup viewGroup) {
        setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        viewGroup.addView(this);
    }

    public void prepareDraw(ScanSide scanSide, boolean z, boolean z2) {
        if (!z2 && this.f4739k.getVisibility() != 0) {
            m2959a(this.f4732d, false, true);
            m2959a(this.f4735g, false, true);
            m2959a(this.f4733e, false, true);
            m2959a(this.f4736h, false, true);
            m2959a(this.f4734f, false, true);
            m2959a(this.f4739k, true, true);
        } else if (z2 && this.f4739k.getVisibility() == 0) {
            m2959a(this.f4739k, false, true);
            m2959a(this.f4732d, false, true);
            m2959a(this.f4735g, false, true);
            m2959a(this.f4733e, true, true);
            m2959a(this.f4736h, true, true);
            m2959a(this.f4734f, true, true);
        }
    }

    public void doDraw(Canvas canvas) {
    }

    public void update(ExtractionUpdate extractionUpdate) {
        if (extractionUpdate.getState() == C4888ab.f4706a) {
            Log.m1907d("update scanningNotTracking");
            m2959a(this.f4732d, false, false);
            m2959a(this.f4735g, false, false);
            m2959a(this.f4734f, true, true);
            this.f4736h.mo46806a(false);
        } else if (extractionUpdate.getState() == C4888ab.f4707b) {
            Log.m1907d("update scanningTracking");
            m2959a(this.f4732d, true, true);
            m2959a(this.f4735g, true, true);
            m2959a(this.f4734f, false, false);
            this.f4736h.mo46806a(true);
        } else if (extractionUpdate.getState() == C4888ab.f4708c) {
            Log.m1907d("update extraction");
            m2959a(this.f4732d, false, true);
            m2959a(this.f4735g, false, true);
            m2959a(this.f4733e, false, true);
            m2959a(this.f4736h, false, true);
            this.f4736h.mo46806a(false);
        } else if (extractionUpdate.getState() == C4888ab.f4709d) {
            Log.m1907d("update progress");
            Pair pair = (Pair) extractionUpdate.getData();
            this.f4732d.setProgress(((Integer) pair.first).intValue(), Integer.toString(((Integer) pair.second).intValue()));
        }
    }

    public void setVisible(int i) {
        setVisibility(i);
    }

    public Rect getOverlayBounds() {
        return this.f4738j;
    }

    /* renamed from: a */
    private void m2959a(View view, boolean z, boolean z2) {
        float f = 1.0f;
        if (z2) {
            float f2 = z ? 0.0f : 1.0f;
            if (!z) {
                f = 0.0f;
            }
            AlphaAnimation alphaAnimation = new AlphaAnimation(f2, f);
            alphaAnimation.setDuration(250);
            view.setAnimation(alphaAnimation);
        }
        view.setVisibility(z ? 0 : 4);
    }
}
