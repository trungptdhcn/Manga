package com.trungpt.manga.sao.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 5/31/2015.
 */
public class ImageDTO
{
    @SerializedName("src")
    List<String> listImage = new ArrayList<>();

    public List<String> getListImage()
    {
        return listImage;
    }

    public void setListImage(List<String> listImage)
    {
        this.listImage = listImage;
    }
}
