package com.emmanuel.emmanuelkorircv.Model;

public class ExperiencesModel {
    //creating variables for our different fields.
    private String Id;
    private String Position;
    private String StartAt;
    private String EndAt;
    private String Company_Name;
    private String Website;
    private String Logo;
    private String Language;
    private String City;
    private String Country;
    private String Role;
    private String Description;

    private String[] Technologies;

    private String[] Icon;
    private String[] Title;
    private String[] Http;



    //getters
    public String getId() {
        return Id;
    }
    public String getPosition() {
        return Position;
    }
    public String getStartAt() {
        return StartAt;
    }
    public String getEndAt() {
        return EndAt;
    }
    public String getCompany_Name() {
        return Company_Name;
    }
    public String getWebsite() {
        return Website;
    }
    public String getLogo() {
        return Logo;
    }
    public String getLanguage() {
        return Language;
    }
    public String getCity() {
        return City;
    }
    public String getCountry() {
        return Country;
    }
    public String  getRole() {
        return Role;
    }

    public String getDescription() {
        return Description;
    }


    public String[] getTechnologies() {
        return Technologies;
    }

    public String[] getIcon() {
        return Icon;
    }

    public String[] getTitle() {
        return Title;
    }

    public String[] getHttp() {
        return Http;
    }



    //setters
    public void setId(String Id) {
        this.Id = Id;
    }
    public void setPosition(String Position) {
        this.Position = Position;
    }

    public void setStartAt(String StartAt) {
        this.StartAt = StartAt;
    }
    public void setEndAt(String EndAt) {
        this.EndAt = EndAt;
    }
    public void setCompany_Name(String Company_Name) {
        this.Company_Name = Company_Name;
    }
    public void setWebsite(String Website) {
        this.Website = Website;
    }
    public void setLogo(String logo) {
        this.Logo = logo;
    }
    public void setLanguage(String language) {
        this.Language = language;
    }
    public void setCity(String city) {
        this.City = city;
    }
    public void setCountry(String City) {
        this.Country = Country;
    }
    public void setRole(String Role) {
        this.Role = Role;
    }
    public void setDescription(String  description) {
        this.Description = description;
    }

    public void setTechnologies(String[]  technologies) {
        this.Technologies = technologies;
    }


    public void setIcon(String[]  icon) {
        this.Icon = icon;
    }

    public void setTitle(String[]  title) {
        this.Title = title;
    }

    public void setHttp(String[]  http) {
        this.Http = http;
    }


    //creating an empty constructor.
    public ExperiencesModel() {

    }




    public ExperiencesModel(String Id, String Position, String StartAt,String EndAt,String Company_Name, String Website, String Logo, String Language,String City, String Country, String Role, String description, String[] Technologies, String[] Icon, String[] Title ,String[] Http)  {
        this.Id = Id;
        this.Position = Position;
        this.StartAt = StartAt;
        this.EndAt = EndAt;
        this.Company_Name =  Company_Name;
        this.Website = Website;
        this.Logo = Logo;
        this.Language = Language;
        this.City = City;
        this.Country =  Country;
        this.Role = Role;
        this.Description = description;
        this.Technologies = Technologies;
        this.Icon = Icon;
        this.Title = Title;
        this.Http = Http;
    }

}
