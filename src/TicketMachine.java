import java.util.Random;

/* Ticket Machine that extends from TicketCounter */
public class TicketMachine extends TicketCounter{

    private int servedCounter = 1; // this counter is used to simulate ticket machine breakdown when it reaches a certain number
    private boolean brokeDown = false;

    public TicketMachine(String machineName){
        super(machineName);
    }

    @Override
    public void sellTicket(Customer customer){
        if(!brokeDown && servedCounter < 15){   // set the breakdown counter be random
            if(lock.tryLock()){
                System.out.println("#" + servedCounter);
                System.out.println("("+java.time.LocalTime.now().withNano(0) + " - TM)" + " Customer " + customer.getName() + " reached " + this.counterName);
                try{
                    Thread.sleep(1500);
                }catch(Exception e){}
                System.out.println("("+java.time.LocalTime.now().withNano(0) + " - TM) " + this.counterName + " sold Ticket to Customer: " + customer.getName());
                customer.getTicket = true;
                servedCounter += 1;
                lock.unlock();
            }
        } else {
            brokeDown = true;
            try{
                Thread.sleep(2500);
            } catch(Exception e){}
            finally{
                System.out.println("Customer " + customer.getName() + " has left to " + customer.ticket.counterName + " - Reason: " + counterName + " broke down!");
                customer.ticketMachine = null;
                customer.ticket.sellTicket(customer);
            }
        }
    }
}
