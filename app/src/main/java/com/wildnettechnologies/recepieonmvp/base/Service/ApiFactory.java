package com.wildnettechnologies.recepieonmvp.base.Service;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiFactory {
    private static String BASE_URL =  "http://services.groupkt.com/";

    private static ApiInterface sApiInterface;

    public static ApiInterface getRecepieClient()
    {
        Interceptor interceptor = new Interceptor()
        {
            @Override
            public Response intercept(Chain chain) throws IOException
            {
                Request newRequest = chain.request().newBuilder().build();
                return chain.proceed(newRequest);
            }
        };

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.interceptors().add(interceptor);

        if(sApiInterface == null)
        {
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(BASE_URL);
            builder.client(client.build());
            builder.addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();

            sApiInterface = retrofit.create(ApiInterface.class);
        }
        return sApiInterface;
    }
}
