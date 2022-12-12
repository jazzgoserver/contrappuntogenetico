package it.jazz.contrappunto.genetico;

import it.jazz.contrappunto.Melodia;
import it.jazz.contrappunto.util.Comuni;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * algoritmo genetico: insieme di soluzioni possibili
 * 
 * @author Guido Zuccaro
 */
public class Popolazione implements Comuni {
	private static final int SELEZIONE = Melodia.LUNGHEZZA * Melodia.LUNGHEZZA
			* Melodia.LUNGHEZZA;
	private static final int CROMOSOMI = SELEZIONE * 2;

	private Melodia cantusFirmus;
	private List<Cromosoma> cromosomi = new ArrayList<>();

	public Popolazione(Melodia cantusFirmus) {
		this.cantusFirmus = cantusFirmus;
		for (int c = 0; c < CROMOSOMI; c++)
			cromosomi.add(new Cromosoma(cantusFirmus));

		// ordina in base alla funzione di valutazione
		Collections.sort(cromosomi);
	}

	/**
	 * genera la popolazione successiva attraverso un meccanismo di selezione
	 * naturale
	 * 
	 * @param precedente
	 */
	public Popolazione(Popolazione precedente) {
		this.cantusFirmus = precedente.cantusFirmus;

		// applica la selezione naturale alla popolazione precedente
		for (int c = 0; c < SELEZIONE; c++)
			cromosomi.add(precedente.cromosomi.get(c));

		// applica la mutazione all'elemnto peggiore selezionato
		peggiore().muta();

		// incrocia gli elementi selezionati
		for (int c = 0; c < SELEZIONE; c++) {
			Cromosoma madre = cromosomi.get(caso.nextInt(SELEZIONE));
			Cromosoma padre = cromosomi.get(caso.nextInt(SELEZIONE));
			Cromosoma figlio = madre.crossover(padre);
			cromosomi.add(figlio);
		}

		// ordina in base alla funzione di valutazione
		Collections.sort(cromosomi);
	}

	public Melodia getCantusFirmus() {
		return cantusFirmus;
	}

	public Cromosoma milgiore() {
		return cromosomi.get(0);
	}

	public Cromosoma peggiore() {
		return cromosomi.get(cromosomi.size() - 1);
	}
}
