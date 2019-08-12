package com.daon.sdk.face.module.analyzer;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.daon.sdk.face.YUV;
import com.daon.sdk.face.module.C3290a;
import com.daon.sdk.face.module.C3290a.C3291a;
import com.daon.sdk.face.module.C3297c;
import visidon.Lib.C5534a;
import visidon.Lib.C5535b;
import visidon.Lib.VerificationAPI;
import visidon.Lib.VerificationAPI.C5527a;
import visidon.Lib.VerificationAPI.C5529d;
import visidon.Lib.VerificationAPI.C5530e;
import visidon.Lib.VerificationAPI.C5532h;

/* renamed from: com.daon.sdk.face.module.analyzer.c */
public final class C3295c implements C3290a, C3297c {

    /* renamed from: a */
    private static String f3072a = "26f596b34af29c73f6a07dca9e2a854dc814888ffcc8eb693c6fcba00c3d66c682055aaf682a2190b55d6686a19d4b83042ec539900324c8f53569219d459767767308419c6bce86cf90b7ede9febbc2a9b219620c730184078b78bb7123506af057b879367b767733005d186a0812df6da6ac7995af2ad22feed2fdba122e7feb1f4dcb6799b5e1e2ab66db0f4dba35df2b63f08dd470ad47e9de706449bf0e432a40b4dd145cec01946a7ab237cd9d95f78007ec1bf3dc470b01589655339019edbbde6402f6d22c9566159f9c7240e0c9de01aeb341349f249c3d209dfb17dbf0d34a4ff35cefd0aecff48a1bd2d89cd22bac4a875c575e2e5f5ccbe245c9638216da65b339d3fa8d1b12bb12a4213d99836d9829dce7c1042e5f0d0481f5aa2d397fa78357e3a5f5a1da4bc67d563232d2d279c4cc208be3460e4ed70b09958b82574e2cf535bddb9d912d30628f0d5d4656f025e4f3f32d4c3d0dd650245b96e23ecde2e56ee7f45d75f3a1981d7d079b011d06f966e36c16db1de58a78ccf6abb0f7ddf6c6f1f974064a480530fedb6ec4e74c346e044461c9f3a10b8e0d34761d7bc778321749c7708ece91baf445f44e8d870973e37ea7597354f3da5ec669bf6dae5802d9c5274021d011fe9bc7da8c8e980adeb7db53b91e29702532546a51201d9d66765be7ebadd9bf601298b6ad78f022c54133955a707a0447fdb0e4e00c54c6c72a02527055ad9e208cdc45dcdbffd270f85b5451bcecfa3ded21aaa5b7fd543cfa67439995646185ffc0ed257eca6819c71de91a3b093196db4680817cdaa836e6fa3aa18f155394762dfd1575b4af0cd14d5766f9d6dfb0b163948feb787be038fa14b0b08749c82113f184339c9bbb83f228b4afc372992d3bc52f72cec103f110d37d28bf562afae7bc73f7a7d8515d0d37365a152e80a7b74fc5b6680dda0d841ed37e525d8ea08b2ff5321b996b4799416279fbb096de4d36b00b255b3e2c50ff95da5f89edebd85d89b143d2702d3ddfcdec0e52f28a2ab0bcb48124426f027ac7f2542f3eb5a6d7d776d154d75413ba0a17361dd9e739003a4919d205fcb5dbfc62a65fe0e64d8bbf699158350b5111d3578f5d326d6c8afe82fac80b154536d13aab7ebc7cd7d0897aacfc2081b412aaf0bc60ebffddd0d58c24d15145c75933b645cf3234e03132060d7a28d72413b365d014d8e42a3538411b626a4c00a1f1de9ea51b497bc65d43d6c7c72e2e19d000c624aca1fc8a60e7b9533232323a8b7af7faaea78d8186c650ba606adcaace054b8116b7656af705101426a8af35062a72b4d9b96edfbc9fb3332abce0ca84d8d847b8f7f26ce379cfa14d0ee16edf57d41af918ea16ad182be7c5a42f9fbc9da788d11440ad8f0986838a41ce0db633fbe514296bc935b255180f20d00fd0ad8f3d55d0c2ebe7fe4730a2ee2f63c892b59143b94b7f4e5b7d5ea735c721e58ff27a85b5673114fa1757c8edb8fc5e17ce6433c34a9cea516d15e28ded54ab3edf5dd7d7fd31181c456c78a9816ea2993334ac8d686e567c961d7c60e2a100712f6e8d77f641785360bcbb5e710c7e9958041b4fa0dab94569b6fefc64d49c08c80832c437104538fe7eea187703462f7986afec2816d8a361abf753534ae3285ab37f75a89fc46c5e3dfd28feeac6b0b21349604f013d18a5d6034e26bfde200ff9aa0d4c63ade777e2c8c563140cec71407c915c3efb209797ea7cd62f4047ab008783a07197b396b47f34b94982e60575fb250b4deba53b55be0d7713064a7a5476ad887c38c7370ee6f712ff4d57a47da7138237fd31f26d0a1a5350707e6729d7c376ff07f74f4324dadd709af3196e1902a41895b407010f3e7f4c88af49c06fcb60b2ca2b964c56913c71d0a31d5ee2c78cb4f0e2cccc48c4ff0910136959178481e5919ab4ddf5337db49fac9eff6915bf7da44f56914457020e0cec0051d154aded738c8bc53360d23f78059cff02a3f77f3d39e9a3c21efdf469ad095525e55d5c122c70a0e894d4913ea651520933626324d5915b403b2be16144363f8d562f4402758b2ae83c674933b32167276668b4cc8d1f58312a7de20795595332f15c81f74e52b92013525e16fdc43d4699750f3ea51b82e9e681e9bd750d4256368a0dd148303700bde149162c0072c34c15761b2604e47c556490ca7166ebbf440cf52e";

