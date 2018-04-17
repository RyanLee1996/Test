package pri.ryan.serivce;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pri.ryan.mapper.HouseBookingMapper;
import pri.ryan.po.HouseBooking;

import java.util.List;
@Service("houseBookingServiceImpl")
public class HouseBookingServiceImpl implements HouseBookingService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public List<HouseBooking> getAllInfo() {
        SqlSession session = sqlSessionFactory.openSession();
        HouseBookingMapper mapper = session.getMapper(HouseBookingMapper.class);
        List<HouseBooking> list = mapper.getAllInfo();
        session.close();
        return list;
    }

    @Override
    public HouseBooking getInfoById(int id) {
        SqlSession session = sqlSessionFactory.openSession();
        HouseBookingMapper mapper = session.getMapper(HouseBookingMapper.class);
        HouseBooking info = mapper.getInfoById(id);
        session.close();
        return info;
    }

    @Override
    public void updateStatus(int id, int status) {
        SqlSession session = sqlSessionFactory.openSession();
        HouseBookingMapper mapper = session.getMapper(HouseBookingMapper.class);
        mapper.updateStatus(id,status);
        session.close();
    }

    @Override
    public void insertInfo(HouseBooking houseBooking) {
        SqlSession session = sqlSessionFactory.openSession();
        HouseBookingMapper mapper = session.getMapper(HouseBookingMapper.class);

        houseBooking.setId(mapper.getId()+1);
        mapper.insertInfo(houseBooking);
        session.close();
    }
}
