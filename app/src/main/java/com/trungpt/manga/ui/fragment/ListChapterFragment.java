package com.trungpt.manga.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import butterknife.InjectView;
import butterknife.OnItemClick;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.trungpt.manga.R;
import com.trungpt.manga.base.BaseFragment;
import com.trungpt.manga.base.event.ChangedFragmentEvent;
import com.trungpt.manga.sao.RestfulService;
import com.trungpt.manga.sao.RestfulServiceIn;
import com.trungpt.manga.sao.dto.ChapterDTO;
import com.trungpt.manga.sao.dto.StoryDTO;
import com.trungpt.manga.ui.fragment.adapter.CommonAdapter;
import com.trungpt.manga.ui.fragment.group.ChapterGroup;
import de.greenrobot.event.EventBus;
import retrofit.RestAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 5/30/2015.
 */
public class ListChapterFragment extends BaseFragment
{
    @InjectView(R.id.list_chapter_fragment_lvChapter)
    ListView listViewChapter;
    @InjectView(R.id.list_chapter_fragment_wheel)
    ProgressWheel progressWheel;
    CommonAdapter chapterAdapter;

    @Override
    public int getLayout()
    {
        return R.layout.list_chapter_fragment;
    }

    @Override
    public void setupView()
    {
        Bundle bundle = this.getArguments();
        final int storyId = bundle.getInt("storyId", -1);
        if (storyId != -1)
        {
            new AsyncTask<String, List<ChapterDTO>, List<ChapterDTO>>()
            {
                @Override
                protected void onPreExecute()
                {
                    super.onPreExecute();
                    progressWheel.setVisibility(View.VISIBLE);
                }

                @Override
                protected List<ChapterDTO> doInBackground(String... params)
                {
                    StoryDTO storyDTO = RestfulService.getInstance().getChapter(storyId);
                    List<ChapterDTO> chapterDTOs = new ArrayList<ChapterDTO>();
                    if (storyDTO != null)
                    {
                        chapterDTOs = storyDTO.getChapterDTOs();
                    }
                    return chapterDTOs;
                }

                @Override
                protected void onPostExecute( List<ChapterDTO> chapterDTOs)
                {
                    super.onPostExecute(chapterDTOs);
                    List<ChapterGroup> chapterGroups = ChapterGroup.convertFromChapterDTO(chapterDTOs);
                    chapterAdapter = new CommonAdapter(getActivity(), chapterGroups);
                    listViewChapter.setAdapter(chapterAdapter);
                    progressWheel.setVisibility(View.GONE);
                }
            }.execute();
        }
    }

    @OnItemClick(R.id.list_chapter_fragment_lvChapter)
    public void read(int position)
    {
        ReadStoryFragment readStoryFragment = new ReadStoryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("chapterId", ((ChapterGroup)chapterAdapter.getItem(position)).getChapterId());
        readStoryFragment.setArguments(bundle);
        EventBus.getDefault().post(new ChangedFragmentEvent(readStoryFragment));
    }
}
