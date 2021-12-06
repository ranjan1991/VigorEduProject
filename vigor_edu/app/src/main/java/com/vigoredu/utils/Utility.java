package com.vigoredu.utils;

import static com.vigoredu.app.APP.getContext;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.UiThread;

import com.vigoredu.R;
import com.vigoredu.app.APP;


public class Utility {


    //TODO code for Open Process Dialog...
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static ProgressDialog showProgress(Context context, String message) {
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(context, R.style.AppCompatAlertDialogStyle);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        if (!((Activity) context).isDestroyed())
            progressDialog.show();
        return progressDialog;
    }


    // TODO check Network connection...
    public static boolean isNetworkAvailable(boolean isShowMessage) {

        boolean isConnected;
        Context context = getContext();

        if (context == null) return false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();

        isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        if (!isConnected && isShowMessage) {
            showMessageS(context.getString(R.string.no_network));
        }

        return isConnected;

    }

    /**
     * Show Short toast Message.
     *
     * @param message description to be shown.
     */
    @UiThread
    public static void showMessageS(@NonNull String message) {

        if (TextUtils.isEmpty(message)) return;

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }

    /**
     * Show Long Toast Message.
     *
     * @param message description to be shown.
     */
    @UiThread
    public static void showMessageL(@NonNull String message) {

        if (TextUtils.isEmpty(message)) return;

        Toast.makeText(APP.getActivity(), message, Toast.LENGTH_LONG).show();

    }

    /**
     * Get Device Id.
     *
     * @return
     */
    public static boolean isValidString(String string) {
        return !TextUtils.isEmpty(string);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void performLogout(Activity activity) {
        //Prefs.instanceOf().clearPref();
        showMessageS(activity.getString(R.string.logout_success));
        activity.finishAffinity();

    }

    public static SharedPreferences preferencesFirstTimeLaunch;
    private static Utility utility;

    private Utility(Context context) {
        preferencesFirstTimeLaunch = context.getSharedPreferences(context.getPackageName(), 0);
    }

    public static Utility instanceOf(Context context) {
        if (utility == null) {
            utility = new Utility(context);
        }

        return utility;
    }


    // TODO code for get file path from URI...
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getPathFromUri(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

}
