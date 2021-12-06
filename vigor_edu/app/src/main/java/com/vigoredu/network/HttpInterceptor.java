package com.vigoredu.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class HttpInterceptor implements Interceptor {

    private static final String TAG = HttpInterceptor.class.getSimpleName();
    private final OkHttpClient httpClient;

    HttpInterceptor(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        //Build new request
        Request.Builder builder = request.newBuilder();
        request = builder.build(); //overwrite old request
//        Logger.e("==API NameImg", request.url() + "====");
        if (!request.url().toString().equalsIgnoreCase("https://fpgindia.in/api/Fpg/v1/user/update-profile-photo")) {
            builder.header(NetworkConstants.KEY_CONTENT_TYPE, NetworkConstants.API_CONTENT_TYPE_VALUE);
            builder.header(NetworkConstants.KEY_ACCEPT, NetworkConstants.API_ACCEPT_VALUE);
        }

//        String token = NetworkConstants.TOKEN; //save token of this request for future
//        setAuthHeader(builder, token); //write current token to request

        Response response = chain.proceed(request); //perform request, here original request will be executed
        if (response.code() == 401) { //if unauthorized
            synchronized (httpClient) { //perform all 401 in sync blocks, to avoid multiply token updates
                // logout();             //go to login screen
            }
        }

        try {
            //commented intentionally as can be used later.
//            printLogs(chain, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    /**
     * This method can be used to log http request and response.
     *
     * @param chain
     * @param request
     * @param response
     */

}
