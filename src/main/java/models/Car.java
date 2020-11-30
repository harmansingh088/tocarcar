package models;

public class Car {
    private int carId;
    private String company;
    private String name;
    private int year;
    private String description;
    private double price;
    private int userId;

    public Car(int carId, String company, String name, int year, String description, double price, int userId) {
        this.carId = carId;
        this.company = company;
        this.name = name;
        this.year = year;
        this.description = description;
        this.price = price;
        this.userId = userId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
