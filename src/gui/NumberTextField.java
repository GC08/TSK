package gui;

import javafx.scene.control.TextField;

public class NumberTextField extends TextField {

	@Override
	public void replaceText(int start, int end, String text) {
		if (validate(text)) {
			super.replaceText(start, end, text);
		}
	}

	@Override
	public void replaceSelection(String text) {
		if (validate(text)) {
			super.replaceSelection(text);
		}
	}

	private boolean validate(String text) {
		try {
			Double.parseDouble(this.getText() + text);
		} catch (NumberFormatException e) {
			return false;
		}

		return true;
	}
	
	public double getValue(){
		return Double.parseDouble(this.getText());
	}
}
