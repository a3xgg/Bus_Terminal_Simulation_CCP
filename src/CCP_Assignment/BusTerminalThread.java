package CCP_Assignment;

import java.util.Random;

public class BusTerminalThread extends Thread{
    public BusTerminalThread(String busTerminal){
        setName(busTerminal);
    }

    @Override
    public void run(){
        System.out.println("WELCOME TO " + getName() + " WE ARE OPEN FOR SERVICE!");

        BusTerminal busTerminal = new BusTerminal("APBT");

        Foyer foyer = new Foyer("Foyer");

        TicketCounter counterOne = new TicketCounter("Counter 1");
        TicketCounter counterTwo = new TicketCounter("Counter 2");
        TicketMachine ticketMachine = new TicketMachine("Ticket Machine");

        TicketInspector ticketInspector = new TicketInspector("Ticket Inspector");
        TicketScanner ticketScanner = new TicketScanner("Ticket Scanner");

        WaitingArea waitingAreaOne = new WaitingArea("Waiting Area 1");
        WaitingArea waitingAreaTwo = new WaitingArea("Waiting Area 2");
        WaitingArea waitingAreaThree = new WaitingArea("Waiting Area 3");

        Bus bus = new Bus("APBT Bus");
        Bus bus2 = new Bus("APBT Bus 2");
        Bus bus3 = new Bus("APBT Bus 3");

        Customer[] customers = new Customer[150];
        for(int i = 0; i < customers.length; i++){
            int randomGenerator = new Random().nextInt(4);
            switch (randomGenerator){
                case 0:
                    customers[i] = new Customer(Integer.toString(i), busTerminal,waitingAreaOne , bus, foyer,counterOne,ticketInspector, ticketScanner);
                    break;
                case 1:
                    customers[i] = new Customer(Integer.toString(i), busTerminal, waitingAreaTwo, bus2, foyer,counterTwo,ticketInspector, ticketScanner);
                    break;
                case 2:
                    int nextRand = new Random().nextInt(2);
                    switch (nextRand){
                        case 0:
                            customers[i] = new Customer(Integer.toString(i), busTerminal, waitingAreaOne, bus, foyer,counterOne, ticketMachine, ticketInspector, ticketScanner);
                            break;
                        case 1:
                            customers[i] = new Customer(Integer.toString(i), busTerminal, waitingAreaTwo, bus2, foyer,counterTwo, ticketMachine, ticketInspector, ticketScanner);
                            break;
                    }
                    break;
                case 3:
                    int rand = new Random().nextInt(2);
                    switch (rand){
                        case 0:
                            customers[i] = new Customer(Integer.toString(i), busTerminal, waitingAreaThree, bus3, foyer,counterOne, ticketMachine, ticketInspector, ticketScanner);
                            break;
                        case 1:
                            customers[i] = new Customer(Integer.toString(i), busTerminal, waitingAreaThree, bus3, foyer,counterTwo, ticketMachine, ticketInspector, ticketScanner);
                            break;
                    }
                    break;
            }
        }
        bus.start();
        bus2.start();
        bus3.start();
        waitingAreaOne.start();
        waitingAreaTwo.start();
        waitingAreaThree.start();
        for(int i = 0; i < customers.length; i++){
            customers[i].start();
        }

    }
}
