package CCP_Assignment;

import java.util.Random;

public class TicketScanner extends TicketInspector{

    public TicketScanner(String ticketInspectorName) {
        super(ticketInspectorName);
    }

    @Override
    public void scanTicket(Customer customer){
        synchronized (this){
            try{
                Thread.sleep((new Random().nextInt(2)) * 1000);
            } catch (Exception e){}
            System.out.println("("+java.time.LocalTime.now().withNano(0)+ " - " + getTicketInspectorName() + ") " + "Customer " + customer.getName() + " is having their ticket scanned by " + this.getTicketInspectorName());
            try{
                Thread.sleep((new Random().nextInt(2)+1) * 1000);
            } catch(Exception e){}
            customer.scanTicket1 = true;
            System.out.println("("+java.time.LocalTime.now().withNano(0)+ " - " + getTicketInspectorName() + ") " + getTicketInspectorName() + " has verified Customer " + customer.getName() + "'s ticket"  );
        }
        if(customer.scanTicket1){
            customer.ticketInspector.scanTicket(customer);
        }
    }
}
