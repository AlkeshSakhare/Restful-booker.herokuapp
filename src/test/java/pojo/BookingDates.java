package pojo;

public class BookingDates {

	private String checkin, checkout;

	public BookingDates(String checkin, String checkout) {
		// TODO Auto-generated constructor stub
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

}
