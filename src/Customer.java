/* Customer Thread */
public class Customer extends Thread{

    TicketCounter ticket;
    TicketMachine ticketMachine;

    boolean getTicket = false;

    public Customer(String customerName, TicketCounter ticket){
        setName(customerName);
        this.ticket = ticket;
    }

    public Customer(String customerName, TicketMachine ticketMachine){
        setName(customerName);
        this.ticketMachine = ticketMachine;
    }

    public Customer(String customerName, TicketMachine ticketMachine, TicketCounter ticket){
        this(customerName, ticketMachine);
        this.ticket = ticket;
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
