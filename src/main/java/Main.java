public class Main {
    public static void main(String[] args) throws InterruptedException {

        final AutoShop autoShop = new AutoShop();

        Thread threadBuyer1 = new Thread(null, autoShop::sellCar, "Покупатель 1");
        Thread threadBuyer2 = new Thread(null, autoShop::sellCar, "Покупатель 2");
        Thread threadBuyer3 = new Thread(null, autoShop::sellCar, "Покупатель 3");

        Thread threadProductCar = new Thread(null, autoShop::productCar, "Производитель Тойота");

        threadBuyer1.start();
        threadBuyer2.start();
        threadBuyer3.start();

        Thread.sleep(1000);

        threadProductCar.start();




    }
}
