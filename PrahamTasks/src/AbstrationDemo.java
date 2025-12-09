//Interface for remote
interface Remote {

    /**
     * Interface method to power On
     * @deprecated no
     */
    void powerOn() ;

    /**
     * Interface method of power off
     * @deprecated no
     */
    void powerOff();

}

//Abstract method for RemoteFunction
abstract class RemoteFunctions {

    /**
     * Abstract method for temprature plus
     * @deprecated no
     */
    abstract void tempPlus();

    /**
     * Abstract method for temprature plus
     * @deprecated no
     */
    abstract void tempMinus();

    //Non abstract methid in abstract class
    void modeChange(){}

}

class RemoteController extends RemoteFunctions implements Remote {

    /**
     *
     * {@inheritDoc}
     *
     * Implementation of power on method
     */
    @Override
    public void powerOn() {
        System.out.println("Power on");
    }

    /**
     *
     * {@inheritDoc}
     *
     * Implementation of power on method
     */
    @Override
    public void powerOff() {
        System.out.println("Power off");
    }

    /**
     *
     * {@inheritDoc}
     *
     * Overriding the abstract temprature plus method
     */
    @Override
    void tempPlus() {
        System.out.println("Temp Plus");
    }

    /**
     *
     * {@inheritDoc}
     *
     * Overriding the abstract temprature minus method
     */
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
