package it.jazz.contrappunto.base;

import it.jazz.contrappunto.teoria.Nota;

import java.util.ArrayList;

import jm.JMC;
import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.Play;

public class Linea extends ArrayList<Nota> implements JMC {
	private static final long serialVersionUID = 1L;

	public void suona() {
		Play.midi(new Score(new Part(toPhrase())));
	}

	public Phrase toPhrase() {
		Phrase frase = new Phrase();
		for (Nota nota : this)
			frase.add(new Note(C4 + nota.getAltezza(), EIGHTH_NOTE));
		return frase;
	}
}
