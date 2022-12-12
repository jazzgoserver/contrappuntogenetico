package it.jazz.contrappunto.costanti;

/**
 * Nel contrappunto gli intervalli sono suddivisi fra:
 * <ul>
 * <li>consonanze perfette (unisono, quinta, ottava),
 * <li>consonanze imperfette (terza, sesta)
 * <li>dissonanze (seconda, quarta, settima e tutti gli intervalli eccedenti o
 * diminuiti).
 * 
 * @author Guido Zuccaro
 */
public interface Intervalli {
	// gli intervalli sono derivati dalle note della scala maggiore
	int UNISONO = Note.DO;
	int SECONDA = Note.RE;
	int TERZA = Note.MI;
	int QUARTA = Note.FA;
	int QUINTA = Note.SOL;
	int SESTA = Note.LA;
	int SETTIMA = Note.SI;
	int OTTAVA = 12;
	/**
	 * consonanze perfette (unisono, quinta, ottava)
	 */
	int[] CONSONANZE_PERFETTE = { UNISONO, QUINTA, OTTAVA };
	/**
	 * consonanze imperfette (terza, sesta)
	 */
	int[] CONSONANZE_IMPERFETTE = { TERZA, SESTA };
}
