package com.airbnb.lottie;

import org.json.JSONObject;

public class MergePaths {
    private final MergePathsMode mode;
    private final String name;

    static class Factory {
        static MergePaths newInstance(JSONObject json) {
            return new MergePaths(json.optString("nm"), MergePathsMode.forId(json.optInt("mm", 1)));
        }
    }

    enum MergePathsMode {
        Merge,
        Add,
        Subtract,
        Intersect,
        ExcludeIntersections;

        /* access modifiers changed from: private */
        public static MergePathsMode forId(int id) {
            switch (id) {
                case 1:
                    return Merge;
                case 2:
                    return Add;
                case 3:
                    return Subtract;
                case 4:
                    return Intersect;
                case 5:
                    return ExcludeIntersections;
                default:
                    return Merge;
            }
        }
    }

    private MergePaths(String name2, MergePathsMode mode2) {
        this.name = name2;
        this.mode = mode2;
    }

    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: 0000 */
    public MergePathsMode getMode() {
        return this.mode;
    }

    public String toString() {
        return "MergePaths{mode=" + this.mode + '}';
    }
}
