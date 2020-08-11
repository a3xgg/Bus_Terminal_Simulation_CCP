import java.util.Random;

/* Ticket Machine that extends from TicketCounter */
public class TicketMachine extends TicketCounter{

    private int servedCounter = 1; // this counter is used to simulate ticket machine breakdown when it reaches a certain number
    boolean brokeDown = false;

    public TicketMachine(String machineName){
        super(machineName);
    }

    @Override
    public void sellTicket(Customer customer){
        
    }
}
