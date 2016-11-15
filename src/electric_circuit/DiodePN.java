package electric_circuit;

public abstract class DiodePN{
	protected double U;
	protected double mUt;
	protected double Is;
	protected double R = 1;
	protected double IFmax;
	
	public DiodePN() {
	}
	
	public double IF(double U) {
		return this.Is *(Math.exp(U/this.mUt) - 1);
	}

	public double UF(double U) {
		return U - (this.IF(U) * this.R);
	}
	
	public double getIS(){
		return this.Is;
	}
	
	public double getmUt(){
		return this.mUt;
	}
	
	public double getIFMax(){
		return this.IFmax;
	}

}
