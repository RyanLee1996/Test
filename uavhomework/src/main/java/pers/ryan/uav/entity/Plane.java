package pers.ryan.uav.entity;

import java.util.ArrayList;

/**
 * Plane类用于记录
 * 无人机当前状态
 * @author Ryan
 */
public class Plane {
    /*无人机id*/
    private String id;
    /*状态是否正常*/
    private boolean status = true;
    /*坐标*/
    private String x;
    private String y;
    private String z;
    /*当前信息条数*/
    private int count = 0;
    /*历史信息*/
    private ArrayList<PlaneSignal> planeSignals = new ArrayList<PlaneSignal>();

    public Plane() {
    }

    public Plane(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public int getX() {
        return Integer.parseInt(x);
    }

    public void setX(String x) {
        this.x = x;
    }

    public int getY() {
        return Integer.parseInt(y);
    }

    public void setY(String y) {
        this.y = y;
    }

    public int getZ() {
        return Integer.parseInt(z);
    }

    public void setZ(String z) {
        this.z = z;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<PlaneSignal> getPlaneSignals() {
        return planeSignals;
    }

    public void setPlaneSignals(ArrayList<PlaneSignal> planeSignals) {
        this.planeSignals = planeSignals;
    }
}
