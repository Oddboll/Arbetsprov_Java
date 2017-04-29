
interface BookList {

	public void ReadFromFile();
	public Book[] List(String searchString);
	public boolean Add(Book book, int quantity);
	public int[] buy(Book... b);
}
