package ryan.hibernate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SkuEntity implements Serializable {
    private int id;
    private String name;
    private int price;
    private Set<OrderEntity> SkuByOrderID = new HashSet<OrderEntity>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Set<OrderEntity> getSkuByOrderID() {
        return SkuByOrderID;
    }

    public void setSkuByOrderID(Set<OrderEntity> skuByOrderID) {
        SkuByOrderID = skuByOrderID;
    }
}
