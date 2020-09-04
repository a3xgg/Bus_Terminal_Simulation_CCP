package CCP_Assignment;

import java.util.Random;

public class TicketCounter{
    private String counterName;

    boolean onBreak = false;
    int servedCounter = 0;
    int counterToBreak;

    public TicketCounter(String counterName){
        this.counterName = counterName;
        this.counterToBreak = new Random().nextInt(10)+1;
    }

    public String getCounterName(){
        return this.counterName;
    }

    public void sellTicket(Customer customer){
        synchronized (this){
            try{
                Thread.sleep(new Random().nextInt(2) * 1000);
            } catch(Exception e){}
            System.out.println("("+java.time.LocalTime.now().withNano(0) + " - " + getCounterName() + ")\t" + "Customer " + customer.getName() + " has reached " + getCounterName());
            try{
                Thread.sleep(1000);
            } catch(Exception e){}
            customer.ticket = true;
            servedCounter += 1;
            System.out.println("("+java.time.LocalTime.now().withNano(0) + " - " + getCounterName() + ")\t" + getCounterName() + " has sold a ticket to Customer " + customer.getName());
            onBreak = servedCounter == counterToBreak ? true : false;
            if(onBreak){
                System.out.println("("+java.time.LocalTime.now().withNano(0)+") " +getCounterName() +" is having a TOILET BREAK, please wait for 5s");
                try{
                    Thread.sleep(5000);
                } catch(Exception e){}
                System.out.println("("+java.time.LocalTime.now().withNano(0)+") " +getCounterName() +" is ready to serve again");
                onBreak = false;
                servedCounter = 0;
            }
        }
        if(customer.ticket == true){
            customer.ticketScanner.scanTicket(customer);
        }
    }
}
