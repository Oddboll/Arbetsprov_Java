import java.math.BigDecimal;

public class Book {
	private String m_title;
	private String m_author;
	private BigDecimal m_price;

	public Book(String title, String author, BigDecimal price) {
		m_title = title;
		m_author = author;
		m_price = price;
	}

	public Book() {

	}

	public String GetSearchString() {
		return m_author + ", " + m_title;
	}

	public String GetFullString() {
		return m_author + ", " + m_title + ": " + m_price + "kr.";
	}

	public BigDecimal GetPrice() {
		return m_price;
	}

}
