package com.airbnb.lottie;

import org.json.JSONObject;

class Mask {
    private final MaskMode maskMode;
    private final AnimatableShapeValue maskPath;

    static class Factory {
        static Mask newMask(JSONObject json, LottieComposition composition) {
            MaskMode maskMode;
            String optString = json.optString("mode");
            char c = 65535;
            switch (optString.hashCode()) {
                case 97:
                    if (optString.equals("a")) {
                        c = 0;
                        break;
                    }
                    break;
                case 105:
                    if (optString.equals("i")) {
                        c = 2;
                        break;
                    }
                    break;
                case 115:
                    if (optString.equals("s")) {
                        c = 1;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    maskMode = MaskMode.MaskModeAdd;
                    break;
                case 1:
                    maskMode = MaskMode.MaskModeSubtract;
                    break;
                case 2:
                    maskMode = MaskMode.MaskModeIntersect;
                    break;
                default:
                    maskMode = MaskMode.MaskModeUnknown;
                    break;
            }
            return new Mask(maskMode, Factory.newInstance(json.optJSONObject("pt"), composition));
        }
    }

    enum MaskMode {
        MaskModeAdd,
        MaskModeSubtract,
        MaskModeIntersect,
        MaskModeUnknown
    }

    private Mask(MaskMode maskMode2, AnimatableShapeValue maskPath2) {
        this.maskMode = maskMode2;
        this.maskPath = maskPath2;
    }

    /* access modifiers changed from: 0000 */
    public MaskMode getMaskMode() {
        return this.maskMode;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableShapeValue getMaskPath() {
        return this.maskPath;
    }
}
