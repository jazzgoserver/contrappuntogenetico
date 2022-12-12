package it.jazz.contrappunto;

import it.jazz.contrappunto.base.Linea;

/**
 * I movimenti (moto delle parti) sono moto parallelo, retto, obliquo,
 * contrario.
 * <ul>
 * <li>Moto parallelo: Le due parti si muovono nella stessa direzione con lo
 * stesso intervallo.
 * <li>Moto retto: Le due parti si muovono nella stessa direzione con diverso
 * intervallo.
 * <li>Moto obliquo: Una parte rimane ferma mentre l’altra parte si muove.
 * <li>Moto contrario: Le due parti si muovono in direzione opposta,
 * avvicinandosi o allontanandosi.
 * </ul>
 * 
 * @author Guido Zuccaro
 */
public class Moto {
	private Sequenza basso;
	private Sequenza soprano;

	public Moto(Linea cantusFirmus, Linea contrappunto, int posizione) {
		basso = new Sequenza(cantusFirmus, posizione);
		soprano = new Sequenza(contrappunto, posizione);
	}

	/**
	 * Moto parallelo
	 * <p>
	 * Le due parti si muovono nella stessa direzione con lo stesso intervallo.
	 * 
	 * @return
	 */
	public boolean isParallelo() {
		return direzione(basso.getIntervallo()) == direzione(soprano
				.getIntervallo())
				&& basso.getIntervallo() == soprano.getIntervallo();
	}

	/**
	 * Moto retto
	 * <p>
	 * Le due parti si muovono nella stessa direzione con diverso intervallo.
	 * 
	 * @return
	 */
	public boolean isRetto() {
		return direzione(basso.getIntervallo()) == direzione(soprano
				.getIntervallo())
				&& basso.getIntervallo() != soprano.getIntervallo();
	}

	/**
	 * Moto obliquo
	 * <p>
	 * Una parte rimane ferma mentre l’altra parte si muove.
	 * 
	 * @return
	 */
	public boolean isObliquo() {
		return (basso.isFerma() && !soprano.isFerma())
				|| (!basso.isFerma() && soprano.isFerma());
	}

	/**
	 * @param intervallo
	 *            intervallo in semitoni tra due note
	 * @return la direzione (il segno) del moto
	 */
	public float direzione(int intervallo) {
		return Math.signum(intervallo);
	}

	/**
	 * Moto contrario
	 * <p>
	 * Le due parti si muovono in direzione opposta, avvicinandosi o
	 * allontanandosi.
	 * 
	 * @return
	 */
	public boolean isContrario() {
		return direzione(basso.getIntervallo()) != direzione(soprano
				.getIntervallo());
	}

}
