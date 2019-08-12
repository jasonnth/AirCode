package com.imagepicker;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.ActivityCompat;
import android.support.v13.app.FragmentCompat;

public class EventForwarderFragment extends Fragment {
    private final Intent intent;
    private final ImagePickerModule module;
    private final int requestCode;

    static EventForwarderFragment startActivity(Activity currentActivity, Intent intent2, int requestCode2, ImagePickerModule module2) {
        EventForwarderFragment fragment = new EventForwarderFragment(intent2, requestCode2, module2);
        currentActivity.getFragmentManager().beginTransaction().add(fragment, null).commit();
        return fragment;
    }

    public EventForwarderFragment() {
        this.intent = null;
        this.requestCode = 0;
        this.module = null;
    }

    public EventForwarderFragment(Intent intent2, int requestCode2, ImagePickerModule module2) {
        this.intent = intent2;
        this.requestCode = requestCode2;
        this.module = module2;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.module != null) {
            int writePermission = ActivityCompat.checkSelfPermission(getActivity(), "android.permission.WRITE_EXTERNAL_STORAGE");
            int cameraPermission = ActivityCompat.checkSelfPermission(getActivity(), "android.permission.CAMERA");
            if (writePermission == 0 && cameraPermission == 0) {
                startActivityForResult(this.intent, this.requestCode);
                return;
            }
            FragmentCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA"}, 1);
        }
    }

    public void removeFragment() {
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }

    public void onActivityResult(int requestCode2, int resultCode, Intent data) {
        if (this.module != null) {
            this.module.onActivityResult(requestCode2, resultCode, data);
        } else {
            removeFragment();
        }
    }

    public void onRequestPermissionsResult(int permissionRequestCode, String[] permissions2, int[] grantResults) {
        Boolean denied = Boolean.valueOf(false);
        Boolean neverAskAgain = Boolean.valueOf(false);
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == -1) {
                denied = Boolean.valueOf(true);
                if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permissions2[i])) {
                    neverAskAgain = Boolean.valueOf(true);
                }
            }
        }
        if (denied.booleanValue()) {
            this.module.onPermisionDenied(neverAskAgain);
        } else {
            startActivityForResult(this.intent, this.requestCode);
        }
    }
}
