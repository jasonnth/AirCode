package p334uk.p335co.deanwild.materialshowcaseview;

import android.content.Context;

/* renamed from: uk.co.deanwild.materialshowcaseview.PrefsManager */
public class PrefsManager {
    public static int SEQUENCE_FINISHED = -1;
    public static int SEQUENCE_NEVER_STARTED = 0;
    private Context context;
    private String showcaseID;

    /* access modifiers changed from: 0000 */
    public boolean hasFired() {
        return getSequenceStatus() == SEQUENCE_FINISHED;
    }

    /* access modifiers changed from: 0000 */
    public void setFired() {
        setSequenceStatus(SEQUENCE_FINISHED);
    }

    /* access modifiers changed from: 0000 */
    public int getSequenceStatus() {
        return this.context.getSharedPreferences("material_showcaseview_prefs", 0).getInt("status_" + this.showcaseID, SEQUENCE_NEVER_STARTED);
    }

    /* access modifiers changed from: 0000 */
    public void setSequenceStatus(int status) {
        this.context.getSharedPreferences("material_showcaseview_prefs", 0).edit().putInt("status_" + this.showcaseID, status).apply();
    }

    public void resetShowcase() {
        resetShowcase(this.context, this.showcaseID);
    }

    static void resetShowcase(Context context2, String showcaseID2) {
        context2.getSharedPreferences("material_showcaseview_prefs", 0).edit().putInt("status_" + showcaseID2, SEQUENCE_NEVER_STARTED).apply();
    }

    public void close() {
        this.context = null;
    }
}
