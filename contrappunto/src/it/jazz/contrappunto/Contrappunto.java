package it.jazz.contrappunto;

import it.jazz.contrappunto.base.Linea;
import it.jazz.contrappunto.costanti.Intervalli;
import it.jazz.contrappunto.teoria.Nota;
import it.jazz.contrappunto.teoria.Scala;
import it.jazz.contrappunto.util.Comuni;

import java.util.ArrayList;
import java.util.List;

/**
 * contrappunto di prima specie
 * 
 * @author Guido Zuccaro
 * @see https://appunticontrappunti.wordpress.com/elementi-di-contrappunto/
 * @see http://www.explodingart.com/jmusic/
 */
public class Contrappunto implements Comuni {
	private Melodia cantusFirmus;
	private Linea contrappunto = new Linea();

	public Contrappunto(Melodia cantusFirmus) {
		this.cantusFirmus = cantusFirmus;
		genera();
	}

	private void genera() {
		for (int n = 0; n < cantusFirmus.getLunghezza(); n++) {
			List<Nota> possibili = filtra(n);
			// sceglie una nota del contrappunto
			// tra le consonanze perfettte ed imperfettte
			Nota scelta = possibili.get(caso.nextInt(possibili.size()));
			contrappunto.add(scelta);
		}
	}

	/**
	 * filtra gli intervalli possibili (consonanze perfettte ed imperfettte)<br>
	 * tra le note delle scale
	 * 
	 * @param posizione
	 * @return
	 */
	private List<Nota> filtra(int posizione) {
		List<Nota> possibili = new ArrayList<>();
		Nota nota = cantusFirmus.get(posizione);
		Scala scala = cantusFirmus.getScala();
		for (int n = 0; n < scala.size(); n++) {
			int intervallo = scala.get(n).getAltezza() - nota.getAltezza();
			if (isConsonanzaPerfetta(intervallo)
					|| isConsonanzaImperfetta(intervallo))
				possibili.add(scala.get(n));
		}
		return possibili;
	}

	/**
	 * @param intervallo
	 * @return l'intervallo appartien alle consonanze perfette?
	 */
	public static boolean isConsonanzaPerfetta(int intervallo) {
		List<Integer> consonanze = new ArrayList<>();
		for (Integer semitoni : Intervalli.CONSONANZE_PERFETTE)
			consonanze.add(semitoni);
		return consonanze.contains(intervallo);
	}

	/**
	 * @param intervallo
	 * @return l'intervallo appartien alle consonanze imperfette?
	 */
	public static boolean isConsonanzaImperfetta(int intervallo) {
		List<Integer> consonanze = new ArrayList<>();
		for (Integer semitoni : Intervalli.CONSONANZE_IMPERFETTE)
			consonanze.add(semitoni);
		return consonanze.contains(intervallo);
	}

	public Melodia getCantusFirmus() {
		return cantusFirmus;
	}

	public Linea getContrappunto() {
		return contrappunto;
	}

}
