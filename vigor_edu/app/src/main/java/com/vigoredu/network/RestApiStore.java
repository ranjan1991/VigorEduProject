package com.vigoredu.network;

import com.vigoredu.response.coordinates.CoordinatesResponseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

interface RestApiStore {

    @FormUrlEncoded
    @POST("employee/coordinates")
    Observable<CoordinatesResponseBean> performUpdateCoordinates(@Field(NetworkConstants.API_TOKEN) String api_token,
                                                                 @Field(NetworkConstants.API_EMPLOYEE_ID) int employee_id);


}

