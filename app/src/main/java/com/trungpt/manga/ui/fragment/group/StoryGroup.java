package com.trungpt.manga.ui.fragment.group;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.trungpt.manga.R;
import com.trungpt.manga.base.utils.StringUtils;
import com.trungpt.manga.sao.dto.StoryDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 5/30/2015.
 */
public class StoryGroup extends Group
{
    private int storyId;
    private String storyName;
    private String urlImageCorver;

    @Override
    public int getLayout()
    {
        return R.layout.story_item;
    }

    @Override
    public void findById(ViewHolder viewHolder, View view)
    {
        ImageView imageView = (ImageView) view.findViewById(R.id.story_item_ivCover);
        TextView tvName = (TextView) view.findViewById(R.id.story_item_tvStoryName);
        ProgressWheel progressWheel = (ProgressWheel) view.findViewById(R.id.story_item_wheelCoverPhoto);
        viewHolder.addView(imageView);
        viewHolder.addView(tvName);
        viewHolder.addView(progressWheel);
    }

    @Override
    public void setDataToView(ViewHolder viewHolder, View view)
    {
        final ImageView imageView = (ImageView) viewHolder.getView(R.id.story_item_ivCover);
        TextView tvName = (TextView) viewHolder.getView(R.id.story_item_tvStoryName);
        final ProgressWheel progressWheel = (ProgressWheel) viewHolder.getView(R.id.story_item_wheelCoverPhoto);
        tvName.setText(storyName);
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        if (StringUtils.isNotEmpty(urlImageCorver))
        {
            Picasso.with(getContext())
                    .load(urlImageCorver)
                    .placeholder(R.drawable.test2)
                    .error(R.drawable.test2)
                    .centerCrop()
                    .resize(width / 4, height / 4)
                    .tag(getContext())
                    .into(imageView, new Callback()
                    {
                        public void onSuccess()
                        {
                            imageView.setVisibility(View.VISIBLE);
                            progressWheel.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError()
                        {
                            progressWheel.setVisibility(View.VISIBLE);
                            imageView.setVisibility(View.GONE);
                        }
                    });
        }
        else
        {
            progressWheel.setVisibility(View.GONE);
            imageView.setImageResource(R.drawable.test2);
        }

    }

    public String getStoryName()
    {
        return storyName;
    }

    public void setStoryName(String storyName)
    {
        this.storyName = storyName;
    }

    public int getStoryId()
    {
        return storyId;
    }

    public void setStoryId(int storyId)
    {
        this.storyId = storyId;
    }

    public String getUrlImageCorver()
    {
        return urlImageCorver;
    }

    public void setUrlImageCorver(String urlImageCorver)
    {
        this.urlImageCorver = urlImageCorver;
    }

    public static List<StoryGroup> convertFromStoryDTO(List<StoryDTO> storyDTOList)
    {
        List<StoryGroup> storyGroups = new ArrayList<>();
        for (StoryDTO storyDTO : storyDTOList)
        {
            StoryGroup storyGroup = new StoryGroup();
            storyGroup.setStoryName(storyDTO.getName());
            storyGroup.setStoryId(storyDTO.getId());
            storyGroup.setUrlImageCorver(storyDTO.getImage());
            storyGroups.add(storyGroup);
        }
        return storyGroups;
    }
}
