package com.oldMaid.Bean;

public class Card {

	String cardName;
	String cardSign;
	
	public Card(){
		
	}
	public Card(String cardName, String cardSign){
		this.cardName = cardName;
		this.cardSign = cardSign;
	}

	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardSign() {
		return cardSign;
	}
	public void setCardSign(String cardSign) {
		this.cardSign = cardSign;
	}
}
