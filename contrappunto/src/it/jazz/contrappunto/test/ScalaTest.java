package it.jazz.contrappunto.test;

import it.jazz.contrappunto.teoria.Scala;
import it.jazz.contrappunto.util.Log;

/**
 * utilizzando i test le sequenze midi vengono troncate: <br>
 * per questo motivo utilizzo un metodo statico
 * 
 * @author Guido Zuccaro
 */
public class ScalaTest {
	public static void main(String[] args) {
		Scala maggiore = new Scala(Scala.MAGGIORE);
		// maggiore.suona();
		Log.debug(maggiore);
	}
}
