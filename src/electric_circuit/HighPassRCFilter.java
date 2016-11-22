package electric_circuit;

public class HighPassRCFilter extends RCFilter{

	public HighPassRCFilter(double freq, double Uin, double R, double C) {
		super(freq, Uin, R, C);
	}

	@Override
	public double Uout() {
		return (Uin * R)/(Xc()+R);
	}
	
	
	
}
