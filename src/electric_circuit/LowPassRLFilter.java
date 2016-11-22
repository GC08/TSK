package electric_circuit;

public class LowPassRLFilter extends RLFilter {

	public LowPassRLFilter(double freq, double Uin, double R, double L) {
		super(freq, Uin, R, L);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double Uout() {
		return (Uin * R)/(R + Xl());
	}

}
