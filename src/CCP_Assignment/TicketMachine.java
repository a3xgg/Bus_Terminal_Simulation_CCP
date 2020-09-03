package CCP_Assignment;

import java.util.Random;

public class TicketMachine extends TicketCounter{

    boolean brokeDown;
    int servedCounter = 0;
    int brokeDownRandomizer;

    public TicketMachine(String counterName) {
        super(counterName);
        this.brokeDown = false;
        //brokeDownRandomizer = (new Random().nextInt(10)+1) * 5; // max breakdown @ 50, min breakdown @ 5
        brokeDownRandomizer = 10;
    }

    @Override
    public void sellTicket(Customer customer){
        synchronized (this) {
            if(brokeDown){
                customer.ticketMachine = null;
                System.out.println("("+java.time.LocalTime.now().withNano(0)+")" + " Customer "+ customer.getName() + " is headed towards " + customer.ticketCounter.getCounterName());
                customer.ticketCounter.sellTicket(customer);
            } else {
                super.sellTicket(customer);
                servedCounter += 1;
                brokeDown = servedCounter == brokeDownRandomizer ? true : false;
                if(servedCounter == brokeDownRandomizer){
                    System.out.println("("+java.time.LocalTime.now().withNano(0)+" - BROKE DOWN)");
                }
            }
        }
    }
}
