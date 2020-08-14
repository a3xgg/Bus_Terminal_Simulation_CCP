public class BusTerminal extends Thread{

    int terminalCustomerLimit;

    Entrance westEntrance, eastEntrance;
    SecurityGuard westGuard, eastGuard;

    public BusTerminal(){
        this.westEntrance = new Entrance("West Entrance", this.westGuard = new SecurityGuard("West Guard"));
        this.eastEntrance = new Entrance("East Entrance", this.eastGuard = new SecurityGuard("East Guard"));
    }

    @Override
    public void run(){

    }
}
