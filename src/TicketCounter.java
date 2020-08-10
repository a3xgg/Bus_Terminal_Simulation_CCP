import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketCounter{

    String counterName;
    Lock lock = new ReentrantLock();

    public TicketCounter(String counterName){
        this.counterName = counterName;
    }

    public void sellTicket(Customer customer){
        if(lock.tryLock()){
            System.out.println("Customer " + customer.getName() + " reached " + this.counterName);
            customer.getTicket = true;
            try{
                Thread.sleep(1500);
            }catch(Exception e){}
            System.out.println(this.counterName + " sold Ticket to Customer: " + customer.getName());
            lock.unlock();
        }
    }
}
