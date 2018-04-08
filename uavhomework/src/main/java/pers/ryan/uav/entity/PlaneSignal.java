package pers.ryan.uav.entity;

/**
 * 无人机信息实体类
 * @author Ryan
 */
public class PlaneSignal {
    /*坐标*/
    private int id;
    private int x;
    private int y;
    private int z;
    /*偏移量*/
    private Integer offsetX;
    private Integer offsetY;
    private Integer offsetZ;
    /*状态*/
    private boolean status;
    public PlaneSignal() {
    }

    public PlaneSignal(int id, int x, int y, int z, int offsetX, int offsetY, int offsetZ, boolean status) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.status = status;
    }

    public PlaneSignal(int id, int x, int y, int z, boolean status) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.status = status;
    }
    public PlaneSignal(int id, boolean status){
        this.id = id;
        this.status = status;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getOffsetZ() {
        return offsetZ;
    }

    public void setOffsetZ(int offsetZ) {
        this.offsetZ = offsetZ;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PlaneSignal{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", offsetX=" + offsetX +
                ", offsetY=" + offsetY +
                ", offsetZ=" + offsetZ +
                ", status=" + status +
                '}';
    }
}
