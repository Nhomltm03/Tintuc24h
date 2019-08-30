package com.example.tintuc24h.Interface;

import com.example.tintuc24h.Common.Common;
import com.example.tintuc24h.Model.News;
import com.example.tintuc24h.Model.WebSite;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface NewsService {

@GET("v2/sources?language=en&apiKey="+ Common.API_KEY)
Call<WebSite> getSources();

    @GET
    Call<News> getNewestArticles(@Url String url);
}
