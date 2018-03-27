package ryan.test;


import org.hibernate.Session;
import org.hibernate.Transaction;
import ryan.hibernate.DepartmentEntity;
import ryan.tools.CreateSession;

public class EmpolyByDepartmentTest {
    private static Transaction transaction;
    private static Session session;
    public static void main(String[] args) {
        CreateSession createSession = new CreateSession();
        session = createSession.getSession();
        transaction = session.beginTransaction();
        DepartmentEntity department = session.load(DepartmentEntity.class,4);
        session.delete(department);
        transaction.commit();
        createSession.destroy(session);
    }
}
