package ryan.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import ryan.hibernate.OrderEntity;
import ryan.hibernate.SkuEntity;
import ryan.tools.CreateSession;

public class OrderSkuTest {
    /*多对多测试*/
    @Test
    public void test() {
        CreateSession createSession = new CreateSession();
        Session session = createSession.getSession();
        Transaction transaction = session.beginTransaction();
        OrderEntity order1 = new OrderEntity();
        OrderEntity order2 = new OrderEntity();
        OrderEntity order3 = new OrderEntity();
        order1.setTime("第一个");
        order2.setTime("第二个");
        order3.setTime("第三个");
        SkuEntity sku1 = new SkuEntity();
        SkuEntity sku2 = new SkuEntity();
        SkuEntity sku3 = new SkuEntity();
        sku1.setName("外星人pc");
        sku1.setPrice(9999);
        sku2.setName("战神pc");
        sku2.setPrice(6666);
        sku3.setName("神舟pc");
        sku3.setPrice(5555);

        order1.getOrderBySkuID().add(sku1);
        order1.getOrderBySkuID().add(sku2);
        order2.getOrderBySkuID().add(sku2);
        order2.getOrderBySkuID().add(sku3);
        order3.getOrderBySkuID().add(sku1);
        order3.getOrderBySkuID().add(sku3);
        session.save(order1);
        session.save(order2);
        session.save(order3);
        transaction.commit();
        createSession.destroy(session);
    }
}
