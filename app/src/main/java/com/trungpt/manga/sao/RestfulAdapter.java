package com.trungpt.manga.sao;

import com.trungpt.manga.base.utils.Constants;
import retrofit.RestAdapter;
import retrofit.client.ApacheClient;

/**
 * Created by Trung on 7/22/2015.
 */
public class RestfulAdapter
{
    private static RestAdapter restAdapter;
    public static RestAdapter getRestAdapter()
    {
        if (restAdapter == null)
        {
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint(Constants.BASE_URL)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .build();
        }
        return restAdapter;
    }
}
