package in.hotelreservationapp.model;

public class Customer{
	private Integer reservation_id;
	private String name;
	private String mobile;
	private String room_num;
	
	public Integer getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(Integer reservation_id) {
		this.reservation_id = reservation_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRoom_num() {
		return room_num;
	}
	public void setRoom_num(String room_num) {
		this.room_num = room_num;
	}
	@Override
	public String toString() {
		return "Student [reservation_id=" + reservation_id + ", name=" + name + ", mobile=" + mobile + ", room_num="
				+ room_num + "]";
	}
}
