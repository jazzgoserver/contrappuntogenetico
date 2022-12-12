package it.jazz.contrappunto;

import it.jazz.contrappunto.base.Linea;
import it.jazz.contrappunto.costanti.Gradi;
import it.jazz.contrappunto.teoria.Nota;
import it.jazz.contrappunto.teoria.Scala;
import it.jazz.contrappunto.util.Comuni;

/**
 * <ul>
 * <li>La melodia può iniziare con la tonica (I) oppure con la dominante (V).
 * Conclude sempre con la tonica (I).
 * <li>La melodia si muove prevalentemente per grado congiunto (scala), ma
 * include alcuni salti che garantiscano varietà e tensione.
 * <li>La melodia presenta un culmine acuto, raggiunto una singola volta.
 * L’ambito complessivo (considerando quindi la nota più grave e la nota più
 * acuta) non supera di molto l’ottava.
 * <li>La melodia evita salti dissonanti.
 * <li>La melodia deve avere un chiaro fraseggio.
 * <li>Un suggerimento utile: un salto verso l’alto (intervalli di quarta o
 * superiori) è seguito da una discesa per grado congiunto (scala); un salto
 * verso il basso (intervalli di quarta o superiori) è seguito da una salita per
 * grado congiunto (scala).
 * 
 * 
 * @author Guido Zuccaro
 */
public class Melodia extends Linea implements Comuni, Gradi {
	private static final long serialVersionUID = 1L;
	/**
	 * numero di quarti nella battuta
	 */
	public static final int BATTUTA = 4;
	/**
	 * lunghezza di default di una melodia
	 */
	public static final int LUNGHEZZA = 4 * BATTUTA;
	/*
	 * direzione del moto della melodia sulla scala
	 */
	public static final int ASCENDENTE = +1;
	public static final int DISCENDENTE = -1;
	/**
	 * scala di riferimento
	 */
	private Scala scala;
	private int lunghezza;

	public Melodia(Scala scala, int lunghezza) {
		this.scala = scala;
		this.lunghezza = lunghezza;
		genera();
	}

	private void genera() {
		// La melodia può iniziare con la tonica (I) oppure con la dominante (V)
		add(testa());
		for (int n = 1; n < lunghezza - 1; n++)
			add(prossima());
		// La melodia conclude sempre con la tonica
		add(scala.get(PRIMO));
	}

	/**
	 * La melodia può iniziare con la tonica (I) oppure con la dominante (V)
	 */
	private Nota testa() {
		Nota[] scelte = { scala.get(PRIMO), scala.get(QUINTO) };
		return scelte[caso.nextInt(scelte.length)];
	}

	/**
	 * La melodia si muove prevalentemente per grado congiunto (scala)
	 * 
	 * @return
	 */
	private Nota prossima() {
		int grado = precedente().getGrado();
		int[] scelte = { ASCENDENTE, DISCENDENTE };
		grado += scelte[caso.nextInt(scelte.length)];
		grado = limiti(grado);
		return scala.get(grado);
	}

	/**
	 * L’ambito complessivo non supera di molto l’ottava
	 * 
	 * @return un grado entro i limiti della scala di riferimento
	 */
	private int limiti(int grado) {
		return grado >= 0 && grado < scala.size() ? grado :
		// La melodia include alcuni salti che garantiscano varietà e tensione
				caso.nextInt(scala.size());
	}

	/**
	 * @return la nota precedente della melodia
	 */
	private Nota precedente() {
		return get(size() - 1);
	}

	public Scala getScala() {
		return scala;
	}

	public int getLunghezza() {
		return lunghezza;
	}
}
