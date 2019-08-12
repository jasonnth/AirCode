package com.airbnb.lottie;

class GradientColor {
    private final int[] colors;
    private final float[] positions;

    GradientColor(float[] positions2, int[] colors2) {
        this.positions = positions2;
        this.colors = colors2;
    }

    /* access modifiers changed from: 0000 */
    public float[] getPositions() {
        return this.positions;
    }

    /* access modifiers changed from: 0000 */
    public int[] getColors() {
        return this.colors;
    }

    /* access modifiers changed from: 0000 */
    public int getSize() {
        return this.colors.length;
    }

    /* access modifiers changed from: 0000 */
    public void lerp(GradientColor gc1, GradientColor gc2, float progress) {
        if (gc1.colors.length != gc2.colors.length) {
            throw new IllegalArgumentException("Cannot interpolate between gradients. Lengths vary (" + gc1.colors.length + " vs " + gc2.colors.length + ")");
        }
        for (int i = 0; i < gc1.colors.length; i++) {
            this.positions[i] = MiscUtils.lerp(gc1.positions[i], gc2.positions[i], progress);
            this.colors[i] = GammaEvaluator.evaluate(progress, gc1.colors[i], gc2.colors[i]);
        }
    }
}
