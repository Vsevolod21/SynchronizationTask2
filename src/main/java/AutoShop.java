import java.util.ArrayList;
import java.util.List;

public class AutoShop {
    SellerCar sellerCar = new SellerCar(this);
    List<Car> cars = new ArrayList<>(10);

    public Car sellCar() {
        return sellerCar.sellCar();
    }

    public void productCar() {
        sellerCar.receiveCar();
    }

    List<Car> getCars() {
        return cars;
    }
}
