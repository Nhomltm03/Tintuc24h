package com.example.tintuc24h.Common;

import com.example.tintuc24h.Interface.IconBetterIdeaService;
import com.example.tintuc24h.Interface.NewsService;
import com.example.tintuc24h.Model.IconBetterIdea;
import com.example.tintuc24h.Remote.IconBetterIdeaClient;
import com.example.tintuc24h.Remote.RetrofitClient;

import retrofit2.Retrofit;

public class Common {
     private  static final String BASE_URL = " https://newsapi.org/";
     public static final String API_KEY = "7c988e9136a0403e923f294a123f2995";

     public static NewsService getNewsService(){
         return RetrofitClient.getClient(BASE_URL).create(NewsService.class);
     }

    public static IconBetterIdeaService getIconService(){
        return IconBetterIdeaClient.getClient().create(IconBetterIdeaService.class);
    }

    public static String getAPIUrl(String source,String sortBy,String apiKEY)
    {
        StringBuilder apiUrl = new StringBuilder("https://newsapi.org/v2/top-headlines?sources=");
        return apiUrl.append(source)
                .append("&apiKey=")
                .append(apiKEY)
                .toString();
    }
}
