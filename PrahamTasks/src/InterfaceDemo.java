interface RemoteTV {

	//Power On method
	void powerOn();

	//Power Off method
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
		RemoteTVController remoteController = new RemoteTVController();
		remoteController.powerOn();
		remoteController.powerOff();
	}

}
