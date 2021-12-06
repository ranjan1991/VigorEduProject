package com.vigoredu.network;
import com.vigoredu.preferences.Pref;
import com.vigoredu.response.coordinates.CoordinatesResponseBean;

import io.reactivex.Observable;


public class ApiHelper {
    private static final String TAG = ApiHelper.class.getSimpleName();

    public static Observable<CoordinatesResponseBean> performUpdateCoordinates() {
        return RestClient.getInstance().getRestAPIStore().performUpdateCoordinates(NetworkConstants.API_TOKEN_VALUE, Pref.instanceOf().getEmployID());
    }

}
