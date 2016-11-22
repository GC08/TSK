package electric_circuit;

public abstract class RCFilter extends Filter {
	private double C;

	RCFilter(double freq, double Uin, double R, double C) {
		super(freq, Uin, R);
		this.setC(C);
	}

	// reaktancja
	public double Xc() {
		double d = 2 * Math.PI * this.getFreq() * this.getC();

		if (d > 0) {
			return 1 / d;
		}

		return 0;
	}

	public double getC() {
		return C;
	}

	public void setC(double c) {
		C = c;
	}

	@Override
	public double getLimitFreq() {
		return 1 / (2 * Math.PI * R * C);
	}

}
