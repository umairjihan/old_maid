package com.oldMaid.Bean;

import java.util.ArrayList;
import java.util.List;

public class Deck {
	
	List<Card> decks;
	
	public Deck(){
		this.decks = new ArrayList<Card>();
	}
	
	public void addToDeck (Card card){
		this.decks.add(card);
	}
	public void removeFromDeck (Card card){
		this.decks.remove(card);
	}
	public int getSize (){
		return this.decks.size();
	}

	public List<Card> getDecks() {
		return decks;
	}

	public void setDecks(List<Card> decks) {
		this.decks = decks;
	}

}
