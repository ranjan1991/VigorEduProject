package com.vigoredu.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;


import com.vigoredu.utils.Utility;

import io.reactivex.disposables.CompositeDisposable;

public class BaseFragment extends Fragment {
    protected String TAG = getClass().getSimpleName();
    protected CompositeDisposable mCompositeDisposable;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disposeAllRxSubs();
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Log.i(TAG, "onCreateAnimation");
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i(TAG, "setUserVisibleHint " + isVisibleToUser + " - " + isResumed());
    }

    /**
     * Dispose All Rx Subs.
     */
    private void disposeAllRxSubs() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed())
            mCompositeDisposable.dispose();
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
            progressDialog = Utility.showProgress(getContext(), "Please wait..");
        }
    }
}