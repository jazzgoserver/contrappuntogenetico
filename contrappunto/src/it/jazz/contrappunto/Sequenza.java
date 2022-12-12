package it.jazz.contrappunto;

import it.jazz.contrappunto.base.Linea;
import it.jazz.contrappunto.teoria.Nota;

/**
 * prende in esame due note consecutive di una linea melodica
 * 
 * @author Guido Zuccaro
 */
public class Sequenza {
	private Nota precedente;
	private Nota corrente;

	public Sequenza(Linea linea, int posizione) {
		precedente = linea.get(posizione - 1);
		corrente = linea.get(posizione);
	}

	/**
	 * @return la melodia si muove di un grado muove lungo la scala in maniera
	 *         ascendente?
	 */
	public boolean isAscendente() {
		return corrente.getGrado() == precedente.getGrado()
				+ Melodia.ASCENDENTE;
	}

	/**
	 * @return la melodia si muove di un grado muove lungo la scala in maniera
	 *         discendente?
	 */
	public boolean isDiscendente() {
		return corrente.getGrado() == precedente.getGrado()
				+ Melodia.DISCENDENTE;
	}

	/**
	 * @return la melodia si muove di un grado lungo la scala?
	 */
	public boolean isGrado() {
		return isAscendente() || isDiscendente();
	}

	/**
	 * @return la melodia ripete la stessa nota?
	 */
	public boolean isFerma() {
		return corrente.getGrado() == precedente.getGrado();
	}

	/**
	 * @return la melodia esegue un salto?
	 */
	public boolean isSalto() {
		return !isFerma() && !isGrado();
	}

	/**
	 * @return intervallo in semitoni tra le due note
	 */
	public int getIntervallo() {
		return corrente.getAltezza() - precedente.getAltezza();
	}

	public Nota getPrecedente() {
		return precedente;
	}

	public Nota getCorrente() {
		return corrente;
	}
}
