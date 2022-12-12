package it.jazz.contrappunto.teoria;

import it.jazz.contrappunto.base.Linea;

public class Scala extends Linea {
	private static final long serialVersionUID = 1L;
	public static final Nota[] MAGGIORE = { Nota.DO, Nota.RE, Nota.MI, Nota.FA,
			Nota.SOL, Nota.LA, Nota.SI };

	public Scala(Nota[] struttura) {
		for (Nota nota : struttura)
			add(nota);
	}
}