    /* renamed from: b */
    private final Context f3073b;

    /* renamed from: c */
    private int f3074c = 640;

    /* renamed from: d */
    private int f3075d = 480;

    /* renamed from: e */
    private boolean f3076e = false;

    /* renamed from: f */
    private boolean f3077f = false;

    /* renamed from: g */
    private Bundle f3078g = new Bundle();

    public C3295c(Context context) {
        this.f3073b = context;
    }

    /* renamed from: a */
    public final void mo28825a(int i, int i2) {
        this.f3074c = i;
        this.f3075d = i2;
        if (this.f3076e) {
            m1770a(C5527a.VIDEO);
        }
    }

    /* renamed from: c */
    private synchronized boolean m1771c() {
        byte[] bArr;
        String str = f3072a;
        if (str == null) {
            bArr = null;
        } else {
            int length = str.length();
            bArr = new byte[(length / 2)];
            for (int i = 0; i < length; i += 2) {
                bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
            }
        }
        if (VerificationAPI.m4051a(bArr) == C5529d.OK) {
            this.f3076e = m1770a(C5527a.VIDEO);
        }
        return this.f3076e;
    }

    /* renamed from: a */
    private boolean m1770a(C5527a aVar) {
        C5535b bVar = new C5535b(this.f3073b);
        bVar.f7300c = this.f3075d;
        bVar.f7299b = this.f3074c;
        bVar.f7302e = aVar;
        bVar.f7303f = C5532h.MEDIUM;
        bVar.f7304g = C5530e.HIGH;
        Log.i("DAON", "Initializing... ");
        Log.i("DAON", "Image size: width = " + bVar.f7299b + " height = " + bVar.f7300c);
        if (VerificationAPI.m4050a(bVar) == C5529d.OK) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public final synchronized void mo28821a() {
        if (this.f3076e) {
            this.f3076e = false;
            VerificationAPI.m4052a();
        }
    }

    /* renamed from: a */
    public final void mo28820a(YUV yuv, C3291a aVar) {
        String str;
        Bundle bundle;
        int i = 0;
        if (!this.f3076e) {
            m1771c();
            return;
        }
        C5534a b = VerificationAPI.m4054b(yuv.getData());
        float f = ((float) b.f7293c) / 100.0f;
        float f2 = ((float) b.f7292b) / 100.0f;
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("result.face.position", b.f7291a);
        bundle2.putFloat("result.liveness.score", f);
        bundle2.putFloat("result.face.recognition.score", f2);
        boolean z = f > this.f3078g.getFloat("blink.settings.threshold", 0.4f);
        if (!this.f3077f || !z) {
            str = "result.liveness.type";
            if (z) {
                i = 3;
                bundle = bundle2;
            } else {
                bundle = bundle2;
            }
        } else {
            str = "result.liveness.type";
            bundle = bundle2;
        }
        bundle.putInt(str, i);
        this.f3077f = z;
        aVar.mo28786a(bundle2);
    }

    /* renamed from: a */
    public final void mo28822a(Bundle bundle) {
        this.f3078g = bundle;
    }
}
