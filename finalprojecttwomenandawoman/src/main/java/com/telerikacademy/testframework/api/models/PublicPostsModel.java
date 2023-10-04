package com.telerikacademy.testframework.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PublicPostsModel{
    public int postId;
    public String content;
    public String picture;
    public String date;
    public ArrayList<Object> likes;
    public ArrayList<Object> comments;
    public int rank;
    public CategoryModel category;
    @SerializedName("public")
    public boolean mypublic;
}
