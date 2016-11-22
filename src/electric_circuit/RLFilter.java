package electric_circuit;

public abstract class RLFilter extends Filter {
	protected double L;

	RLFilter(double freq, double Uin, double R, double L) {
		super(freq, Uin, R);
		this.setL(L);
	}

	public double getL() {
		return L;
	}

	public void setL(double l) {
		L = l;
	}

	public double Xl() {
		return 2 * Math.PI * freq * L;
	}

	@Override
	public double getLimitFreq() {
		return R / (2 * Math.PI * L);
	}

}
