package com.example.newsreader;

/**
 * Created by Ralph_Chao on 2016/12/15.
 */

public class NewsData {
    private String title;
    private String content;
    private String url;
    private float stars;
    private int commentNum;
    private int id;

    public NewsData(int inputId, String inputTitle, String inputContent, String inputUrl, float inputStars, int inputCommentNum) {
        this.id = inputId;
        this.title = inputTitle;
        this.content = inputContent;
        this.url = inputUrl;
        this.stars = inputStars;
        this.commentNum = inputCommentNum;
    }

    public NewsData(String inputTitle, String inputContent, String inputUrl, float inputStars, int inputCommentNum) {
        this.title = inputTitle;
        this.content = inputContent;
        this.url = inputUrl;
        this.stars = inputStars;
        this.commentNum = inputCommentNum;
    }

    public void setTitle(String inputTitle) {
        this.title = inputTitle;
    }

    public void setContent(String inputContent) {
        this.content = inputContent;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public float getStars() {
        return stars;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public int getID() {
        return id;
    }

}
