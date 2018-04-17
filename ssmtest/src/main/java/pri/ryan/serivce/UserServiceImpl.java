package pri.ryan.serivce;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pri.ryan.mapper.UserMapper;
import pri.ryan.po.User;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService{

    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    /*flag
      - 0 没有该用户
      - 1 密码错误
      - 2 登录成功
      */
    @Override
    public int userLoad(User user) {
        int flag = 0;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User realUser = null;
        try{
            realUser = userMapper.getUserPassword(user.getName());
            if (realUser != null){
                if(realUser.getPassword().equals(user.getPassword())){
                    flag = 2;
                }else {
                    flag = 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sqlSession.close();
        return flag;
    }
}
