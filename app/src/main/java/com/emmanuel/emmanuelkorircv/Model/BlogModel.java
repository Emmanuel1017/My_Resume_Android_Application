package com.emmanuel.emmanuelkorircv.Model;

public class BlogModel  {
    //creating variables for our different fields.
    private String Thumbnail;
    private String Http;
    private String Date;
    private String Language;
    private String Title;
    private String Description;




    //getters
    public String getThumbnail() {
        return Thumbnail;
    }
    public String getHttp() {
        return Http;
    }
    public String getDate() {
        return Date;
    }
    public String getLanguage() {return Language;}
    public String  getTitle() {
        return Title;
    }
    public String getDescription() { return Description;}


    //setters
    public void setThumbnail(String thumbnail) {
        this.Thumbnail = thumbnail;
    }
    public void setHttp(String http) {
        this.Http = http;
    }
    public void setDate(String date) {
        this.Date = date;
    }
    public void setLanguage(String language) {
        this.Language = language;
    }
    public void setTitle(String title) {
        this.Title = title;
    }
    public void setDescription(String  description) {
        this.Description = description;
    }


    //creating an empty constructor.
    public BlogModel() {

    }




    public BlogModel(String thumbnail, String http, String date, String language, String title, String description)  {
        this.Thumbnail = thumbnail;
        this.Http = http;
        this.Date = date;
        this.Language = Date;
        this.Title = title;
        this.Description = description;
    }

}
