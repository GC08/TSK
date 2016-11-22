package electric_circuit;

public abstract class Filter {
	protected double freq,Uin,R;
	
	Filter(double freq, double Uin,double R){
		this.setFreq(freq);
		this.setUin(Uin);
		this.setR(R);
	}
	
	public abstract double getLimitFreq();
	
	public double getVoltageGain(){
		if(Uin == 0){
			return 0;
		}
		
		return 10 * Math.log10(Uout()/Uin);
	}

	public abstract double Uout();

	public double getUin() {
		return Uin;
	}

	public void setUin(double uin) {
		Uin = uin;
	}

	public double getFreq() {
		return freq;
	}

	public void setFreq(double freq) {
		this.freq = freq;
	}

	public double getR() {
		return R;
	}

	public void setR(double r) {
		R = r;
	}
}
