package com.trungpt.manga.sao.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung on 5/30/2015.
 */
public class StoryDTO
{
    private int id;
    private String name;
    private String image;
    private String cover;
    @SerializedName("chapters")
    List<ChapterDTO> chapterDTOs = new ArrayList<>();
    @SerializedName("authors")
    AuthorDTO authorDTO;
    @SerializedName("countries")
    CountryDTO countryDTO;
    @SerializedName("categories")
    List<CategoriesDTO> categoriesDTOs= new ArrayList<>();

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

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public List<ChapterDTO> getChapterDTOs()
    {
        return chapterDTOs;
    }

    public void setChapterDTOs(List<ChapterDTO> chapterDTOs)
    {
        this.chapterDTOs = chapterDTOs;
    }

    public AuthorDTO getAuthorDTO()
    {
        return authorDTO;
    }

    public void setAuthorDTO(AuthorDTO authorDTO)
    {
        this.authorDTO = authorDTO;
    }

    public CountryDTO getCountryDTO()
    {
        return countryDTO;
    }

    public void setCountryDTO(CountryDTO countryDTO)
    {
        this.countryDTO = countryDTO;
    }

    public String getCover()
    {
        return cover;
    }

    public void setCover(String cover)
    {
        this.cover = cover;
    }


    public List<CategoriesDTO> getCategoriesDTOs()
    {
        return categoriesDTOs;
    }

    public void setCategoriesDTOs(List<CategoriesDTO> categoriesDTOs)
    {
        this.categoriesDTOs = categoriesDTOs;
    }
}
