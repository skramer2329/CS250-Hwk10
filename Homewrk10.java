import java.util.Scanner;

public class Homewrk10 {

	public static void main(String[] args)

	{

		Scanner stdId = new Scanner(System.in);

		int[] deck = new int[36];
		int nextCard = 0;
		int[] hand = new int[4];
		int chips = 100;
		int totalPlays = 0;
		int totalWins = 0;
		int totalLoss = 0;
		String userInput = " ";

		welcome();
		initDeck(deck);
		shuffleDeck(deck, 256);

		do {
			System.out.println("Play a hand? [y / n]:");
			userInput = stdId.nextLine();

			while (userInput.equalsIgnoreCase("y")) {
				
				++totalPlays;
				int bet = getBet(stdId, chips);

				dealHand(deck, nextCard, hand);
				sortHand(hand);
				displayHand(hand);
				nextCard += 4;
				if (nextCard == 36) {
					initDeck(deck);
					shuffleDeck(deck, 256);
					nextCard = 0;

				}
				System.out.println();

				if (isQuad(hand) == true) {
					System.out.println("You have a Quad!");
					chips += (6545 * bet);
					System.out.println("You have $" + chips);
					++totalWins;
					
				} else if (isFlush(hand) == true) {
					System.out.println("You have a Flush!");
					chips += (123 * bet);
					System.out.println("You have $" + chips);
					++totalWins;
					
				} else if (isStraight(hand) == true) {
					System.out.println("You have a Straight!");
					chips += (79 * bet);
					System.out.println("You have $" + chips);
					++totalWins;
					
				} else if (isStraightFlush(hand) == true) {
					System.out.println("You have a Straight Flush!");
					chips += (2454 * bet);
					System.out.println("You have $" + chips);
					++totalWins;
					
				} else if (isTrip(hand) == true) {
					System.out.println("You have a Triple!");
					chips += (51 * bet);
					System.out.println("You have $" + chips);
					++totalWins;
					
				} else if (is2Pair(hand) == true) {
					System.out.println("You have a two Pair!");
					chips += (23 * bet);
					System.out.println("You have $" + chips);
					++totalWins;
					
				} else if (isPair(hand) == true) {
					System.out.println("You have a Pair!");
					chips += bet;
					System.out.println("You have $" + chips);
					++totalWins;
					
				} else {
					System.out.println("You got a Bubkiss. Whatever that means.");
					chips -= bet;
					System.out.println("You have $" + chips);
					++totalLoss;
				}

				System.out.println();
				System.out.println("Would you like to play again? [y / n]");
				userInput = stdId.nextLine();

			}

		} while (!(userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("n")));

		if (userInput.equalsIgnoreCase("n")) {
			System.out.println("Thanks for playing!");
			report(chips, totalPlays, totalWins, totalLoss);
			
		}

		stdId.close();
	}

	public static void welcome() {
		System.out.println("Welcome to four card Poker.");
		System.out.println("You have $100 to start.");
		System.out.println();
		System.out.println("++++++++++++++++++++++++++++++++++++");
		System.out.println();
	}

	public static int cardValue(int card) {
		return card % 9 + 1;
	}

	public static String cardSuit(int card) {
		String[] suits = { "C", "S", "H", "D" };

		return suits[card / 9];
	}

	public static void initDeck(int[] deck) {

		for (int i = 0; i < deck.length; ++i)
			deck[i] = i;
	}

	public static void shuffleDeck(int[] deck, int n) {

		for (int i = 1; i <= n; ++i) {
			int j = (int) (Math.random() * 36);
			int k = (int) (Math.random() * 36);

			int tmp = deck[j];
			deck[j] = deck[k];
			deck[k] = tmp;
		}
	}

	public static int dealHand(int[] deck, int nextCard, int[] hand) {

		int i;
		int j;
		for (i = 0, j = nextCard; i < 4; ++i, ++j) {
			hand[i] = deck[j];

		}

		return i;

	}

	public static void displayHand(int[] hand) {

		for (int i = 0; i < 4; ++i) {
			System.out.print(cardValue(hand[i]) + cardSuit(hand[i]) + " ");
		}

	}

	public static boolean isQuad(int[] hand) {

		if (cardValue(hand[0]) == cardValue(hand[1]) && cardValue(hand[1]) == cardValue(hand[2])
				&& cardValue(hand[2]) == cardValue(hand[3]))

			return true;
		else
			return false;

	}

