package com.oldMaid.Bean;

public class Card {

	String cardName;
	String cardSign;
	int pairId;

	public Card(){
		
	}
	public Card(String cardName, String cardSign, int pairId){
		this.cardName = cardName;
		this.cardSign = cardSign;
		this.pairId   = pairId;
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
	public int getPairId() {
		return pairId;
	}

	public void setPairId(int pairId) {
		this.pairId = pairId;
	}
}
