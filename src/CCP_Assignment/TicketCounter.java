package CCP_Assignment;

import java.util.Random;

public class TicketCounter{
    private String counterName;
    public TicketCounter(String counterName){
        this.counterName = counterName;
    }

    public String getCounterName(){
        return this.counterName;
    }

    public synchronized void sellTicket(Customer customer){
        try{
            Thread.sleep((new Random().nextInt(4) + 1) * 1000);
        } catch(Exception e){}
        System.out.println("("+java.time.LocalTime.now().withNano(0) + " - " + getCounterName() + ")\t" + "Customer " + customer.getName() + " has reached " + getCounterName());
        try{
            Thread.sleep(1500);
        } catch(Exception e){}
        customer.ticket = true;
        System.out.println("("+java.time.LocalTime.now().withNano(0) + " - " + getCounterName() + ")\t" + getCounterName() + " has sold a ticket to Customer " + customer.getName());
    }
}
