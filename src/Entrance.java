/* Entrance Class */
public class Entrance extends Thread{

    SecurityGuard securityGuard;

    public Entrance(String entranceName, SecurityGuard securityGuard){
        setName(entranceName);
        this.securityGuard = securityGuard;
    }

    @Override
    public void run(){

    }
}
