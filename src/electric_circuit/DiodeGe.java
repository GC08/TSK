package electric_circuit;

public class DiodeGe extends DiodePN {

	public DiodeGe() {
		this.Is = 10 * Math.pow(10, -9);
		this.mUt = 0.03;
		this.IFmax = 0.1;
	}
	

}
