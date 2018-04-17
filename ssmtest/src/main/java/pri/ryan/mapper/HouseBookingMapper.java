package pri.ryan.mapper;

import org.apache.ibatis.annotations.Param;
import pri.ryan.po.HouseBooking;

import java.util.List;

public interface HouseBookingMapper {
    List<HouseBooking> getAllInfo();
    HouseBooking getInfoById(int id);
    void updateStatus(@Param("id")int id,@Param("status") int status);
    void insertInfo(HouseBooking houseBooking);
    int getId();
}
