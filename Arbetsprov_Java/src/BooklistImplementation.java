import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class BooklistImplementation implements BookList {

	// public:
	public static final int OK = 0;
	public static final int NOT_IN_STOCK = 1;
	public static final int DOES_NOT_EXIST = 2;

	// private:
	private Map<Book, Integer> m_bookList = new LinkedHashMap<Book, Integer>(); // map
																				// with
																				// entire
																				// inventory
																				// and
																				// quantity

	public BooklistImplementation() {

	}

	public void ReadFromFile() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("bookstoredata.txt"));
			String line;
			try {
				while ((line = br.readLine()) != null) {
					String s[] = line.split(";");
					Book newBook;
					newBook = new Book(s[0], s[1], new BigDecimal(s[2].replaceAll(",", "")));
					m_bookList.put(newBook, Integer.parseInt(s[3]));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Book[] List(String searchString) {
		Book[] returnArray;

		if (searchString.equals("*")) {

			Set<Book> keys = m_bookList.keySet();
			returnArray = keys.toArray(new Book[m_bookList.size()]);

			return returnArray;
		}

		ArrayList<Book> foundBooks = new ArrayList<Book>();
		String[] search = searchString.split(" "); // split search string to
													// array
		for (Book book : m_bookList.keySet()) {
			String[] bookString = book.GetSearchString().replace(",", "").split(" "); // get
																						// books
																						// search
																						// string
																						// and
																						// split
																						// to
																						// array
			for (String b : bookString) // iterate over the two arrays and
										// compare
			{
				boolean found = false;
				for (String s : search) {
					if (b.equalsIgnoreCase(s)) {
						foundBooks.add(book);
						found = true;
					}
				}
				if (found) // if already found, don't check again. No duplicates
					break;
			}
		}

		returnArray = foundBooks.toArray(new Book[foundBooks.size()]);
		return returnArray;
	}

	public boolean Add(Book book, int quantity) {
		if (m_bookList.containsKey(book))
			return false;

		m_bookList.put(book, quantity);
		return true;
	}

	public void Remove(Book... b) // if books get sold
	{
		for (Book book : b) {
			if (m_bookList.containsKey(book)) // Should never fail, but just to
												// be sure
				m_bookList.put(book, m_bookList.get(book) - 1);
		}
	}

	public int[] Buy(Book... b) {
		ArrayList<Integer> status = new ArrayList<Integer>();
		for (Book book : b) {
			if (m_bookList.containsKey(book)) {
				Integer value = m_bookList.get(book);
				if (value > 0)
					status.add(OK);
				else if (value == 0)
					status.add(NOT_IN_STOCK);
			} else
				status.add(DOES_NOT_EXIST);
		}

		// Maybe change later
		int[] returnArray = new int[status.size()];

		for (int i = 0; i < status.size(); i++)
			returnArray[i] = status.get(i);

		return returnArray;
	}
}
