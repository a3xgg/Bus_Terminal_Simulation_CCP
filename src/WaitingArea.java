import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaitingArea {

    int maxCapacity;
    boolean isFullCapacity = false;
    Lock lock = new ReentrantLock();
    String waitingAreaName;

    public WaitingArea(String waitingAreaName){
        this.maxCapacity = 10;
        this.waitingAreaName = waitingAreaName;
    }

    public void enterWaitingArea(Customer customer){
        if(lock.tryLock()){
            System.out.println("(" + java.time.LocalTime.now().withNano(0) + " - WA) " + "Customer " + customer.getName() + " is headed towards " + waitingAreaName);
            try{
                Thread.sleep(0);
            } catch(Exception e){}
            System.out.println("(" + java.time.LocalTime.now().withNano(0) + " - WA) " + "Customer " + customer.getName() + " has reached " + waitingAreaName);
            maxCapacity -= 1;
            System.out.println("Current Number of available seats at " + this.waitingAreaName + ": " + maxCapacity);
            isFullCapacity = maxCapacity <= 0 ? true: false;
            lock.unlock();
        }
    }
}
