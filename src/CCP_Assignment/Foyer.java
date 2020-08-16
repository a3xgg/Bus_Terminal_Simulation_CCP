package CCP_Assignment;

public class Foyer extends Thread{

    public Foyer (String foyerName){
        setName(foyerName);
    }

    public void enterFoyer(Customer customer){
        synchronized (this){
            try {
                Thread.sleep(1500);
                System.out.println("Customer " + customer.getName() + " is waiting at the Foyer");
            } catch(Exception e){}
        }
//        synchronized (customer.waitingArea){
//            try{
//                customer.waitingArea.wait();
//            } catch(Exception e){}
//        }
//        customer.waitingArea.enterWaitingArea(customer);
    }

    @Override
    public void run(){
        while(true){

        }
    }
}
