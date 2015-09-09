package com.trungpt.manga.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import butterknife.InjectView;
import butterknife.OnItemClick;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.trungpt.manga.R;
import com.trungpt.manga.base.BaseFragment;
import com.trungpt.manga.base.event.ChangedFragmentEvent;
import com.trungpt.manga.base.listener.SearchListener;
import com.trungpt.manga.sao.RestfulService;
import com.trungpt.manga.sao.RestfulServiceIn;
import com.trungpt.manga.sao.dto.StoryDTO;
import com.trungpt.manga.ui.fragment.adapter.CommonAdapter;
import com.trungpt.manga.ui.fragment.group.StoryGroup;
import de.greenrobot.event.EventBus;
import retrofit.RestAdapter;

import java.util.List;

/**
 * Created by Trung on 5/30/2015.
 */
public class ListStoryFragment extends BaseFragment implements SearchListener
{
    @InjectView(R.id.list_story_fragment_grStory)
    GridView grStory;
    @InjectView(R.id.list_story_fragment_wheel)
    ProgressWheel progress;
    CommonAdapter storyAdapter;
    List<StoryGroup> storyGroups;
    @InjectView(R.id.multiple_actions)
    FloatingActionsMenu menuMultipleActions;
    @InjectView(R.id.action_a)
    FloatingActionButton btHot;
    @InjectView(R.id.action_b)
    FloatingActionButton btFavorite;

    @Override
    public int getLayout()
    {
        return R.layout.list_story_fragment;
    }

    @Override
    public void setupView()
    {
        btHot.setIcon(R.drawable.hot);
        btFavorite.setIcon(R.drawable.ic_fab_star);
        new AsyncTask<String, List<StoryDTO>, List<StoryDTO>>()
        {
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                progress.setVisibility(View.VISIBLE);
            }

            @Override
            protected List<StoryDTO> doInBackground(String... params)
            {
                List<StoryDTO> storyDTOs = RestfulService.getInstance().getListStory();
                return storyDTOs;
            }

            @Override
            protected void onPostExecute(List<StoryDTO> storyDTOs)
            {
                super.onPostExecute(storyDTOs);
                storyGroups = StoryGroup.convertFromStoryDTO(storyDTOs);
                storyAdapter = new CommonAdapter(getActivity(), storyGroups);
                grStory.setAdapter(storyAdapter);
                progress.setVisibility(View.GONE);
            }
        }.execute();
    }

    @OnItemClick(R.id.list_story_fragment_grStory)
    public void clickStory(int position)
    {
        StoryInformationFragment storyInformationFragment = new StoryInformationFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("storyId", ((StoryGroup) storyAdapter.getItem(position)).getStoryId());
        storyInformationFragment.setArguments(bundle);
        EventBus.getDefault().post(new ChangedFragmentEvent(storyInformationFragment));
    }

    @Override
    public void performSearch(String searchKey)
    {
        Log.e("Trung dai ca", "Trung dai ca");
    }
}
