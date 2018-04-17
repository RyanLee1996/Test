package pri.ryan.serivce;


import pri.ryan.po.HouseBooking;

import java.util.List;

public interface HouseBookingService {
    List<HouseBooking> getAllInfo();
    HouseBooking getInfoById(int id);
    void updateStatus(int id,int status);
    void insertInfo(HouseBooking houseBooking);
}
