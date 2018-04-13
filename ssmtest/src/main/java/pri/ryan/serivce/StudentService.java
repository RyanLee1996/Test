package pri.ryan.serivce;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pri.ryan.mapper.StudentMapper;
import pri.ryan.po.Student;


import java.util.List;
@Service("studentService")
public class StudentService {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public List<Student> queryUserById(int id){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        //调用userMapper的方法
        List<Student> student = null;
        try {
            student = studentMapper.queryStudentById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public List<Student> queryStudentByName(String name){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> list = null;
        try {
            list = studentMapper.queryStudentByName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
