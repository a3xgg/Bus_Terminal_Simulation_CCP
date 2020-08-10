import java.util.Random;

/* Ticket Machine that extends from TicketCounter */
public class TicketMachine extends TicketCounter{

    int servedCounter = 0; // this counter is used to simulate ticket machine breakdown when it reaches a certain number
    boolean brokeDown = false;

    public TicketMachine(String machineName){
        super(machineName);
    }

    @Override
    public void sellTicket(Customer customer){
        if(servedCounter < 15){
            if(lock.tryLock()){
                servedCounter += 1;
                System.out.println("("+java.time.LocalTime.now().withNano(0) + " - TM)" + " Customer " + customer.getName() + " reached " + this.counterName);
                customer.getTicket = true;
                try{
                    Thread.sleep(new Random().nextInt(3) * 2500);
                }catch(Exception e){}
                System.out.println("("+java.time.LocalTime.now().withNano(0) + " - TM) " + this.counterName + " sold Ticket to Customer: " + customer.getName());
                lock.unlock();
            }
        } else {
            brokeDown = true;
            System.out.println("(WARNING) " + this.counterName + " broke down awaiting repair....");
            try{
                Thread.sleep(5000);
                servedCounter = 0;
            }catch (Exception e){}
            finally{
                System.out.println("(FIXED) " + this.counterName + " is ready to serve customers!");
            }
        }
    }
}
