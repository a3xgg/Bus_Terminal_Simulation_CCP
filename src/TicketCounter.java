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
            System.out.println("("+java.time.LocalTime.now().withNano(0) + ")" + " Customer " + customer.getName() + " reached " + this.counterName);
            customer.getTicket = true;
            try{
                Thread.sleep(1500);
            }catch(Exception e){}
            System.out.println("("+java.time.LocalTime.now().withNano(0) + ") " + this.counterName + " sold Ticket to Customer: " + customer.getName());
            lock.unlock();
        }
    }
}
