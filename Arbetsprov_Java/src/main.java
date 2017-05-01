import java.util.Scanner;

public class main {

	public static final int OK = 0;
	public static final int NOT_IN_STOCK = 1;
	public static final int DOES_NOT_EXIST = 2;

	public static void main(String[] args) {
		BookList m_bookList = new BooklistImplementation();
		m_bookList.ReadFromFile();
		Cart m_cart = new Cart();
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to the book store. please enter your commands. "
				+ "Enter \"help\" for a list of available commands");

		while (true) {
			String[] input = scanner.nextLine().split(" ", 2);

			switch (input[0].toLowerCase()) {
			case "help":
				System.out.println(
						"List of commands:\n" + "List <search string> to list books. Enter * to list all books\n"
								+ "Buy <search string> to add to your cart\n"
								+ "Remove <search string> to remove from your cart\n"
								+ "Checkout to checkout and discard cart\n" + "View to view cart\n"
								+ "Discard to discard cart without buying\n" + "Exit to exit program");
				break;

			case "list":
				if (input.length <= 1) {
					System.out.println("No search string entered");
					break;
				}
				Book[] books = m_bookList.List(input[1]);
				System.out.println("Found " + books.length + " books.");
				for (Book b : books)
					System.out.println(b.GetFullString());
				break;

			case "buy":
				if (input.length <= 1) {
					System.out.println("No search string entered");
					break;
				}

				books = m_bookList.List(input[1]);
				int[] status = new int[1];
				Book chosenBook = new Book();

				if (books.length > 1) { // if search generate more than one
										// result, chose which one you want
					boolean success = false;
					while (!success) {
						System.out.println(
								"Search generated more than one result. Use numbers to choose which one you want");
						for (int i = 0; i < books.length; i++)
							System.out.println(i + ". " + books[i].GetFullString());

						int chosen = 0;
						try {
							chosen = Integer.parseInt(scanner.nextLine());
							if (chosen < books.length) {
								chosenBook = books[chosen];
								success = true;
							} else
								System.out.println("Number out of range");
						} catch (Exception e) {
							System.out.println("Unknown input");

						}
					}

				} else if (books.length < 1) {
					System.out.println("No books found");
					break;
				} else {
					chosenBook = books[0];
				}

				status = m_bookList.Buy(books);

				if (status[0] == OK) {
					m_cart.AddToCart(chosenBook);
					System.out.println("'" + chosenBook.GetSearchString() + "' added to cart");
				} else if (status[0] == NOT_IN_STOCK) {
					System.out.println("'" + chosenBook.GetSearchString() + "' is not in stock");
				} else if (status[0] == DOES_NOT_EXIST) {
					System.out.println("'" + chosenBook.GetSearchString() + "' does not exist");

				}
				break;

			case "remove":
				if (input.length <= 1) {
					System.out.println("No search string entered");
					break;
				}

				if (input[1].equals("*"))
					m_cart.discard();

				books = m_bookList.List(input[1]);
				if (books.length < 1) {
					System.out.println("No books found");
					break;
				}
				m_cart.RemoveFromCart(books);
				break;

			case "checkout":
				m_bookList.Remove(m_cart.Checkout());
				break;

			case "view":
				m_cart.viewCart();
				break;

			case "discard":
				m_cart.discard();
				break;

			case "exit":
				System.exit(0);
				break;

			default:
				System.out.println("Command not recognized");
			}
		}
	}

}
