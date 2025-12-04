interface RemoteTV{

    void powerOn();
    void powerOff();
}

class RemoteTVController implements RemoteTV{
    @Override
    public void powerOn() {
        System.out.println("Power on");
    }

    @Override
    public void powerOff() {
        System.out.println("Power off");
    }


}

public class InterfaceDemo {
    public static void main(String[] args) {

        RemoteController remoteController = new RemoteController();
        remoteController.powerOn();
        remoteController.powerOff();

    }
}
