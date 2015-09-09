package com.trungpt.manga.ui.fragment.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.trungpt.manga.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 5/31/2015.
 */
public class PagerAdapter extends android.support.v4.view.PagerAdapter
{
    private List<String> urls = new ArrayList<>();
    private Context context;
    private ArrayList<View> views = new ArrayList<View>();

    public PagerAdapter(Context context, List<String> urls)
    {
        this.context = context;
        this.urls = urls;
    }

    @Override
    public int getItemPosition(Object object)
    {
        int index = views.indexOf(object);
        if (index == -1)
        {
            return POSITION_NONE;
        }
        else
        {
            return index;
        }
    }


    @Override
    public Object instantiateItem(final ViewGroup container, final int position)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View viewLayout = inflater.inflate(R.layout.read_story_pager_item, container, false);
        final ImageView ivStory = (ImageView) viewLayout.findViewById(R.id.read_story_pager_item_ivImage);
        final ProgressWheel progressWheel = (ProgressWheel) viewLayout.findViewById(R.id.read_story_pager_item_wheel);
        Picasso.with(context)
                .load(urls.get(position))
                .error(R.drawable.ic_launcher)
                .into(ivStory, new Callback()
                {
                    public void onSuccess()
                    {
                        ivStory.setVisibility(View.VISIBLE);
                        progressWheel.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError()
                    {
                        progressWheel.setVisibility(View.VISIBLE);
                        ivStory.setVisibility(View.GONE);
                    }
                });
        container.addView(viewLayout);
        return viewLayout;
    }

    @Override
    public int getCount()
    {
        return urls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object)
    {
        container.removeView((View) object);
    }

    public List<String> getUrls()
    {
        return urls;
    }

    public void setUrls(List<String> urls)
    {
        this.urls = urls;
    }
}
