import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketCounter{

    String counterName;
    Lock lock = new ReentrantLock();

    public TicketCounter(String counterName){
        this.counterName = counterName;
    }

    public void sellTicket(Customer customer){

    }
}
