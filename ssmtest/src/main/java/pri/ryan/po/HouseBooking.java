package pri.ryan.po;

public class HouseBooking {
    private int id;
    private String area;
    private String cartype;
    private String movedate;
    private String contact;
    private String phone;
    private int status;

    @Override
    public String toString() {
        return "HouseBookingMapper{" +
                "id=" + id +
                ", area='" + area + '\'' +
                ", cartype='" + cartype + '\'' +
                ", movedate='" + movedate + '\'' +
                ", contact='" + contact + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype;
    }

    public String getMovedate() {
        return movedate;
    }

    public void setMovedate(String movedate) {
        this.movedate = movedate;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
