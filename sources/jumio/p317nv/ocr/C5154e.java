package jumio.p317nv.ocr;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.jumio.clientlib.impl.livenessAndTM.FrameProcessingCallbackTemplateMatcher;
import com.jumio.clientlib.impl.livenessAndTM.FrameQueue;
import com.jumio.clientlib.impl.livenessAndTM.LibImage;
import com.jumio.clientlib.impl.livenessAndTM.TemplateInfo;
import com.jumio.commons.log.Log;

/* renamed from: jumio.nv.ocr.e */
/* compiled from: TemplateMatcherFrameCallback */
public class C5154e extends FrameProcessingCallbackTemplateMatcher {

    /* renamed from: a */
    private Handler f5798a;

    /* renamed from: b */
    private long f5799b = 0;

    /* renamed from: c */
    private long f5800c = 0;

    /* renamed from: d */
    private boolean f5801d = false;

    public C5154e(Handler handler) {
        this.f5798a = handler;
    }

    public void intermediateResult(FrameQueue frameQueue, TemplateInfo templateInfo) {
        long currentTimeMillis;
        if (!this.f5801d) {
            if (this.f5800c == 0) {
                this.f5800c = System.currentTimeMillis();
                currentTimeMillis = 0;
            } else {
                currentTimeMillis = System.currentTimeMillis() - this.f5800c;
                this.f5800c = System.currentTimeMillis();
            }
            C5145a aVar = new C5145a(templateInfo);
            aVar.mo47266a(currentTimeMillis);
            Bundle bundle = new Bundle();
            bundle.putParcelable("templateInfo", aVar);
            if (frameQueue.size() > 0) {
                LibImage frameByID = frameQueue.getFrameByID(templateInfo.getFrameIndex());
                if (frameByID != null) {
                    byte[] bArr = new byte[((int) (((float) (frameByID.getWidth() * frameByID.getHeight())) * 1.5f))];
                    frameByID.getFrameBuffer(bArr);
                    bundle.putByteArray("finalResultFrame", bArr);
                } else {
                    Log.m1929w("TemplateMatcher", "IntermediateResult: no frame received from queue!!");
                }
            } else {
                Log.m1929w("TemplateMatcher", "IntermediateResult: FrameQueue empty!!");
            }
            Message message = new Message();
            message.what = 102;
            message.setData(bundle);
            this.f5798a.sendMessage(message);
        }
    }

    public void finalResult(FrameQueue frameQueue, TemplateInfo templateInfo) {
        long j = 0;
        if (!this.f5801d) {
            this.f5801d = true;
            if (this.f5799b == 0) {
                this.f5799b = System.currentTimeMillis();
            } else {
                j = System.currentTimeMillis() - this.f5799b;
                this.f5799b = System.currentTimeMillis();
            }
            Bundle bundle = new Bundle();
            C5145a aVar = new C5145a(templateInfo);
            aVar.mo47266a(j);
            bundle.putParcelable("templateInfo", aVar);
            LibImage frameByID = frameQueue.getFrameByID(templateInfo.getFrameIndex());
            byte[] bArr = new byte[((int) (((float) (frameByID.getWidth() * frameByID.getHeight())) * 1.5f))];
            frameByID.getFrameBuffer(bArr);
            frameQueue.clear();
            bundle.putByteArray("finalResultFrame", bArr);
            Message message = new Message();
            message.what = 103;
            message.setData(bundle);
            this.f5798a.sendMessage(message);
        }
    }

    public void noResult(FrameQueue frameQueue) {
        this.f5801d = false;
        this.f5798a.sendEmptyMessage(101);
    }

    /* renamed from: a */
    public void mo47309a() {
        this.f5801d = false;
    }
}
