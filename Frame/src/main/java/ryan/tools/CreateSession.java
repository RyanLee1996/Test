package ryan.tools;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateSession {
    private static boolean flag = false;
    /**
     * 静态内部类不会随外部类加载而加载, 达到lazy加载的目的
     * 利用了classloder 机制来保证初始化 SingletSessionFactory 时只有一个线程
     * */
    private static class SingletSessionFactory {
        private static final Configuration CONFIG = new Configuration().configure();
        private static final SessionFactory SESSION_FACTORY = CONFIG.buildSessionFactory();
    }
    public CreateSession(){}
    public static final SessionFactory getFactory() {
        return SingletSessionFactory.SESSION_FACTORY;
    }
    public Session getSession() {
        flag = true;
        return getFactory().openSession();
    }
    public void destroy(Session session){
        if (flag){
            session.close();
            getFactory().close();
        }
    }
}
