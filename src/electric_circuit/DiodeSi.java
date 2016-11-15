package electric_circuit;

public class DiodeSi extends DiodePN{

	public DiodeSi() {
		this.Is = 10 * Math.pow(10, -12);
		this.mUt = 0.03;
		this.IFmax = 0.1;
	}

}
