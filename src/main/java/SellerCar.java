import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SellerCar {
    private final AutoShop autoShop;

    public SellerCar(AutoShop autoShop) {
        this.autoShop = autoShop;
    }

    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    Random random = new Random();
    int timeProduction = random.nextInt(1500);
    int timeWait = random.nextInt(1000);

    public void receiveCar() {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(timeProduction);
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " закручивает последние гайки");
                autoShop.getCars().add(new Car());
                System.out.println((Thread.currentThread().getName() + " выпустил новое авто"));
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public Car sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            lock.lock();
            while (autoShop.getCars().size() == 0) {
                System.out.println("Машин нет!");
                condition.await();
            }
            Thread.sleep(timeWait);
            System.out.println("Всего машин в салоне: " + autoShop.getCars().size());
            System.out.println(Thread.currentThread().getName() + " уехал на новегьком авто");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return autoShop.getCars().remove(0);
    }
}
