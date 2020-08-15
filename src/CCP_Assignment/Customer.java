package CCP_Assignment;

public class Customer extends Thread{

    TicketCounter ticketCounter;
    BusTerminal busTerminal;
    WaitingArea waitingArea;
    Foyer foyer;
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

    public Customer(String customerName, BusTerminal busTerminal,WaitingArea waitingArea , TicketCounter ticketCounter){
        this(customerName, busTerminal);
        this.waitingArea = waitingArea;
        this.ticketCounter = ticketCounter;
    }
    public Customer(String customerName, BusTerminal busTerminal,WaitingArea waitingArea, Foyer foyer, TicketCounter ticketCounter){
        this(customerName, busTerminal);
        this.waitingArea = waitingArea;
        this.ticketCounter = ticketCounter;
        this.foyer = foyer;
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
