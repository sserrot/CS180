import java.util.Scanner;

public class wordGame {

	public static void main(String[] args) {
Scanner keyboard = new Scanner(System.in);
// welcome and gather player info
System.out.println("Greetings!");
System.out.println("Player 1, please enter your name: ");
String player1 = keyboard.next();
System.out.println("Player 2, please enter your name: ");
String player2 = keyboard.next();
// initialize playercount for round wins
int player1count = 0;
int player2count = 0;
// find limit to rounds
System.out.println("How many rounds would you like to play? ");
String rounds = keyboard.next();
int roundsToPlay = Integer.parseInt(rounds);
// initialize counter for rounds played
int countRounds = 1;
// get initial inputs for game
System.out.println("-----------Playing round " + countRounds + "----------" );
System.out.println(player1 + ", please enter the first word: ");
String word1 = keyboard.next();
word1 = word1.toUpperCase();
// create a string for failed words and a loop to prompt a new word
String word1fail = "on";
while (word1.length() > 3 || word1fail.length() > 3) {
	System.out.println("The word must have at most 3 letters. Please try again:");
	System.out.println(player1 + ", please enter the first word: ");
	word1fail = keyboard.next();
	word1fail = word1fail.toUpperCase();
	if(word1fail.length() <= 3) {
		word1 = word1fail;
		break;
	}
}
// for loop? this might help with counting rounds because it's unknown (for = rounds to play, count rounds >0 counter++)
do {
	// player 2 tries
	System.out.println(player2 + " please enter a word containing '" + word1 +"':");
	String checkToWord1 = keyboard.next();
	checkToWord1 = checkToWord1.toUpperCase();
	// check length
	if (checkToWord1.length() < word1.length()) {
		System.out.println(checkToWord1.toUpperCase() +" is not longer than the previous word, " + word1.toUpperCase());
		System.out.println(player1 + " wins!");
		player1count++;
		System.out.println("--------------");
		System.out.println("FINAL RESULT:" + player1 +":" + player1count + "--" + player2 + ":" + player2count);
		countRounds++;
		break;
	}
	// check if it contains word
	if (checkToWord1.contains(word1) == false) {
	System.out.println(checkToWord1.toUpperCase() +  " does not contain " + word1.toUpperCase());
	System.out.println(player1 + " wins!");
	player1count++;
	System.out.println("--------------");
	System.out.println("FINAL RESULT:" + player1 +":" + player1count + "--" + player2 + ":" + player2count);
	countRounds++;
	break;
	}
	// player 1 tries
	System.out.println(player1 + " please enter a word containing '" + word1 +"':");
	String secondcheckToWord1 = keyboard.next();
	secondcheckToWord1 = secondcheckToWord1.toUpperCase();
	if (secondcheckToWord1.length() < checkToWord1.length()) {
		System.out.println(secondcheckToWord1.toUpperCase() +" is not longer than the previous word, " + checkToWord1.toUpperCase());
		System.out.println(player2 + " wins!");
		player2count++;
		System.out.println("--------------");
		System.out.println("FINAL RESULT:" + player1 +":" + player1count + "--" + player2 + ":" + player2count);
		countRounds++;
		break;
	}
	if (secondcheckToWord1.contains(word1) == false) {
		System.out.println(secondcheckToWord1.toUpperCase() +  " does not contain " + word1.toUpperCase());
		System.out.println(player2 + " wins!");
		player2count++;
		System.out.println("--------------");
		System.out.println("FINAL RESULT:" + player1 +":" + player1count + "--" + player2 + ":" + player2count);
		countRounds++;
		break;
	}
}  while (roundsToPlay == countRounds);


	

}
}
