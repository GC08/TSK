package electric_circuit;

public class LowPassRCFilter extends RCFilter{

	public LowPassRCFilter(double freq, double Uin, double R, double C) {
		super(freq, Uin, R, C);
	}

	@Override
	public double Uout() {
		return (Uin*Xc())/(Xc()+R);
	}

}
