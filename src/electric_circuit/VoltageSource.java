package electric_circuit;

public class VoltageSource {
	private double U, Offset, Frequency;
	private Type type;

	public VoltageSource(double U, Type type) {
		this.U = U;
		this.type = type;
	}

	public VoltageSource(double U, Type type, double Offset, double Frequency) {
		this.U = U;
		this.type = type;
		this.Offset = Offset;
		this.Frequency = Frequency;
	}

	public double U() {
		if (this.type == Type.DIRECT) {
			return U;
		}

		return 0;
	}

	public double U(double time) {
		if(U == 0){
			return 0;
		}
		
		double sinValue = Math.sin(2*Math.PI * time * this.Frequency);
		
		switch (this.type) {
		case SINUS:
			return sinValue * this.U + this.Offset;
		case TRIANGULAR:
			double multipler = 1;
			
			if(sinValue > 0){
				 multipler =  sinValue / Math.abs(sinValue);
			}
			
			return  0;
		default:
			return 0;
		}
	}

	public enum Type {
		DIRECT, SINUS, TRIANGULAR;
	}
}
