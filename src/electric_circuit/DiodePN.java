package electric_circuit;

public abstract class DiodePN extends ElectricElement{
	protected double U;
	protected double mUt;
	protected double Is;
	protected double R = 1;
	protected double IFmax;
	
	public DiodePN(double U) {
		this.U = U;
	}
	
	@Override
	public double IF() {
		return this.Is *(Math.exp(this.U/this.mUt) - 1);
	}

	@Override
	public double UF() {
		return U - (this.IF() * this.R);
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