	public static boolean isFlush(int[] hand) {// flush isnt working

		if ((cardSuit(hand[0]) == (cardSuit(hand[1]))
				&& (cardSuit(hand[1]) == (cardSuit(hand[2])) && (cardSuit(hand[2]) == (cardSuit(hand[3]))))))

			return true;
		else
			return false;

	}

	public static boolean isStraight(int[] hand) {

		sortHand(hand);
		if ((cardValue(hand[0]) == (cardValue(hand[1])) - 1 && (cardValue(hand[1]) == (cardValue(hand[2])) - 1
				&& (cardValue(hand[2])) == (cardValue(hand[3])) - 1)))
			return true;
		else
			return false;

	}

	public static boolean isStraightFlush(int[] hand) {

		boolean straight = isStraight(hand);
		boolean flush = isFlush(hand);
		if (straight && flush == true)
			return true;
		else
			return false;
	}

	public static boolean isTrip(int[] hand) {// triple is working

		if ((cardValue(hand[0]) == cardValue(hand[1])
				&& (cardValue(hand[1]) == cardValue(hand[2]) && (cardValue(hand[2]) != cardValue(hand[3])))))
			return true;

		else if ((cardValue(hand[1]) == cardValue(hand[2])
				&& (cardValue(hand[2]) == cardValue(hand[3]) && (cardValue(hand[2]) != cardValue(hand[0])))))
			return true;

		else if ((cardValue(hand[2]) == cardValue(hand[3])
				&& (cardValue(hand[3]) == cardValue(hand[0]) && (cardValue(hand[2]) != cardValue(hand[1])))))
			return true;

		else if ((cardValue(hand[3]) == cardValue(hand[0])
				&& (cardValue(hand[0]) == cardValue(hand[1]) && (cardValue(hand[2]) != cardValue(hand[0])))))
			return true;
		else
			return false;
	}

	public static boolean is2Pair(int[] hand) {// two pair is working
		if (cardValue(hand[0]) == cardValue(hand[1]) && cardValue(hand[2]) == cardValue(hand[3])
				&& (cardValue(hand[0]) != cardValue(hand[2])))
			return true;

		else if ((cardValue(hand[0]) == cardValue(hand[2])
				&& (cardValue(hand[1]) == cardValue(hand[3]) && (cardValue(hand[0]) != cardValue(hand[1])))))
			return true;

		else if ((cardValue(hand[0]) == cardValue(hand[3])
				&& (cardValue(hand[1]) == cardValue(hand[2]) && (cardValue(hand[0]) != cardValue(hand[1])))))
			return true;
		else
			return false;
	}

	public static boolean isPair(int[] hand) {// pair is working
		if ((cardValue(hand[0]) == cardValue(hand[1]) || (cardValue(hand[0]) == cardValue(hand[2])
				|| (cardValue(hand[0]) == cardValue(hand[3]) || (cardValue(hand[1]) == cardValue(hand[2])
						|| (cardValue(hand[1]) == cardValue(hand[3]) || (cardValue(hand[2]) == cardValue(hand[3]))))))))
			return true;
		else
			return false;
	}

	public static void sortHand(int[] hand) {
		for (int i = 0; i < hand.length; ++i) {
			int maxLoc = i;
			for (int j = i + 1; j < hand.length; ++j)
				if (cardValue(hand[j]) > cardValue(hand[maxLoc]))
					maxLoc = j;
			int tmp = hand[i];
			hand[i] = hand[maxLoc];
			hand[maxLoc] = tmp;
		}
	}

	public static int getBet(Scanner stdId, int chips) {

		int bet;

		do {
			System.out.println("How much would you like to bet?");
			bet = stdId.nextInt();

		} while (!(bet >= 1 && bet <= chips));

		return bet;
	}

	public static void report(int chips, int totalPlays, int totalWins, int totalLoss) {
		System.out.println("You won a total of " + totalWins + " times.");
		System.out.println("You lost a total of " + totalLoss + " times.");
		System.out.println("You played a total of " + totalPlays + " times.");
		
		if (chips > 100) {
			System.out.println("You won a total of $" + (chips - 100));
		} else if (chips < 100) {
			System.out.println("You lost a total of $" + (100 - chips));
		} else if (chips == 100) {
			System.out.println("You still have 100 chips. You did not win or lose anything.");
		}

	}

	public static void displayDeck(int[] deck) {
		for (int i = 0; i < deck.length; ++i) {

			System.out.println(deck[i]);
		}
	}

}
