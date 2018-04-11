package ryan.test;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ryan.hibernate.OrderEntity;
import ryan.hibernate.SkuEntity;


@Repository
@Transactional
public class OrderSkuTest {
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }
    @Transactional("transactionManager")
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    private HibernateTemplate hibernateTemplate;
    /*多对多测试*/
    @Test
    @Transactional(value = "transactionManager",readOnly=true)
    public void test() {
        ApplicationContext context =  new ClassPathXmlApplicationContext("applicationContext.xml");
        OrderSkuTest sku = (OrderSkuTest) context.getBean("sku");
        OrderEntity order1 = new OrderEntity();
        OrderEntity order2 = new OrderEntity();
        OrderEntity order3 = new OrderEntity();
        order1.setTime("afafa");
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
        sku.getHibernateTemplate().setCheckWriteOperations(false);
        sku.getHibernateTemplate().save(order1);
        sku.getHibernateTemplate().save(order2);
        sku.getHibernateTemplate().save(order3);
    }
}
