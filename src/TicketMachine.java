import java.util.Random;

/* Ticket Machine that extends from TicketCounter */
public class TicketMachine extends TicketCounter{

    int servedCounter = 1; // this counter is used to simulate ticket machine breakdown when it reaches a certain number
    boolean brokeDown = false;

    public TicketMachine(String machineName){
        super(machineName);
    }

    @Override
    public void sellTicket(Customer customer){
        if(!brokeDown && servedCounter < 10){
            if(lock.tryLock()){
                System.out.println("#" + servedCounter);
                System.out.println("("+java.time.LocalTime.now().withNano(0) + " - TM)" + " Customer " + customer.getName() + " reached " + this.counterName);
                try{
                    Thread.sleep(0);
                }catch(Exception e){}
                System.out.println("("+java.time.LocalTime.now().withNano(0) + " - TM) " + this.counterName + " sold Ticket to Customer: " + customer.getName());
                customer.getTicket = true;
                servedCounter += 1;
                lock.unlock();
            }
        } else {
            customer.ticketMachine = null;
            customer.ticket.sellTicket(customer);
        }
    }
}
