package com.luba.todo.privatbank.download;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

public interface RetrofitInterface {

    @GET("p24api/pubinfo?json&exchange&coursid=5")
    @Streaming
    Call<ResponseBody> downloadFile();

}


