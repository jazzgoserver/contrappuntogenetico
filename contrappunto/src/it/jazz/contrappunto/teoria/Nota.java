package it.jazz.contrappunto.teoria;

import it.jazz.contrappunto.costanti.Gradi;
import it.jazz.contrappunto.costanti.Note;

public enum Nota {
	DO(Note.DO, Gradi.PRIMO), RE(Note.RE, Gradi.SECONDO), //
	MI(Note.MI, Gradi.TERZO), FA(Note.FA, Gradi.QUARTO), //
	SOL(Note.SOL, Gradi.QUINTO), LA(Note.LA, Gradi.SESTO), //
	SI(Note.SI, Gradi.SETTIMO);

	private int altezza;
	private int grado;

	private Nota(int altezza, int grado) {
		this.altezza = altezza;
		this.grado = grado;
	}

	public int getAltezza() {
		return altezza;
	}

	public int getGrado() {
		return grado;
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
