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

	public void setU(double U) {
		this.U = U;
	}

	public void setOffset(double offset) {
		this.Offset = offset;
	}

	public void setFrequency(double freq) {
		this.Frequency = freq;
	}
	
	public void setType(Type type) {
		this.type = type;
	}

	public double U(double time) {
		if (U == 0) {
			return 0;
		}
		
		double USource = 0;

		switch (this.type) {
		case SINUS:
			USource = Math.sin(2 * Math.PI * time * this.Frequency) * this.U + this.Offset;
			break;
		case TRIANGULAR:
			time += 0.25;
			double faze = (time * this.Frequency) % 1.0;

			if (faze <= 0.5) {
				USource = 4 * this.U * (faze - 0.25);
			}else{

				USource = 4 * this.U * (0.75 - faze);
			}
			
			break;
		case DIRECT:
			USource = U;
			break;
		default:
			return 0;
		}
		
		return USource + this.Offset;
	}

	public enum Type {
		DIRECT, SINUS, TRIANGULAR;
	}
}
