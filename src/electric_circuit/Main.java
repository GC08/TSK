package electric_circuit;

import java.util.Scanner;

public class Main {

	public static void main(String[] args){
		int choose = 0;
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);

		while ((choose != 1) && (choose != 2)) {
			System.out.println("Wybierz diodę:");
			System.out.println("1. Krzemowa");
			System.out.println("2. Germanowa");
			choose = reader.nextInt();
		}

		System.out.println("Podaj napięcie wejściowe(V):");
		double U = reader.nextDouble();
		DiodePN diode = null;

		switch (choose) {
		case 1:
			diode = new DiodeSi();
			break;
		case 2:
			diode = new DiodeGe();
			break;
		}
		
		System.out.println("Parametry diody:");
		System.out.println("Is" + diode.getIS() + " A");
		System.out.println("mUt" + diode.getmUt() + " V");
		System.out.println("Parametry wyjśćiowe:");
		System.out.println("If" + diode.IF(0.7) + " A");
		System.out.println("Uf" + diode.UF(0.7) + " V");
	}

}
