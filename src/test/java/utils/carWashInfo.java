package utils;

public class carWashInfo {
    private String name;
    private String phone;
    private int rating;

    public carWashInfo(String name, String phone, int rating) {
        this.name = name;
        this.phone = phone;
        this.rating = rating;
        
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getRating() {
        return rating;
    }
}
