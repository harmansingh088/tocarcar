package services;

import models.Car;
import models.CarPosting;
import models.CarPostingWrapper;
import models.User;

import java.sql.ResultSet;

public class CarPostingWrapperProvider {
    public static CarPostingWrapper getCarPostingWrapperObj(ResultSet rs) throws Exception{
        CarPosting carPosting = new CarPosting(
                rs.getDate("cp.postingDate"),
                rs.getString("cp.description"),
                rs.getDouble("cp.price"),
                rs.getInt("cp.carId"),
                rs.getInt("cp.ownerId"),
                rs.getString("cp.status")
        );
        carPosting.setCarPostingId(rs.getInt("cp.carPostingId"));

        Car newCar = new Car(
                rs.getString("c.company"),
                rs.getString("c.name"),
                rs.getString("c.colour"),
                rs.getInt("c.year"),
                rs.getString("c.description"),
                rs.getDouble("c.price"),
                rs.getInt("c.userId"));
        newCar.setCarId(rs.getInt("c.carId"));

        User user = new User(
                rs.getString("u.firstName"),
                rs.getString("u.lastName"),
                rs.getString("u.email"),
                "",
                rs.getString("u.userType"),
                rs.getString("u.phoneNumber"),
                rs.getInt("u.age")
        );


        CarPostingWrapper carPostingWrapperObj = new CarPostingWrapper();
        carPostingWrapperObj.setCarPosting(carPosting);
        carPostingWrapperObj.setCar(newCar);
        carPostingWrapperObj.setUser(user);

        return carPostingWrapperObj;
    }
}
