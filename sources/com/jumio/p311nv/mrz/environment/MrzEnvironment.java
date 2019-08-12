package com.jumio.p311nv.mrz.environment;

import android.content.Context;
import com.jumio.core.environment.Environment;
import java.io.File;

/* renamed from: com.jumio.nv.mrz.environment.MrzEnvironment */
public class MrzEnvironment extends Environment {
    public static final String BUILD_VERSION = "JMSDK 2.4.0 (0-55)";
    public static final String OCR_VERSION = "1.99.0";

    /* renamed from: a */
    private static boolean f3445a = false;

    /* renamed from: b */
    private static String f3446b = "/jumio/";

    public static String getMRZEngineSettingsPath(Context context) {
        extractFile(context, MrzEnvironment.class, "mrz_mobile/mrzcontexts/field_syntax_restrictions", "3925ca191439b16624ac1d1031604c7ca20a445e7ce9befe832d316e39f6c5f6", ".xml");
        extractFile(context, MrzEnvironment.class, "mrz_mobile/mrzhighlight/highlight-ini", "7a2db3523eea4897eca168c26c18466a2e3498311cd47721c431a3039b2bccbc", ".pb");
        extractFile(context, MrzEnvironment.class, "mrz_mobile/dictionaries/six_digits", "4089d6a775648628dc2488aecab49f3d1fd0adcd4683e0e891bf47f64fc21e98", ".pb");
        extractFile(context, MrzEnvironment.class, "mrz_mobile/dictionaries/sc_an_rules", "6f366db36c024868078647a56f90300e1cfd5dc4b685f62ffb4fdfe2f7020d8f", ".txt");
        extractFile(context, MrzEnvironment.class, "mrz_mobile/dictionaries/surname", "9e1747493f14722e44af43c014bac4cd92cfa5696bd4b826aba36091ea80a7da", ".dict");
        extractFile(context, MrzEnvironment.class, "mrz_mobile/dictionaries/name", "5fdc4542884401d9314f469cf5b63395bb2b2418dc489f695008aaddad62a81f", ".dict");
        extractFile(context, MrzEnvironment.class, "mrz_mobile/mrzapi_engine", "03187c416d60de0b3c06160c9fb6837f3760c0b90d24657706795795c338cfcc", ".xml");
        extractFile(context, MrzEnvironment.class, "mrz_mobile/mrzocr/network_6_packed", "c1b80624cb2a615ba60f9b09b20ff299b707300a89a311c1f6678e22a72a28e7", ".pb");
        return new File(getDataDirectory(context), "mrz_mobile/mrzapi_engine.xml").getAbsolutePath();
    }

    public static synchronized boolean loadMRZJniInterfaceLib() {
        synchronized (MrzEnvironment.class) {
            if (!f3445a) {
                System.loadLibrary("mrzjniInterface");
                f3445a = true;
            }
        }
        return true;
    }
}