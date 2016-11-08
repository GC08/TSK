package electric_circuit;

import java.util.ArrayList;

public abstract class ElectricElement {
	ArrayList<ElectricElement> leftElements;
	ArrayList<ElectricElement> rightElements;
	
	boolean isGrounded(){
		for(ElectricElement element:this.leftElements){
			if(element.isGrounded()){
				return true;
			}
		}
		
		for(ElectricElement element:this.rightElements){
			if(element.isGrounded()){
				return true;
			}
		}
		
		return false;
	}
	
	abstract double IF();
	abstract double UF();;
}
