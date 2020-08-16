package CCP_Assignment;

public class Customer extends Thread{

    TicketCounter ticketCounter;
    BusTerminal busTerminal;
    WaitingArea waitingArea;
    Bus bus;
    Foyer foyer;
    boolean ticket = false;
    boolean entered = false;

    public Customer(String customerName, BusTerminal busTerminal,WaitingArea waitingArea, Bus bus, Foyer foyer, TicketCounter ticketCounter){
        setName(customerName);
        this.foyer = foyer;
        this.busTerminal = busTerminal;
        this.bus = bus;
        this.waitingArea = waitingArea;
        this.ticketCounter = ticketCounter;
    }

    @Override
    public void run(){
        while(entered == false){
            busTerminal.enterBusTerminal(this);
        }
    }
}
