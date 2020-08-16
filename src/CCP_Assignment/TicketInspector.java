package CCP_Assignment;

public class TicketInspector extends Thread{

    public TicketInspector(String ticketInspectorName, WaitingArea waitingAreaOne, WaitingArea waitingAreaTwo, WaitingArea waitingAreaThree){
        setName(ticketInspectorName);
    }
}
