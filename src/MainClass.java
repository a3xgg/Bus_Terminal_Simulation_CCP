import java.util.Random;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static void main(String args[]){

        TicketCounter firstCounter = new TicketCounter("Counter 1");
        TicketCounter secondCounter = new TicketCounter("Counter 2");
        TicketMachine ticketMachine = new TicketMachine("Ticket Machine");

        Customer[] firstBatch = new Customer[25];
        Customer[] secondBatch = new Customer[25];
        Customer[] thirdBatch = new Customer[25];

        for(int i = 0; i < 25; i++){
            int randCounter = new Random().nextInt(2);
            firstBatch[i] = new Customer(Integer.toString(i), firstCounter);
            secondBatch[i] = new Customer(Integer.toString(25 + i), secondCounter);
            switch (randCounter){
                case 0:
                    thirdBatch[i] = new Customer(Integer.toString(50 + i), ticketMachine, firstCounter);
                    break;
                case 1:
                    thirdBatch[i] = new Customer(Integer.toString(50 + i), ticketMachine, secondCounter);
                    break;
            }
        }
        for(int i = 0; i < 25; i++){
//            firstBatch[i].start();
//            secondBatch[i].start();
            thirdBatch[i].start();
        }
    }
}
