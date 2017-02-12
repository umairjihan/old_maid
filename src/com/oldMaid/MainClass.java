package com.oldMaid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import com.oldMaid.Bean.Card;
import com.oldMaid.Bean.Player;

public class MainClass {
	
	static List<Card> allCards;
	static List<Player> players;
	public static void main(String[] args) {
		generateCards();
		generatePlayer();
		distributeCardsToPlayers();
		int playerTurn = 0;
		while(true){
			ShowInWindow();
			Player player = players.get(playerTurn);
			List<Card> duplicates = findDuplicates( player.getDeck().getDecks());
			if(duplicates.size()>0){
				if(playerTurn==0)
					UserInput("You hav a common card....Press \"ENTER\" to continue...");
				else
					UserInput(player.getName()+"has common card. Press \"ENTER\" to continue...");
				for(int i=0;i<duplicates.size();i++)
					player.getDeck().removeFromDeck(duplicates.get(i));
			}else{
				if(playerTurn==0){
					int expextedCardFrom = nextPlayer(playerTurn, false);
					System.out.format("\"NO MATCH.\" Please take a card from "+players.get(expextedCardFrom).getName());
					Scanner in = new Scanner(System.in); 
				    int n = in.nextInt();
					n--;
					player.getDeck().addToDeck(players.get(expextedCardFrom).getDeck().getDecks().get(n));
					players.get(expextedCardFrom).getDeck().getDecks().remove(n);
				} else {
					int expextedCardFrom = nextPlayer(playerTurn, false);
					UserInput("\"NO MATCH.\" Please Enter to Continue");
					Random r = new Random();
					int randint = (Math.abs(r.nextInt()) % players.get(expextedCardFrom).getDeck().getSize());
					
					player.getDeck().addToDeck(players.get(expextedCardFrom).getDeck().getDecks().get(randint));
					players.get(expextedCardFrom).getDeck().getDecks().remove(randint);
				}
				duplicates = findDuplicates( player.getDeck().getDecks());
				if(duplicates.size()>0){
					ShowInWindow();
					if(playerTurn==0)
						UserInput("You hav a common card....Press \"ENTER\" to continue...");
					else
						UserInput(player.getName()+"has common card. Press \"ENTER\" to continue...");
					for(int i=0;i<duplicates.size();i++)
						player.getDeck().removeFromDeck(duplicates.get(i));
				}
				
			}
			playerTurn = nextPlayer(playerTurn, true);
			if(gameIsOver()){
				UserInput("\"GAME IS OVER\" ENTER to continue...");
				return;
			}
				
			
		}
	}
	public final static void clearConsole()
	{
	    try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	        //  Handle any exceptions.
	    }
	}
	private static boolean gameIsOver() {
		boolean oneCard = false;  
		for(int i=0;i<players.size();i++){
			Player player = players.get(i);
			if(player.getDeck().getSize()==1 && player.getDeck().getDecks().get(0).getCardName().equals("Sceleton"))
				oneCard = true;
		}
		if(oneCard){
			for(int i=0;i<players.size();i++){
				Player player = players.get(i);
				if(player.getDeck().getSize()==1 && player.getDeck().getDecks().get(0).getCardName().equals("Sceleton"))
					oneCard = true;
				else if(player.getDeck().getSize()!=0){
					return false;
				}
			}
		}else
			return false;
		return true;
	}
	private static int nextPlayer(int cuuentTurn, boolean turnChange) {
		if(!turnChange){
			while(true){
				if(cuuentTurn==3)cuuentTurn=0;
				else cuuentTurn++;
				if(players.get(cuuentTurn).getDeck().getSize()==0)
					continue;
				else
					return cuuentTurn;
			}
		}else{
			if(cuuentTurn==3)cuuentTurn=0;
			else cuuentTurn++;
			return cuuentTurn;
		}
	}
	private static void UserInput(String string) {
		System.out.format(string);
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		
	}
	private static List<Card> findDuplicates(List<Card> list) {
		List<Card> tempCards = new ArrayList<Card>(list);
		List<Card> duplicates = new ArrayList<Card>();
	    for(int i=0;i<tempCards.size();) {
	        Card temp = tempCards.get(i);
	        tempCards.remove(i);
	        for(int j=0;j<tempCards.size();j++){
	        	if(temp.getCardName().equals(tempCards.get(j).getCardName())){
	        		duplicates.add(temp);
	        		duplicates.add(tempCards.get(j));
	        		tempCards.remove(j);
	        	}
	        }
	    }
	    
	    return duplicates;
	}

	private static void ShowInWindow() {

		clearConsole();
		for(int playerIndex=0;playerIndex<players.size();playerIndex++){
			String header = "+";
			String row1 = "|";
			String row2 = "|";
			String row3 = "|";
			Player player = players.get(playerIndex);
			System.out.format(player.getName()+":%n");
			for(int cardIndex=0;cardIndex<player.getDeck().getSize();cardIndex++){
				header = header + "--------------+";
				if(playerIndex!=0){
					row1 = row1 + String.format(" %-12s |", " ");
					row2 = row2 + String.format(" %-12s |", cardIndex+1);
					row3 = row3 + String.format(" %-12s |", " ");
				}else{
					row1 = row1 + String.format(" %-12s |", cardIndex+1);
					row2 = row2 + String.format(" %-12s |", player.getDeck().getDecks().get(cardIndex).getCardName());
					row3 = row3 + String.format(" %-12s |", player.getDeck().getDecks().get(cardIndex).getCardSign());
				}
			}
			row1 = row1 + "%n";
			row2 = row2 + "%n";
			row3 = row3 + "%n";
			header = header + "%n";
			System.out.format(header);
			System.out.format(row1);
			System.out.format(row2);
			System.out.format(row3);
			System.out.format(header);
		}
		
	}

	private static void distributeCardsToPlayers() {
		List<Card> tempCards = new ArrayList<Card>(allCards);
		for(int i=0,k=0;i<21;i++,k++){
			if(k==4)k=0;
			Random r = new Random();
			int randint = (Math.abs(r.nextInt()) % (21-i));
			players.get(k).getDeck().addToDeck(tempCards.get(randint));
			tempCards.remove(randint);
		}
	}

	public static void generateCards(){
		allCards = new ArrayList<Card>();
		String[] cardName = {"Goblin", "Archer", "Golem", "Wizard", "King", "Queen", "Bomber", "Pekka", "Berberian", "Healer"};
		String[] cardSign = {"--!--", "-->", "@*@", "* * *", "$$$", "*$*", "000", "XxxX", "*BB*", "^$$^"};
		for(int i=0;i<10;i++){
			allCards.add(new Card(cardName[i], cardSign[i]));
			allCards.add(new Card(cardName[i], cardSign[i]));
		}
		allCards.add(new Card("Sceleton", " @@@ "));
	}
	public static void generatePlayer(){
		players = new ArrayList<Player>();
		players.add(new Player("Player 1"));
		players.add(new Player("Player 2"));
		players.add(new Player("Player 3"));
		players.add(new Player("Player 4"));
	}

}
