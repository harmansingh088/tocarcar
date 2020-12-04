package models;

import java.sql.Date;

public class CarPosting {
    private int carPostingId;
    private Date postingDate;
    private String description;
    private double price;
    private int carId;
    private int ownerId;
    private int renteeId;
    private String status;

    public CarPosting(Date postingDate, String description, double price, int carId, int ownerId, String status) {
        this.postingDate = postingDate;
        this.description = description;
        this.price = price;
        this.carId = carId;
        this.ownerId = ownerId;
        this.status = status;
    }

    public int getCarPostingId() {
        return carPostingId;
    }

    public void setCarPostingId(int carPostingId) {
        this.carPostingId = carPostingId;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
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

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getRenteeId() {
        return renteeId;
    }

    public void setRenteeId(int renteeId) {
        this.renteeId = renteeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
