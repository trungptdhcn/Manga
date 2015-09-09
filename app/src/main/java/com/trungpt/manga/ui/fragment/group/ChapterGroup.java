package com.trungpt.manga.ui.fragment.group;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.trungpt.manga.R;
import com.trungpt.manga.sao.dto.ChapterDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 5/30/2015.
 */
public class ChapterGroup extends Group
{
    private String chapterName;
    private int chapterId;

    @Override
    public int getLayout()
    {
        return R.layout.chapter_item;
    }

    @Override
    public void findById(ViewHolder viewHolder, View view)
    {
        TextView tvName = (TextView) view.findViewById(R.id.chapter_item_tvChapter);
        viewHolder.addView(tvName);
    }

    @Override
    public void setDataToView(ViewHolder viewHolder, View view)
    {
        TextView tvName = (TextView) viewHolder.getView(R.id.chapter_item_tvChapter);
        tvName.setText(chapterName);
    }

    public String getChapterName()
    {
        return chapterName;
    }

    public void setChapterName(String chapterName)
    {
        this.chapterName = chapterName;
    }

    public int getChapterId()
    {
        return chapterId;
    }

    public void setChapterId(int chapterId)
    {
        this.chapterId = chapterId;
    }

    public static List<ChapterGroup> convertFromChapterDTO(List<ChapterDTO> chapterDTOs)
    {
        List<ChapterGroup> chapterGroups = new ArrayList<>();
        for (ChapterDTO chapterDTO : chapterDTOs)
        {
            ChapterGroup chapterGroup = new ChapterGroup();
            chapterGroup.setChapterName(chapterDTO.getName());
            chapterGroup.setChapterId(chapterDTO.getId());
            chapterGroups.add(chapterGroup);
        }
        return chapterGroups;
    }
}
