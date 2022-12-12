package it.jazz.contrappunto.genetico;

import it.jazz.contrappunto.Contrappunto;
import it.jazz.contrappunto.Melodia;
import it.jazz.contrappunto.base.Linea;
import it.jazz.contrappunto.util.Comuni;

import java.util.ArrayList;
import java.util.List;

import jm.music.data.Part;
import jm.music.data.Score;
import jm.util.Play;

/**
 * algoritmo genetico: ingola ipotesi di soluzione
 * 
 * @author Guido Zuccaro
 */
public class Cromosoma extends Linea implements Comparable<Cromosoma>, Comuni {
	private static final long serialVersionUID = 1L;
	private Melodia cantusFirmus;

	public Cromosoma(Melodia cantusFirmus) {
		this.cantusFirmus = cantusFirmus;
		Contrappunto contrappunto = new Contrappunto(cantusFirmus);
		addAll(contrappunto.getContrappunto());
	}

	/**
	 * costruttore di copia
	 * 
	 * @param origine
	 */
	public Cromosoma(Cromosoma origine) {
		this.cantusFirmus = origine.cantusFirmus;
		addAll(origine);
	}

	/**
	 * operatore genetico: mutazione
	 * 
	 * @return il cromosoma stesso mutato
	 */
	public Cromosoma muta() {
		set(caso.nextInt(size()),
				cantusFirmus.getScala().get(
						caso.nextInt(cantusFirmus.getScala().size())));
		return this;
	}

	/**
	 * operatore genetico: crossover
	 * 
	 * @param padre
	 * @return un nuovo coromosoma figlio ottenuto incrociando madre(this) e
	 *         padre
	 */
	public Cromosoma crossover(Cromosoma padre) {
		// madre
		Cromosoma figlio = new Cromosoma(this);
		int punto = caso.nextInt(size());
		for (int g = punto; g < size(); g++)
			set(g, padre.get(g));
		return figlio;
	}

	/**
	 * @return la lista delgi intervalli del contrappunto
	 */
	public List<Integer> getIntervalli() {
		List<Integer> intervalli = new ArrayList<>();
		for (int n = 0; n < getCantusFirmus().size(); n++) {
			int intervallo = getContrappunto().get(n).getAltezza()
					- getCantusFirmus().get(n).getAltezza();
			intervalli.add(intervallo);
		}
		return intervalli;
	}

	/**
	 * funzione di valutazione:
	 * <ul>
	 * <li>restituisce il numero di violazioni delle regole del contrappunto di
	 * prima specie:
	 * <li>le linee di contrappunto valide avranno valore 0
	 * 
	 * @return il valore di fitness del cromosoma
	 */
	public int fitness() {
		return new Fitness(this).getFitness();
	}

	public Melodia getCantusFirmus() {
		return cantusFirmus;
	}

	public Linea getContrappunto() {
		return this;
	}

	/**
	 * Consideriamo innanzitutto il contrappunto a due voci:
	 * <ul>
	 * <li>chiamiamo soprano la voce superiore (chiave di violino) e
	 * <li>basso la voce inferiore (chiave di basso).
	 */
	public void suona() {
		Score spartito = new Score("contrappunto");

		Part soprano = new Part("soprano");
		soprano.add(getContrappunto().toPhrase());
		spartito.add(soprano);

		Part basso = new Part("basso");
		basso.add(getCantusFirmus().toPhrase());
		spartito.add(basso);

		Play.midi(spartito);
	}

	/**
	 * implementa l'interfaccia Comparable per poter utilizzare
	 * Collections.sort()
	 * <p>
	 * ordina in ordine crescente per valore di fitness (numero di violazioni
	 * delle regole del contrappunto di prima specie)
	 */
	@Override
	public int compareTo(Cromosoma altro) {
		return this.fitness() - altro.fitness();
	}

	@Override
	public String toString() {
		return new ArrayList<>(this) + Comuni.DEBUG + fitness();
	}
}
