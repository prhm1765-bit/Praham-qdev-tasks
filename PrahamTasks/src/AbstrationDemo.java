interface Remote{

    void powerOn();
    void powerOff();
}

abstract class RemoteFunctions{
    abstract void tempPlus();
    abstract void tempMinus();

    //Non abstract methid in abstract class
    void modeChange(){}

}

class RemoteController extends RemoteFunctions implements Remote{
    @Override
    public void powerOn() {
        System.out.println("Power on");
    }

    @Override
    public void powerOff() {
        System.out.println("Power off");
    }

    @Override
    void tempPlus() {
        System.out.println("Temp Plus");
    }

    @Override
    void tempMinus() {
        System.out.println("Temp Minus");
    }

}
public class AbstrationDemo {

    public static void main(String[] args) {
        RemoteController remoteController = new RemoteController();
        remoteController.powerOn();
        remoteController.powerOff();
        remoteController.tempPlus();
        remoteController.tempMinus();

    }
}
