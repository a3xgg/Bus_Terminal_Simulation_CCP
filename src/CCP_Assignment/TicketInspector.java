package CCP_Assignment;

import java.util.Random;

public class TicketInspector{

    String ticketInspectorName;

    public TicketInspector(String ticketInspectorName){
        this.ticketInspectorName = ticketInspectorName;
    }

    public String getTicketInspectorName(){
        return this.ticketInspectorName;
    }

    public void scanTicket(Customer customer){      // Function allows the customer to scan their ticket with the ticket inspector
        synchronized (this){
            try{
                Thread.sleep((new Random().nextInt(3)+1) * 1000);
            } catch (Exception e){}
            System.out.println("("+java.time.LocalTime.now().withNano(0)+ " - " + getTicketInspectorName() + ") " + "Customer " + customer.getName() + " is having their ticket scanned by " + this.getTicketInspectorName());
            try{
                Thread.sleep((new Random().nextInt(2)+1) * 1000);
            } catch(Exception e){}
            customer.scanTicket2 = true;
            System.out.println("("+java.time.LocalTime.now().withNano(0)+ " - " + getTicketInspectorName() + ") " + getTicketInspectorName() + " has verified Customer " + customer.getName() + "'s ticket"  );
        }
        if(customer.scanTicket1 && customer.scanTicket2){       // requires that ticket is checked by both the scanner and inspector
            customer.waitingArea.enterWaitingArea(customer);
        } else {
            if (customer.scanTicket1 == false){
                customer.ticketScanner.scanTicket(customer);
            } else if(customer.scanTicket2 == false){
                customer.ticketInspector.scanTicket(customer);
            }
        }
    }
}
