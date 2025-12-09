interface RemoteTV {

    /**
     * Power On method
     * @deprecated no
     */
    void powerOn();

    /**
     * Power Off method
     * @deprecated no
     */
    void powerOff();

}

class RemoteTVController implements RemoteTV {

    /**
     * {@inheritDoc}
     * Inheritd
     */
    @Override
    public void powerOn() {
        System.out.println("Power on");
    }

    /**
     * {@inheritDoc}
     * Inheritd
     */
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
