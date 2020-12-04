package models;

import java.util.List;

public class CarPostingWrapper{
    CarPosting carPosting;
    Car car;
    User user;
    List<String> carPhotos;

    public CarPosting getCarPosting() {
        return carPosting;
    }

    public void setCarPosting(CarPosting carPosting) {
        this.carPosting = carPosting;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getCarPhotos() {
        return carPhotos;
    }

    public void setCarPhotos(List<String> carPhotos) {
        this.carPhotos = carPhotos;
    }
}
