package com.trungpt.manga.sao.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 5/30/2015.
 */
public class ChapterDTO
{
    private int id;
    private String name;
    private int chapter;
    private String image;
    @SerializedName("html")
    private List<String> imageUrls;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getChapter()
    {
        return chapter;
    }

    public void setChapter(int chapter)
    {
        this.chapter = chapter;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public List<String> getImageUrls()
    {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls)
    {
        this.imageUrls = imageUrls;
    }
}
