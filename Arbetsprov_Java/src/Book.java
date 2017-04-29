import java.math.BigDecimal;


public class Book {
	private String m_title;
	private String m_author;
	private BigDecimal m_price;
	
	public Book(String title, String author, BigDecimal price)
	{
		m_title = title;
		m_author = author;
		m_price = price;
	}
	
	public String GetSearchString()
	{
		return m_author + ", " + m_title; 
	}
	
	public BigDecimal GetPrice()
	{
		return m_price;
	}

}