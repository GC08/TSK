package electric_circuit;

public class RLC extends Filter {
	private double C, L, Rwe;

	public RLC(double freq, double Uin, double R, double L, double C, double Rwe) {
		super(freq, Uin, R);
		this.setL(L);
		this.setC(C);
		this.setRwe(Rwe);
	}

	@Override
	public double getLimitFreq() {
		return 0;
	}
	
	public double getRezFreq() {
		return 1.0/(2*Math.PI*Math.sqrt(L*C));
	}

	@Override
	public double Uout() {
		return Uin * (Gwe() + Y());
	}

	private double Y() {
		return G() + Bl() + Bc();
	}

	private double Bc() {
		return 2.0 * Math.PI * freq * C;
	}

	private double Bl() {
		return 1.0 / 2.0 * Math.PI * freq * L;
	}

	private double G() {
		return 1.0 / R;
	}

	private double Gwe() {
		return 1.0 / Rwe;
	}

	public double getL() {
		return L;
	}

	public void setL(double l) {
		L = l;
	}

	public double getC() {
		return C;
	}

	public void setC(double c) {
		C = c;
	}

	public double getRwe() {
		return Rwe;
	}

	public void setRwe(double rwe) {
		Rwe = rwe;
	}

}
