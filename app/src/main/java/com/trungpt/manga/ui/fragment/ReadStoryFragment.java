package com.trungpt.manga.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import butterknife.InjectView;
import com.trungpt.manga.R;
import com.trungpt.manga.base.BaseFragment;
import com.trungpt.manga.sao.RestfulService;
import com.trungpt.manga.sao.RestfulServiceIn;
import com.trungpt.manga.sao.dto.ChapterDTO;
import com.trungpt.manga.ui.fragment.adapter.PagerAdapter;
import retrofit.RestAdapter;

import java.util.List;

/**
 * Created by Trung on 5/31/2015.
 */
public class ReadStoryFragment extends BaseFragment
{
    @InjectView(R.id.read_story_fragment_viewpager)
    ViewPager vpPager;
    PagerAdapter adapter;

    @Override
    public int getLayout()
    {
        return R.layout.read_story_fragment;
    }

    @Override
    public void setupView()
    {
        ((ActionBarActivity) getActivity()).getSupportActionBar().hide();
        Bundle bundle = this.getArguments();
        final int chapterId = bundle.getInt("chapterId", -1);
        if (chapterId != -1)
        {
            new AsyncTask<String, List<String>, List<String>>()
            {
                @Override
                protected List<String> doInBackground(String... params)
                {
                    ChapterDTO chapterDTO = RestfulService.getInstance().getReadStory(chapterId);
                    List<String> imageUrls = chapterDTO.getImageUrls();
                    return imageUrls;
                }

                @Override
                protected void onPostExecute(List<String> images)
                {
                    super.onPostExecute(images);
                    adapter = new PagerAdapter(getActivity(), images);
                    vpPager.setAdapter(adapter);
                }
            }.execute();
        }
    }

    @Override
    public boolean isVisibleToolbar()
    {
        return false;
    }
}
