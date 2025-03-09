package michael.co.model;

public class GroceryShop extends BaseEntity {
    private String name;
    private String websiteURL;
    private int picture;

    public GroceryShop(String name, String websiteURL, int picture){
        setName(name);
        setWebsiteURL(websiteURL);
        setPicture(picture);
    }

    public String getName(){
        return this.name;
    }
    public String getWebsiteURL(){
        return this.websiteURL;
    }
    public int getImageResource(){
        return this.picture;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setWebsiteURL(String websiteURL){
        this.websiteURL = websiteURL;
    }
    public void setPicture(int picture){
        this.picture = picture;
    }
}
