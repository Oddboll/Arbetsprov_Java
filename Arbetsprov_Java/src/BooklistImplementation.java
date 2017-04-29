import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.math.BigDecimal;
public class BooklistImplementation implements BookList{

//public:
public static final int OK = 0;
public static final int NOT_IN_STOCK = 1;
public static final int DOES_NOT_EXIST = 2;

//private:
private Map<Book, Integer> bookList = new LinkedHashMap<Book, Integer>();
	
	public BooklistImplementation ()
	{

        Book b = new Book("едц", "hej", new BigDecimal(2));
        Add(b, 2);
		ReadFromFile();
	}
	
	public void ReadFromFile()
	{
		BufferedReader br;
			try {
				br = new BufferedReader(new FileReader("bookstoredata.txt"));
				String line;
				try {
					while(( line = br.readLine()) != null)
					{
						String s[] = line.split(";");
						Book newBook;
						newBook = new Book(s[0], s[1], new BigDecimal(s[2].replaceAll(",", "")));
						bookList.put(newBook, Integer.parseInt(s[3]));
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	public Book[] List(String searchString)
	{
		ArrayList<Book> foundBooks = new ArrayList<Book>();
		String[]search = searchString.split(" ");
		for(Book book : bookList.keySet())
		{
			String[] bookString = book.GetSearchString().replace(",", "").split(" ");
			for(String b : bookString)
			{
				for(String s : search)
				{
					if(b.equalsIgnoreCase(s))
						foundBooks.add(book);
				}
			}
		}
		
		Book[] returnArray = foundBooks.toArray(new Book[foundBooks.size()]);
		return returnArray;
	}
	
	public boolean Add(Book book, int quantity)
	{
		if(bookList.containsKey(book))
			return false;
		
		bookList.put(book, quantity);
		return true;
	}
	
	public int[] buy(Book... b)
	{
		ArrayList<Integer>status = new ArrayList<Integer>();
		for(Book book : b)
		{
			if(bookList.containsKey(book))
			{
				Integer value = bookList.get(book);
				if(value > 0)
					status.add(OK);
				else if(value == 0)
					status.add(NOT_IN_STOCK);
			}
			else 
				status.add(DOES_NOT_EXIST);
		}
		
		//Maybe change later
		int[] returnArray = new int[status.size()];
		
		for(int i = 0; i<status.size(); i++)
			returnArray[i] = status.get(i);
		
		return returnArray;
	}
}
