package com.vigoredu.app;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.vigoredu.BuildConfig;
import com.vigoredu.utils.Logger;

public class APP extends MultiDexApplication {
    private static final String TAG = APP.class.getSimpleName();
    private static Context mApplicationContext;
    private static APP mActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = this;
        mActivity = (APP) getContext();
        MultiDex.install(this);
        setupBuildInfo();


    }

    /**
     * Get Application Context.
     *
     * @return
     */
    public static Context getContext() {
        return mApplicationContext;
    }

    public static APP getActivity() {
        return mActivity;
    }

    /**
     * This will initialize image loader for applicaton.
     */
    /**
     * Just to display app info
     */
    private void setupBuildInfo() {
        Logger.i(TAG, "Flavor : " + BuildConfig.FLAVOR +
                "\nBuild version code : " + BuildConfig.VERSION_CODE +
                "\nBuild version name : " + BuildConfig.VERSION_NAME);
        Logger.i(TAG, "Base Url : " + BuildConfig.BASE_URL);
    }


}
