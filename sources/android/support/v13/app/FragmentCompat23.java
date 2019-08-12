package android.support.v13.app;

import android.annotation.TargetApi;
import android.app.Fragment;

@TargetApi(23)
class FragmentCompat23 {
    public static void requestPermissions(Fragment fragment, String[] permissions2, int requestCode) {
        fragment.requestPermissions(permissions2, requestCode);
    }
}
