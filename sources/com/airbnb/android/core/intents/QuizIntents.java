package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.Activities;

public class QuizIntents {

    public static class DeepLinks {
        public static Intent deepLinkIntentForMythbusters(Context context, Bundle extras) {
            return new Intent(context, Activities.mythbusters());
        }
    }
}
