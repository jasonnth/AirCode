package com.airbnb.android.core.controllers;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.airbnb.android.core.models.Trebuchet;
import java.util.List;

public class TrebuchetController {
    private final SharedPreferences trebuchetPreferences;

    public TrebuchetController(SharedPreferences trebuchetPreferences2) {
        this.trebuchetPreferences = trebuchetPreferences2;
    }

    public void update(List<Trebuchet> trebuchets) {
        Editor editor = this.trebuchetPreferences.edit().clear();
        for (Trebuchet trebuchet : trebuchets) {
            editor.putBoolean(trebuchet.getId(), trebuchet.isLaunch());
        }
        editor.apply();
    }
}
