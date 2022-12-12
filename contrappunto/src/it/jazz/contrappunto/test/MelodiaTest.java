package it.jazz.contrappunto.test;

import it.jazz.contrappunto.Melodia;
import it.jazz.contrappunto.teoria.Scala;
import it.jazz.contrappunto.util.Log;

/**
 * utilizzando i test le sequenze midi vengono troncate: <br>
 * per questo motivo utilizzo un metodo statico
 * 
 * @author Guido Zuccaro
 */
public class MelodiaTest {
	public static void main(String[] args) {
		Scala maggiore = new Scala(Scala.MAGGIORE);
		Melodia cantusFirmus = new Melodia(maggiore, Melodia.LUNGHEZZA);
		// cantusFirmus.suona();
		Log.debug(cantusFirmus);
	}
}
