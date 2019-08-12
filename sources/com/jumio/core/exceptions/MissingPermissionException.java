package com.jumio.core.exceptions;

import java.util.Arrays;

public class MissingPermissionException extends Exception {
    private String[] mPermissions;

    public MissingPermissionException(String[] whichPermissions) {
        super(createMessage(whichPermissions));
        this.mPermissions = (String[]) Arrays.copyOf(whichPermissions, whichPermissions.length);
    }

    public MissingPermissionException(String permission) {
        this(new String[]{permission});
        this.mPermissions = new String[]{permission};
    }

    private static String createMessage(String[] whichPermissions) {
        StringBuilder str = new StringBuilder("On devices running Android Marshmallow (6.0) you need to acquire the following permissions dynamically before starting the SDK: ");
        for (String p : whichPermissions) {
            str.append(p).append("\n");
        }
        str.append("On any other API level permissions must be declared in the AndroidManifest. More information about that can be found here: https://developer.android.com/training/permissions/requesting.html");
        return str.toString();
    }

    public String[] getPermissions() {
        return this.mPermissions;
    }
}
