package com.trungpt.manga.ui.fragment;

import android.graphics.*;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.InjectView;
import butterknife.OnClick;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.trungpt.manga.R;
import com.trungpt.manga.base.BaseFragment;
import com.trungpt.manga.base.event.ChangedFragmentEvent;
import com.trungpt.manga.base.utils.StringUtils;
import com.trungpt.manga.sao.RestfulService;
import com.trungpt.manga.sao.RestfulServiceIn;
import com.trungpt.manga.sao.dto.CategoriesDTO;
import com.trungpt.manga.sao.dto.ChapterDTO;
import com.trungpt.manga.sao.dto.StoryDTO;
import de.greenrobot.event.EventBus;
import retrofit.RestAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 5/31/2015.
 */
public class StoryInformationFragment extends BaseFragment
{
    @InjectView(R.id.information_fragment_tvAuthor)
    TextView tvAuthor;
    @InjectView(R.id.information_fragment_tvCountry)
    TextView tvCountry;
    @InjectView(R.id.information_fragment_ivCover)
    ImageView ivCover;
    @InjectView(R.id.information_fragment_tvNamOfStory)
    TextView tvNameOfStory;
    @InjectView(R.id.information_fragment_tvCategories)
    TextView tvCategories;
    @InjectView(R.id.information_fragment_wheel)
    ProgressWheel progress;
    @InjectView(R.id.information_fragment_wheelCoverPhoto)
    ProgressWheel progressCoverPhoto;
    @InjectView(R.id.information_fragment_btread)
    Button btReadNow;
    int storyId;


    @Override
    public int getLayout()
    {
        return R.layout.informaion_fragment;
    }

    @Override
    public void setupView()
    {
        Bundle bundle = this.getArguments();
        storyId = bundle.getInt("storyId", -1);
        if (storyId != -1)
        {
            new AsyncTask<String, StoryDTO, StoryDTO>()
            {
                @Override
                protected void onPreExecute()
                {
                    super.onPreExecute();
                    progress.setVisibility(View.VISIBLE);
                }

                @Override
                protected StoryDTO doInBackground(String... params)
                {
                    StoryDTO storyDTO = RestfulService.getInstance().getChapter(storyId);
                    List<ChapterDTO> chapterDTOs = new ArrayList<ChapterDTO>();
                    if (storyDTO != null)
                    {
                        chapterDTOs = storyDTO.getChapterDTOs();
                    }
                    return storyDTO;
                }

                @Override
                protected void onPostExecute(StoryDTO storyDTO)
                {
                    super.onPostExecute(storyDTO);
                    Typeface type2 = Typeface.createFromAsset(getActivity().getAssets(), "mangatb.ttf");
                    String urlCover = storyDTO.getCover();
                    String author = "";
                    String storyName = "";
                    String category = "";
                    if (storyDTO.getAuthorDTO() != null)
                    {
                        author = storyDTO.getAuthorDTO().getName();
                    }
                    String country = storyDTO.getCountryDTO().getName();
                    if (storyDTO.getCountryDTO() != null)
                    {
                        storyName = storyDTO.getName();
                    }
                    if (storyDTO.getCategoriesDTOs() != null && storyDTO.getCategoriesDTOs().size() > 0)
                    {
                        for (CategoriesDTO categoriesDTO : storyDTO.getCategoriesDTOs())
                        {
                            if (StringUtils.isNotEmpty(categoriesDTO.getName()))
                            {
                                if(StringUtils.isNotEmpty(category))
                                {
                                    category = category + ", " + categoriesDTO.getName();
                                }
                                else
                                {
                                    category = category + categoriesDTO.getName();
                                }
                            }
                        }
                    }
                    tvAuthor.setTypeface(type2);
                    tvCountry.setTypeface(type2);
                    checkEmpty(author, tvAuthor);
                    checkEmpty(country, tvCountry);
                    checkEmpty(category, tvCategories);
                    Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "crowntitle.ttf");
                    Shader textShader = new LinearGradient(0, 0, 0, 20,
                            new int[]{Color.BLACK, Color.RED, Color.BLACK},
                            new float[]{0, 1, 2}, Shader.TileMode.MIRROR);
                    tvNameOfStory.getPaint().setShader(textShader);
                    tvNameOfStory.setTypeface(type);
                    tvNameOfStory.setText(storyName);
                    Display display = getActivity().getWindowManager().getDefaultDisplay();
                    Point size = new Point();
                    display.getSize(size);
                    int width = size.x;
                    int height = size.x;
                    if (StringUtils.isNotEmpty(urlCover))
                    {
                        Picasso.with(getActivity())
                                .load(urlCover)
                                .centerCrop()
                                .resize(width, height / 3)
                                .into(ivCover, new Callback()
                                {
                                    public void onSuccess()
                                    {
                                        ivCover.setVisibility(View.VISIBLE);
                                        progressCoverPhoto.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onError()
                                    {
                                        progressCoverPhoto.setVisibility(View.VISIBLE);
                                        ivCover.setVisibility(View.GONE);
                                    }
                                });
                    }
                    else
                    {

                    }
                    progress.setVisibility(View.GONE);
                }
            }.execute();
        }
    }

    public void checkEmpty(String s, TextView tv)
    {
        if (StringUtils.isNotEmpty(s))
        {
            tv.setText(s);
        }
    }

    @OnClick(R.id.information_fragment_btread)
    public void readNow()
    {
        ListChapterFragment listChapterFragment = new ListChapterFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("storyId", storyId);
        listChapterFragment.setArguments(bundle);
        EventBus.getDefault().post(new ChangedFragmentEvent(listChapterFragment));
    }

}
