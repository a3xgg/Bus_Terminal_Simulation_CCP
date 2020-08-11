/* Customer Thread */
public class Customer extends Thread{

    TicketCounter ticket;
    TicketMachine ticketMachine;
    Entrance entryLocation;
    WaitingArea waitingArea;
    boolean getTicket = false;

    public Customer(String customerName, TicketCounter ticket, WaitingArea waitingArea){
        setName(customerName);
        this.ticket = ticket;
        this.waitingArea = waitingArea;
    }

    public Customer(String customerName, TicketMachine ticketMachine, TicketCounter ticket, WaitingArea waitingArea){
        this(customerName, ticket, waitingArea);
        this.ticketMachine = ticketMachine;
    }

    public Customer(String customerName, Entrance entry){
        setName(customerName);
        this.entryLocation = entry;
    }

    @Override
    public void run(){
        while(true){
           if(this.getTicket == false){
               if (this.ticket != null && this.ticketMachine == null) {
                   ticket.sellTicket(this);
               } else if(this.ticketMachine != null && this.ticket != null){
                   ticketMachine.sellTicket(this);
               }
           }
        }
    }
}
