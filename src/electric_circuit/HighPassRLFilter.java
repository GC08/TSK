package electric_circuit;

public class HighPassRLFilter extends RLFilter{

	public HighPassRLFilter(double freq, double Uin, double R, double L) {
		super(freq, Uin, R, L);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double Uout() {
		return (Uin*Xl())/(R+Xl());
	}
	
}
