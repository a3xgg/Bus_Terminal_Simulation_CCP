package CCP_Assignment;

import CCP_Assignment.TicketCounter;

public class Customer extends Thread{

    TicketCounter ticketCounter;
    BusTerminal busTerminal;
    boolean ticket = false;
    boolean entered = false;

    public Customer(String customerName, BusTerminal busTerminal){
        setName(customerName);
        this.busTerminal = busTerminal;
    }

    public Customer(String customerName, TicketCounter ticketCounter){
        setName(customerName);
        this.ticketCounter = ticketCounter;
    }

    public Customer(String customerName, BusTerminal busTerminal, TicketCounter ticketCounter){
        this(customerName, busTerminal);
        this.ticketCounter = ticketCounter;
    }

    @Override
    public void run(){
//        while(ticket == false){
//            ticketCounter.sellTicket(this);
//        }
        while(entered == false){
            busTerminal.enterBusTerminal(this);
        }
    }
}
