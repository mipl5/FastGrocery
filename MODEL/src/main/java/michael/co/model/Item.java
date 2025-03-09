package michael.co.model;

public class Item extends BaseEntity {
    // class members:
    private String name;
    private double price;
    private int imageResource;
    // constructors:
    public Item(String name, double price, int imageResource){
        setName(name);
        setPrice(price);
        setImageResource(imageResource);
    }
    // getters:
    public String getName(){
        return this.name;
    }
    public double getPrice(){
        return this.price;
    }
    public int getImageResource(){
        return this.imageResource;
    }
    // setters:
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setImageResource(int imageResource){
        this.imageResource = imageResource;
    }
}
