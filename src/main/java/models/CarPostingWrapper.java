package models;

public class CarPostingWrapper{
    CarPosting carPosting;
    Car car;

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
}
