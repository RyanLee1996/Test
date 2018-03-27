package ryan.hibernate;

import java.util.HashSet;
import java.util.Set;

public class OrderEntity {
    private int id;
    private String time;
    private String status;
    private Set<SkuEntity> orderBySkuID = new HashSet<SkuEntity>();

    public OrderEntity(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<SkuEntity> getOrderBySkuID() {
        return orderBySkuID;
    }

    public void setOrderBySkuID(Set<SkuEntity> orderBySkuID) {
        this.orderBySkuID = orderBySkuID;
    }


}
