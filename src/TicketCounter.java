public class TicketCounter extends Thread{

    Customer customer;

    public TicketCounter(String counterName, Customer customer){
        setName(counterName);
        this.customer = customer;
    }

    @Override
    public void run(){

    }
}
