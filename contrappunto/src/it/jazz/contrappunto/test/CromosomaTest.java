package it.jazz.contrappunto.test;

import it.jazz.contrappunto.Melodia;
import it.jazz.contrappunto.genetico.Cromosoma;
import it.jazz.contrappunto.teoria.Scala;
import it.jazz.contrappunto.util.Comuni;
import it.jazz.contrappunto.util.Log;

/**
 * utilizzando i test le sequenze midi vengono troncate: <br>
 * per questo motivo utilizzo un metodo statico
 * 
 * @author Guido Zuccaro
 */
public class CromosomaTest implements Comuni {
	public static void main(String[] args) {
		Scala maggiore = new Scala(Scala.MAGGIORE);
		Melodia cantusFirmus = new Melodia(maggiore, Melodia.LUNGHEZZA);

		Cromosoma madre = new Cromosoma(cantusFirmus);
		Log.debug("madre" + DEBUG + madre);

		Cromosoma padre = new Cromosoma(cantusFirmus);
		Log.debug("padre" + DEBUG + padre);

		Cromosoma figlio = madre.crossover(padre);
		Log.debug("figlio" + DEBUG + figlio);

		Cromosoma mutato = figlio.muta();
		Log.debug("mutato" + DEBUG + mutato);
	}
}
