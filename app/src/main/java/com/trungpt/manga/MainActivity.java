package com.trungpt.manga;

import com.trungpt.manga.base.BaseActivity;
import com.trungpt.manga.base.BaseFragment;
import com.trungpt.manga.ui.fragment.ListStoryFragment;

/**
 * Created by Trung on 7/22/2015.
 */
public class MainActivity extends BaseActivity
{
    @Override
    public BaseFragment getDefaultFragment()
    {
        return new ListStoryFragment();
    }
}
