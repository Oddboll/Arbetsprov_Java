import java.math.BigDecimal;
import java.util.ArrayList;

public class Cart {

	ArrayList<Book> inCart = new ArrayList<Book>();

	public Cart() {
	}

	public void AddToCart(Book... b) {
		for (Book book : b)
			inCart.add(book);
	}

	public void RemoveFromCart(Book... b) {
		for (Book book : b)
			if (inCart.remove(b))
				System.out.println(book.GetSearchString() + " removed from cart");
			else
				System.out.println("Could not find " + book.GetSearchString() + " in cart");

	}

	public BigDecimal GetTotalPrice() {
		BigDecimal totalPrice = BigDecimal.ZERO;
		for (Book b : inCart)
			totalPrice = totalPrice.add(b.GetPrice());
		return totalPrice;
	}

	public Book[] Checkout() {
		System.out.println("Checkout: Total price of " + inCart.size() + " books is: " + GetTotalPrice() + "kr.");
		Book[] boughtBooks = inCart.toArray(new Book[inCart.size()]);
		discard();
		System.out.println("Cart emptied");
		return boughtBooks;
	}

	public void viewCart() {
		if (inCart.size() < 1) {
			System.out.println("Cart is empty");
			return;
		}
		System.out.println("Books in cart: ");
		for (Book b : inCart) {
			System.out.println(b.GetSearchString());
		}
		System.out.println("Total price is " + GetTotalPrice() + "kr.");
	}

	public void discard() {
		inCart = new ArrayList<Book>();
		System.out.println("Cart Emptied.");

	}
}
