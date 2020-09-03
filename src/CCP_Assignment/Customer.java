package CCP_Assignment;

public class Customer extends Thread{

    TicketCounter ticketCounter;
    BusTerminal busTerminal;
    WaitingArea waitingArea;
    Bus bus;
    Foyer foyer;
    TicketMachine ticketMachine;
    TicketInspector ticketInspector;
    TicketScanner ticketScanner;

    boolean ticket = false;
    boolean entered = false;
    boolean scanTicket1 = false;    // use by TicketScanner
    boolean scanTicket2 = false;    // use by TicketInspector
    boolean enteredBus = false;
    boolean enteredWaitingArea = false;

    public Customer(String customerName, BusTerminal busTerminal,WaitingArea waitingArea, Bus bus, Foyer foyer, TicketCounter ticketCounter, TicketInspector ticketInspector, TicketScanner ticketScanner){
        setName(customerName);
        this.foyer = foyer;
        this.busTerminal = busTerminal;
        this.bus = bus;
        this.waitingArea = waitingArea;
        this.ticketCounter = ticketCounter;
        this.ticketMachine = null;
        this.ticketInspector = ticketInspector;
        this.ticketScanner = ticketScanner;
    }

    public Customer(String customerName, BusTerminal busTerminal,WaitingArea waitingArea, Bus bus, Foyer foyer, TicketCounter ticketCounter, TicketMachine ticketMachine, TicketInspector ticketInspector, TicketScanner ticketScanner){
        this(customerName, busTerminal, waitingArea, bus, foyer, ticketCounter, ticketInspector, ticketScanner);
        this.ticketMachine = ticketMachine;
    }

    @Override
    public void run(){
        while(entered == false){
            busTerminal.enterBusTerminal(this);
        }
    }
}
