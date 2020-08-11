public class BusTerminal extends Thread{

    int terminalCustomerLimit;

    public BusTerminal(Entrance westEntrance, Entrance eastEntrance){
        this.terminalCustomerLimit = 100;
    }

    @Override
    public void run(){

    }
}
