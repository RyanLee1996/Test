package pri.ryan.mapper;

import pri.ryan.po.Student;

import java.util.List;

public interface StudentMapper {
    public List<Student> queryStudentById(int id)throws Exception;
    public List<Student> queryStudentByName(String name)throws Exception;
}
