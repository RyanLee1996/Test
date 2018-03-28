package ryan.test;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import ryan.tools.CreateSession;

import java.util.List;

public class StudentCourseTest {
    @Test
    public  void getMaxPopularCourseName() {
        CreateSession createSession = new CreateSession();
        Session session = createSession.getSession();
        String hql = "select c.name from StudentEntity s right join  s.courseid c order by c.name desc";
        Query query = session.createQuery(hql);
        query.setFirstResult(0).setMaxResults(1);
        query.setCacheRegion("javaClassName");
        query.setCacheable(true);
        List<Object> list = query.list();
        for (Object o:list) {
            System.out.println(o);
        }
        createSession.destroy(session);
    }
    @Test
    public void getTest(){
        CreateSession createSession = new CreateSession();
        Session session = null;
        for (int i = 0; i < 5; i++) {
            session= createSession.getSession();
            String hql = "select s.name from StudentEntity s where s.id=1";
            Query query = session.createQuery(hql);
            query.setCacheRegion("javaClassName");
            query.setCacheable(true);
            List<Object> list = query.list();
            for (Object o:list) {
                System.out.println(o);
            }
            if (i<4){
               session.close();
            }
        }
        createSession.destroy(session);
    }
    public static void main(String[] args) {
        CreateSession createSession = new CreateSession();
        Session session = createSession.getSession();
        String hql = "select s.name,s.profession,c.name from StudentEntity s  join  s.courseid c";
        Query query = session.createQuery(hql);
        int page = 2;
        int count = 5;
        query.setFirstResult((page-1)*count).setMaxResults(count);
        List<Object[]> list = query.list();
        for (Object[] o:list) {
            System.out.println(o[0]+" "+o[1]+" "+o[2]);
        }
        createSession.destroy(session);
    }
}
