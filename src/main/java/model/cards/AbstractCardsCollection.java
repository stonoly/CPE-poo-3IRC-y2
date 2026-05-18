package model.cards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import allShared.ICardsCollection;

/**
 * Classe qui décrit les attributs et méthodes commun(e)s à toutes les Collections de cartes
 * elle implémente l'interface Iterable pour être parcourue par un Iterator [Design pattern]
 * 
 * Elle s'appuie sur les méthodes de sa Collection enveloppée (ici List)  
 * enrichies des méthodes shuffle(), max() et iterator()
 * [On peut la voir comme un adapter de List - Design Pattern]
 * 
 * [Question du Carnet de Bord : qu'est-ce qui justifie l'existence de cette classe ?
 * La réponse n'est pas "... factoriser le code commun à ses classes dérivées" 
 * (même si cette phrase est néanmoins vraie ;-)) ]
 * 
 * @author francoise.perrin
 */
public abstract class AbstractCardsCollection implements ICardsCollection, Iterable<Card> {

	protected final List<Card> cards;

	public AbstractCardsCollection() {
		cards = new ArrayList<Card>();
	}

	public AbstractCardsCollection(Collection<Card> collection) {
		cards = new ArrayList<Card>(collection);
	}

	public AbstractCardsCollection(ICardsCollection iCardsCollection) {
		this();
		for (Card card : iCardsCollection) {
			this.cards.add(card);
		}
	}

	@Override
	public String toString() {
		return "[" + cards + "]";
	}

	/**
	 * Mélange les cartes de manière aléatoire
	 *
	 * Ecrivez et testez cette méthode de 2 manières :
	 *  1 - en utilisant la méthode native shuffle() de la classe Collections
	 *  2 - en utilisant la méthode swap() et un nombre aléatoire (Random)
	 */
	@Override
	public void shuffle() {
		Random random = new Random();
		for (int i = 0; i < this.cards.size(); i++) {
			int rand_num = random.nextInt(this.cards.size());
			Collections.swap(this.cards, i, rand_num);
		}
	}
//	@Override
//	public final void shuffle() {
//		Collections.shuffle(this.cards);
//	}

	@Override
	public Card removeTopCard() {
		Card card = null;
		if (!this.isEmpty()){
			card = this.cards.get(0);
			this.cards.remove(0);
		}
		return card;
	}

	@Override
	public Card removeCard(int index) {
		Card card = null;
		if (index < this.size() && !this.isEmpty()){
			card = this.cards.get(index);
			this.cards.remove(index);
		}
		return card;
	}

	@Override
	public void addCard(Card pc) {
		cards.add(pc);
	}

	@Override
	public void clear() {
		this.cards.clear();
	}

	@Override
	public int size() {
		int ret = 0;
		ret = this.cards.size();
		return ret;
	}

	@Override
	public Card max() {
		Card card = null;
		if (!this.isEmpty()) {
			card = Collections.max(this.cards);
		}
		return  card;
	}

	@Override
	public Card max(Comparator<Card> comparator) {
		Card card = null;
		if (!this.isEmpty()) {
			card = Collections.max(this.cards, comparator);
		}
		return  card;
	}

	@Override
	public void sort() {
		Collections.sort(this.cards);
	}

	@Override
	public void sort(Comparator<Card> comparator) {
		Collections.sort(this.cards, comparator);
	}

	@Override
	public boolean isEmpty() {
		boolean ret = this.cards.isEmpty();
		return ret;
	}

	@Override
	public Iterator<Card> iterator() {

		return new Iterator<Card>() {
			Iterator<Card> it =  cards.iterator();
			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public Card next() {
				return it.next();
			}

		};
	}

}
