package com.trungpt.manga.sao;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.trungpt.manga.sao.dto.ChapterDTO;
import com.trungpt.manga.sao.dto.StoryDTO;
import retrofit.http.GET;
import retrofit.http.Path;

import java.io.IOException;
import java.util.List;

/**
 * Created by Trung on 5/30/2015.
 */
public interface RestfulServiceIn
{

//    public static String getMethod(OkHttpClient client, String url) throws IOException
//    {
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//
//        Response response = client.newCall(request).execute();
//        return response.body().string();
//    }

    @GET("/api/v1/story")
    public List<StoryDTO> getListStory();

    @GET("/api/v1/story/{chapter}")
    public StoryDTO getChapter(@Path("chapter") int chapter);


    @GET("/api/v1/chapter/{chapter}")
    public ChapterDTO getReadStory(@Path("chapter") int chapter);
}
