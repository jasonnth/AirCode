package org.spongycastle.crypto.modes;

import android.support.p000v4.media.session.PlaybackStateCompat;
import net.p318sf.scuba.smartcards.ISO7816;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.StreamBlockCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.ParametersWithSBox;

public class GCFBBlockCipher extends StreamBlockCipher {

    /* renamed from: C */
    private static final byte[] f6781C = {105, 0, 114, ISO7816.INS_MSE, ISOFileInfo.FMD_BYTE, -55, 4, 35, ISOFileInfo.ENV_TEMP_EF, 58, -37, -106, 70, -23, ISO7816.INS_PSO, -60, 24, -2, -84, -108, 0, -19, 7, 18, ISO7816.INS_GET_RESPONSE, -122, ISO7816.INS_UPDATE_RECORD, ISO7816.INS_ENVELOPE, -17, 76, -87, 43};
    private final CFBBlockCipher cfbEngine;
    private long counter = 0;
    private boolean forEncryption;
    private KeyParameter key;

    public GCFBBlockCipher(BlockCipher engine) {
        super(engine);
        this.cfbEngine = new CFBBlockCipher(engine, engine.getBlockSize() * 8);
    }

    public void init(boolean forEncryption2, CipherParameters params) throws IllegalArgumentException {
        this.counter = 0;
        this.cfbEngine.init(forEncryption2, params);
        this.forEncryption = forEncryption2;
        if (params instanceof ParametersWithIV) {
            params = ((ParametersWithIV) params).getParameters();
        }
        if (params instanceof ParametersWithRandom) {
            params = ((ParametersWithRandom) params).getParameters();
        }
        if (params instanceof ParametersWithSBox) {
            params = ((ParametersWithSBox) params).getParameters();
        }
        this.key = (KeyParameter) params;
    }

    public String getAlgorithmName() {
        String name = this.cfbEngine.getAlgorithmName();
        return name.substring(0, name.indexOf(47)) + "/G" + name.substring(name.indexOf(47) + 1);
    }

    public int getBlockSize() {
        return this.cfbEngine.getBlockSize();
    }

    public int processBlock(byte[] in, int inOff, byte[] out, int outOff) throws DataLengthException, IllegalStateException {
        processBytes(in, inOff, this.cfbEngine.getBlockSize(), out, outOff);
        return this.cfbEngine.getBlockSize();
    }

    /* access modifiers changed from: protected */
    public byte calculateByte(byte b) {
        if (this.counter > 0 && this.counter % PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID == 0) {
            BlockCipher base = this.cfbEngine.getUnderlyingCipher();
            base.init(false, this.key);
            byte[] nextKey = new byte[32];
            base.processBlock(f6781C, 0, nextKey, 0);
            base.processBlock(f6781C, 8, nextKey, 8);
            base.processBlock(f6781C, 16, nextKey, 16);
            base.processBlock(f6781C, 24, nextKey, 24);
            this.key = new KeyParameter(nextKey);
            base.init(true, this.key);
            byte[] iv = this.cfbEngine.getCurrentIV();
            base.processBlock(iv, 0, iv, 0);
            this.cfbEngine.init(this.forEncryption, new ParametersWithIV(this.key, iv));
        }
        this.counter++;
        return this.cfbEngine.calculateByte(b);
    }

    public void reset() {
        this.counter = 0;
        this.cfbEngine.reset();
    }
}
