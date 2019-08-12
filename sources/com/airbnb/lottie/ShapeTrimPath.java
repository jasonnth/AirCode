package com.airbnb.lottie;

import org.json.JSONObject;

class ShapeTrimPath {
    private final AnimatableFloatValue end;
    private final String name;
    private final AnimatableFloatValue offset;
    private final AnimatableFloatValue start;
    private final Type type;

    static class Factory {
        static ShapeTrimPath newInstance(JSONObject json, LottieComposition composition) {
            return new ShapeTrimPath(json.optString("nm"), Type.forId(json.optInt("m", 1)), Factory.newInstance(json.optJSONObject("s"), composition, false), Factory.newInstance(json.optJSONObject("e"), composition, false), Factory.newInstance(json.optJSONObject("o"), composition, false));
        }
    }

    enum Type {
        Simultaneously,
        Individually;

        static Type forId(int id) {
            switch (id) {
                case 1:
                    return Simultaneously;
                case 2:
                    return Individually;
                default:
                    throw new IllegalArgumentException("Unknown trim path type " + id);
            }
        }
    }

    private ShapeTrimPath(String name2, Type type2, AnimatableFloatValue start2, AnimatableFloatValue end2, AnimatableFloatValue offset2) {
        this.name = name2;
        this.type = type2;
        this.start = start2;
        this.end = end2;
        this.offset = offset2;
    }

    /* access modifiers changed from: 0000 */
    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: 0000 */
    public Type getType() {
        return this.type;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableFloatValue getEnd() {
        return this.end;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableFloatValue getStart() {
        return this.start;
    }

    /* access modifiers changed from: 0000 */
    public AnimatableFloatValue getOffset() {
        return this.offset;
    }

    public String toString() {
        return "Trim Path: {start: " + this.start + ", end: " + this.end + ", offset: " + this.offset + "}";
    }
}
