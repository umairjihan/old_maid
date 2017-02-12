package com.oldMaid.Bean;

public class Player {
	
	Deck deck;
	String name;
	
	public Player(){
		this.deck = new Deck();
	}
	public Player(String name){
		this.name = name;
		this.deck = new Deck();
	}
	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
