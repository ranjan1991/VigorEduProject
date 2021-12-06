package com.vigoredu.preferences;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.vigoredu.app.APP;


public class Pref {
    private static Pref prefs;
    private SharedPreferences sharedPreferences;

    private Pref() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(APP.getContext());
    }

    public static Pref instanceOf() {
        if (prefs == null) {
            prefs = new Pref();
        }
        return prefs;
    }

    /**
     * Get data from prefs with key {key} & of type {obj}
     *
     * @param key
     * @param obj
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getDataWithKey(String key, T obj) {
        if (obj instanceof String) {
            return (T) sharedPreferences.getString(key, (String) obj);
        } else if (obj instanceof Integer) {
            return (T) (Integer) sharedPreferences.getInt(key, (Integer) obj);
        } else if (obj instanceof Boolean) {
            return (T) (Boolean) sharedPreferences.getBoolean(key, (Boolean) obj);
        }
        return null;
    }

    /**
     * Save data to prefs with key {key} & of type {obj}
     *
     * @param key
     * @param obj
     * @param <T>
     * @return
     */
    public <T> void putDataWithKey(String key, T obj) {
//        Logger.e("Key:"+key,"Value:"+obj);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (obj instanceof String) {
            editor.putString(key, (String) obj);
        } else if (obj instanceof Integer) {
            editor.putInt(key, (Integer) obj);
        } else if (obj instanceof Boolean) {
            editor.putBoolean(key, (Boolean) obj);
        } else if (obj instanceof Float) {
            editor.putFloat(key, (Float) obj);
        }
        editor.commit();
    }

    /*
     * Set and Get Pref ProjectListData
     * */
    // TODO UserID set and get...
    public int getEmployID() {
        return getDataWithKey(Keys.USER_ID, 0);
    }

    public void setEmployID(int userId) {
        // LogUtils.error("userNmPREF==", user_name);
        putDataWithKey(Keys.USER_ID, userId);
    }

    // TODO User mobile set and get...
    public String getUserMobile() {
        return getDataWithKey(Keys.USER_MOBILE, "");
    }

    public void setUserMobile(String mobile) {
        // LogUtils.error("userNmPREF==", user_name);
        putDataWithKey(Keys.USER_MOBILE, mobile);
    }


    // TODO User name set and get...
    public String getUserName() {
        return getDataWithKey(Keys.USER_NAME, "");
    }

    public void setUserName(String userName) {
        putDataWithKey(Keys.USER_NAME, userName);
    }


    public String getDeviceId() {
        return getDataWithKey(Keys.USER_DEVICE_ID, "");
    }

    public void setDeviceId(String device_id) {
        putDataWithKey(Keys.USER_DEVICE_ID, device_id);
    }


    public int getAttendanceId() {
        return getDataWithKey(Keys.USER_ATTENDANCE_ID, 0);
    }

    public void setAttendanceId(int attendance_id) {
        putDataWithKey(Keys.USER_ATTENDANCE_ID, attendance_id);
    }

    public void clearPref() {
        sharedPreferences.edit().clear().commit();
    }

    //TODO This Class will contains all keys going to use in preferences.
    public static class Keys {
        static final String USER_ID = "userId";
        static final String USER_NAME = "userName";
        static final String USER_MOBILE = "mobile";
        static final String USER_DEVICE_ID = "device_id";
        static final String USER_ATTENDANCE_ID = "attendance_id";

    }
}
