package com.vigoredu.utils;



import static com.vigoredu.utils.Constants.MESSAGE_IS_NULL;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Size;

import com.vigoredu.BuildConfig;


public class Logger {

    public static void e(@NonNull @Size(min = 1, max = 23) String tag, @NonNull String message) {
        if (BuildConfig.LOGGING) {
            if (TextUtils.isEmpty(message)) message = MESSAGE_IS_NULL;
            Log.e(tag, message);
        }
    }

    public static void d(@NonNull @Size(min = 1, max = 23) String tag, @NonNull String message) {
        if (BuildConfig.LOGGING) {
            if (TextUtils.isEmpty(message)) message = MESSAGE_IS_NULL;
            Log.d(tag, message);
        }
    }

    public static void i(@NonNull @Size(min = 1, max = 23) String tag, @NonNull String message) {
        if (BuildConfig.LOGGING) {
            if (TextUtils.isEmpty(message)) message = MESSAGE_IS_NULL;
            Log.i(tag, message);
        }
    }


    public static void w(@NonNull @Size(min = 1, max = 23) String tag, @NonNull String message) {
        if (BuildConfig.LOGGING) {
            if (TextUtils.isEmpty(message)) message = MESSAGE_IS_NULL;
            Log.w(tag, message);
        }
    }


    public static void v(@NonNull @Size(min = 1, max = 23) String tag, @NonNull String message) {
        if (BuildConfig.LOGGING) {
            if (TextUtils.isEmpty(message)) message = MESSAGE_IS_NULL;
            Log.v(tag, message);
        }
    }

}
