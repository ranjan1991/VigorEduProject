package com.vigoredu;


import static com.vigoredu.utils.Constants._action_auth_failed;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.vigoredu.utils.Logger;
import com.vigoredu.utils.Utility;

import io.reactivex.disposables.CompositeDisposable;


public class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    protected CompositeDisposable mCompositeDisposable;
    private ProgressDialog progressDialog;
    private BroadcastReceiver mLogoutBroadcast = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent == null) return;

            if (Utility.isValidString(intent.getAction())) {
                switch (intent.getAction()) {
                    case _action_auth_failed:
                        Utility.performLogout(BaseActivity.this);
                        break;
                }
            } else {
                Logger.e(TAG, "action not found");
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disposeAllRxSubs();
        mCompositeDisposable = new CompositeDisposable();
    }

    //     TODO Load new fragments with Animation....
    public void OpenFragmentWithBottomToTopAnim(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_out_up, R.anim.slide_in_up);
        ft.replace(R.id.frame, fragment).addToBackStack("").commit();

//                ft.add(R.id.frame, fragment).addToBackStack("").commit();
    }

    // TODO Load new fragments....
    public void OpenFragment(Fragment fragment, Bundle bundle) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        ft.replace(R.id.frame, fragment).addToBackStack(null).commit();

//                ft.add(R.id.frame, fragment).addToBackStack("").commit();
    }

    // TODO Load new fragments....
    public void OpenWithStackFragment(Fragment fragment, Bundle bundle) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
//        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        ft.replace(R.id.frame, fragment).addToBackStack(null).commit();

//                ft.add(R.id.frame, fragment).addToBackStack("").commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposeAllRxSubs();
    }

    /**
     * Dispose All Rx Subs.
     */
    private void disposeAllRxSubs() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed())
            mCompositeDisposable.dispose();
    }

    public void showProgressBar(ProgressBar progressBar) {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressBar(ProgressBar progressBar) {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    /**
     * Method is used to hide progressbar
     **/
    public void hideProgressDialogue() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * Method is used for showing progressbar
     **/
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void showProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            return;
        } else {
            progressDialog = Utility.showProgress(this, "Please wait..");
        }
    }

    @NonNull
    private IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(_action_auth_failed);
        return intentFilter;
    }
}
