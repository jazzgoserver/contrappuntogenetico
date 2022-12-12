package it.jazz.contrappunto.genetico;

import it.jazz.contrappunto.Melodia;
import it.jazz.contrappunto.teoria.Scala;
import it.jazz.contrappunto.util.Comuni;
import it.jazz.contrappunto.util.Log;

/**
 * prova a generare linee di contrappunto valide usando un algoritmo genetico
 * 
 * @author Guido Zuccaro
 */
public class Genetico implements Comuni {
	public static void main(String[] args) {

		// sceglie la scala di riferimento della melodia
		Scala maggiore = new Scala(Scala.MAGGIORE);

		// genera il cantus firmus (viene usata come linea di basso)
		Melodia cantusFirmus = new Melodia(maggiore, Melodia.LUNGHEZZA);

		// genera casualmente una popolazione di soluzioni
		// (linee di contrappunto)
		Popolazione popolazione = new Popolazione(cantusFirmus);
		for (int p = 0; p < Melodia.LUNGHEZZA; p++) {

			// genera la nuova popolazione
			popolazione = new Popolazione(popolazione);

			// mostra lo spartito
			Log.debug("milgiore" + DEBUG + popolazione.milgiore());
			Log.debug("popolazione" + DEBUG + p);
		}

		// suona lo spatito
		Cromosoma milgiore = popolazione.milgiore();
		// milgiore.suona();
		Log.debug("contrappunto" + DEBUG + milgiore);
		Log.debug("intervalli" + DEBUG + milgiore.getIntervalli());
		Log.debug("cantus firmus" + DEBUG + milgiore.getCantusFirmus());
	}
}
