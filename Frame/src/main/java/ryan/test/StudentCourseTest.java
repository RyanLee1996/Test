package ryan.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ryan.hibernate.CourseEntity;
import ryan.hibernate.StudentEntity;
import ryan.tools.CreateSession;

public class StudentCourseTest {
    public static void main(String[] args) {
        CreateSession createSession = new CreateSession();
        Session session = createSession.getSession();
        Transaction transaction = session.beginTransaction();
        CourseEntity course1 = new CourseEntity();
        course1.setName("java");
        CourseEntity course2 = new CourseEntity();
        course2.setName("python");

        for (int i = 0; i < 10; i++) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setAge(20+i);
            studentEntity.setName("测试"+i);
            if (i%2==0){
                studentEntity.setProfession("大数据");
                course1.getCourseByStudent().add(studentEntity);

            }else {
                studentEntity.setProfession("区块链");
                course2.getCourseByStudent().add(studentEntity);
            }
        }
        session.save(course1);
        session.save(course2);
        transaction.commit();
        createSession.destroy(session);
    }
}
