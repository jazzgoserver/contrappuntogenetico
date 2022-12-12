package it.jazz.contrappunto.genetico;

import it.jazz.contrappunto.Contrappunto;
import it.jazz.contrappunto.Moto;
import it.jazz.contrappunto.Sequenza;

/**
 * algoritmo genetico: funzione di valutazione
 * <p>
 * <ul>
 * <li>FIXME: attualmente vengono solo conteggiate: utilizzare dei pesi per una
 * convergenza più rapida
 * </ul>
 * <ul>
 * Nel contrappunto semplice utilizziamo esclusivamente consonanze (perfette e
 * imperfette).
 * <li>Divieto di moto parallelo fra consonanze perfette.
 * <li>Da consonanza perfetta a consonanza perfetta: si usa il moto obliquo,
 * oppure il moto contrario con una delle due voci che muove di grado.
 * <li>Da consonanza imperfetta a consonanza perfetta: si usa preferibilmente il
 * moto contrario oppure il moto obliquo; il moto retto si usa solo se una voce
 * muove di grado.
 * <li>Da consonanza imperfetta a consonanza imperfetta: si usano il moto
 * parallelo, il moto contrario e il moto obliquo; il moto retto si usa solo se
 * una voce muove di grado.
 * <li>Da consonanza perfetta a consonanza imperfetta: si usano preferibilmente
 * il moto contrario e il moto obliquo; il moto retto si usa solo se una voce
 * muove di grado.
 * </ul>
 * 
 * @author Guido Zuccaro
 */
public class Fitness {
	/**
	 * FIXME: attualmente vengono solo conteggiate: utilizzare dei pesi per una
	 * convergenza più rapida
	 */
	private static final int VIOLAZIONE = 1;
	private Cromosoma cromosoma;
	/**
	 * il valore di fitness è espresso con un valore positivo che indica la
	 * violazione delle regole (un valore più alto indica un maggior numero di
	 * violazioni)
	 */
	private int fitness = 0;

	public Fitness(Cromosoma cromosoma) {
		this.cromosoma = cromosoma;
		calcola();
	}

	private void calcola() {
		for (int n = 1; n < cromosoma.size(); n++) {

			// intervallo di partenza
			int precedente = getIntervallo(n - 1);
			// intervallo di arrivo
			int corrente = getIntervallo(n);

			// moto parallelo, retto, obliquo, contrario
			Moto moto = new Moto(cromosoma.getCantusFirmus(),
					cromosoma.getContrappunto(), n);

			if (!Contrappunto.isConsonanzaPerfetta(corrente)
					&& !Contrappunto.isConsonanzaPerfetta(corrente))
				fitness += VIOLAZIONE;

			// Divieto di moto parallelo fra consonanze perfette.
			if (Contrappunto.isConsonanzaPerfetta(precedente)
					&& Contrappunto.isConsonanzaPerfetta(corrente)) {
				if (moto.isParallelo())
					fitness += VIOLAZIONE;
			}
			// Da consonanza imperfetta a consonanza perfetta:
			if (Contrappunto.isConsonanzaImperfetta(precedente)
					&& Contrappunto.isConsonanzaPerfetta(corrente)) {
				// si usa preferibilmente il moto contrario oppure il moto
				// obliquo;
				if (moto.isParallelo())
					fitness += VIOLAZIONE;
				// il moto retto si usa solo se una voce muove di grado.
				if (moto.isRetto() && !isGrado(n))
					fitness += VIOLAZIONE;
			}

			// Da consonanza imperfetta a consonanza imperfetta:
			if (Contrappunto.isConsonanzaImperfetta(precedente)
					&& Contrappunto.isConsonanzaImperfetta(corrente)) {
				// si usano il moto parallelo, il moto contrario e il moto
				// obliquo;
				// il moto retto si usa solo se una voce muove di grado.
				if (moto.isRetto() && !isGrado(n))
					fitness += VIOLAZIONE;
			}

			// Da consonanza perfetta a consonanza imperfetta:
			if (Contrappunto.isConsonanzaPerfetta(precedente)
					&& Contrappunto.isConsonanzaImperfetta(corrente)) {
				// si usano preferibilmente il moto contrario e il moto obliquo;
				if (moto.isParallelo())
					fitness += VIOLAZIONE;
				// il moto retto si usa solo se una voce muove di grado.
				if (moto.isRetto() && !isGrado(n))
					fitness += VIOLAZIONE;
			}
		}
	}

	/**
	 * @return intervallo in semitoni tra le due note
	 */
	private int getIntervallo(int posizione) {
		return cromosoma.getContrappunto().get(posizione).getAltezza()
				- cromosoma.getCantusFirmus().get(posizione).getAltezza();
	}

	/**
	 * @param posizione
	 * @return almeno una delle due linee melodiche si muove di un grado lungo
	 *         la scala?
	 */
	public boolean isGrado(int posizione) {
		Sequenza basso = new Sequenza(cromosoma.getCantusFirmus(), posizione);
		Sequenza soprano = new Sequenza(cromosoma.getContrappunto(), posizione);
		return basso.isGrado() || soprano.isGrado();
	}

	public Cromosoma getCromosoma() {
		return cromosoma;
	}

	public int getFitness() {
		return fitness;
	}
}
