package electric_circuit;

public abstract class DiodePN {
	protected double U;
	protected double mUt;
	protected double Is;
	protected double R = 1;
	protected double IFmax;

	public DiodePN() {
	}

	public double IF(double U) {
		if (UF(U) > 0 && U > 0) {
			return If(U);
		}

		return 0;
	}
	
	private double If(double U){
		return this.Is * (Math.exp(U / this.mUt) - 1);
	}

	public double UF(double U) {
		double uf = U - (this.If(U) * this.R);

		if (uf > 0) {
			return uf;
		}

		return 0;
	}

	public double getIS() {
		return this.Is;
	}

	public double getmUt() {
		return this.mUt;
	}

	public double getIFMax() {
		return this.IFmax;
	}

}
