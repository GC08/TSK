package electric_circuit;

public class ZenerDiodeForwardBias{

	private double U;
	private double I;
	private double k = 1.38 * Math.pow(10,-23);
	private double q = 1.6* Math.pow(10,-19);
	private double T = 293;
	private double deltaU = 0.6;
	private double Is = 0.38;
	
	public ZenerDiodeForwardBias(double U, double I) {
		this.U = U;
		this.I = I;
	}
	
	public double IF() {
		return this.Is *(Math.exp((this.q * this.U)/(this.k - this.T)) - 1);
	}

	public double UF() {
		return U - deltaU;
	}

}
