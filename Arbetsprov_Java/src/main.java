import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class main {

public static final int OK = 0;
public static final int NOT_IN_STOCK = 1;
public static final int DOES_NOT_EXIST = 2;

	public static void main(String[] args){
		BookList bookList = new BooklistImplementation();
        bookList.ReadFromFile();
		
        Book[] books = bookList.List("*");
        System.out.println("found "+books.length+" books.");
        for(Book b : books)
        	System.out.println(b.GetFullString());
        int[] status = bookList.buy(books);
        for(int i = 0; i < status.length; i++)
        {
        	if(status[i] == OK)
        	{
        		//add to cart
        		System.out.println("'"+books[i].GetSearchString() +"' added to cart");
        	}
        	else if(status[i] == NOT_IN_STOCK)
        	{
        		System.out.println("'"+books[i].GetSearchString() +"' is not in stock");
        	}
        	else if(status[i] == DOES_NOT_EXIST)
        	{
        		System.out.println("'"+books[i].GetSearchString() +"' does not exist");
        	}
        }
    }

}
