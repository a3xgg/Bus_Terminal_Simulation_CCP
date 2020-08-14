package CCP_Assignment;

import java.util.Random;

public class MainClass {
    public static void main(String args[]) {
        BusTerminalThread busTerminal = new BusTerminalThread("APBT");
        busTerminal.start();
    }
}
