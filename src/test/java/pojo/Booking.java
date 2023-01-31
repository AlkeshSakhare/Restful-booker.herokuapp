package pojo;

public class Booking {
	private String firstname, lastname, additionalneeds;
	private int totalprice;
	private boolean depositpaid;
	private BookingDates bookingDates;

	public Booking(String firstname, String lastname, String additionalneeds, int totalprice, boolean depositpaid,
			BookingDates bookingDates) {
		// TODO Auto-generated constructor stub

		this.firstname = firstname;
		this.lastname = lastname;
		this.totalprice = totalprice;
		this.bookingDates = bookingDates;
		this.additionalneeds = additionalneeds;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAdditionalneeds() {
		return additionalneeds;
	}

	public void setAdditionalneeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
	}

	public int getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	public boolean isDepositpaid() {
		return depositpaid;
	}

	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}

	public BookingDates getBookingDates() {
		return bookingDates;
	}

	public void setBookingDates(BookingDates bookingDates) {
		this.bookingDates = bookingDates;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getFirstname() + " " + getLastname() + " " + getTotalprice() + " " + getAdditionalneeds() + " "
				+ getBookingDates();
	}
}
