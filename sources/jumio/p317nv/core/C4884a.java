package jumio.p317nv.core;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.jumio.p311nv.NetverifyDocumentData;
import com.jumio.p311nv.NetverifySDK;

/* renamed from: jumio.nv.core.a */
/* compiled from: CloseSdkIntent */
public class C4884a extends Intent {
    public static final Creator<C4884a> CREATOR = new Creator<C4884a>() {
        /* renamed from: a */
        public C4884a createFromParcel(Parcel parcel) {
            return new C4884a();
        }

        /* renamed from: a */
        public C4884a[] newArray(int i) {
            return new C4884a[i];
        }
    };

    public C4884a() {
        super("com.jumio.nv.CLOSE_SDK");
    }

    public C4884a(NetverifyDocumentData netverifyDocumentData, String str) {
        super("com.jumio.nv.CLOSE_SDK");
        putExtra(NetverifySDK.EXTRA_SCAN_DATA, netverifyDocumentData);
        putExtra(NetverifySDK.EXTRA_SCAN_REFERENCE, str);
        putExtra("com.jumio.nv.RESULT", -1);
    }

    public C4884a(int i, int i2, String str, String str2) {
        super("com.jumio.nv.CLOSE_SDK");
        putExtra(NetverifySDK.EXTRA_SCAN_REFERENCE, str2);
        putExtra(NetverifySDK.EXTRA_ERROR_CODE, i);
        putExtra(NetverifySDK.EXTRA_DETAILED_ERROR_CODE, i2);
        putExtra(NetverifySDK.EXTRA_ERROR_MESSAGE, str);
        putExtra("com.jumio.nv.RESULT", 0);
    }

    /* renamed from: a */
    public static IntentFilter m2939a() {
        return new IntentFilter("com.jumio.nv.CLOSE_SDK");
    }
}
