package pers.ryan.uav.service;

import pers.ryan.uav.entity.Plane;
import pers.ryan.uav.entity.PlaneSignal;
import pers.ryan.uav.tools.FileUtil;

import java.util.HashMap;
import java.util.Scanner;

/**
 * PlaneService类
 * 用于处理关于Plane类的服务
 * @author Ryan
 */
public class PlaneService {

    private HashMap<String,Plane> planes = new HashMap<String, Plane>();

    /**
     *  用于将输入的信息注入到Plane类
     * @param str 信息内容
     * @param flag 标记,console是否输出信息
     */
    public void inputInfo(String str,boolean flag){
        String[] strings = str.split(" ");
        if(flag){
            System.out.println(str);
        }
        boolean planeExit  = false;
        boolean exceptionFlag  = false;
        String planeId = null;
        int count = 0;
        int x = 0;
        int y = 0;
        int z = 0;
        int offsetX = 0;
        int offsetY = 0;
        int offsetZ = 0;

        try{
            planeId = strings[0];
            planeExit = planeId.matches("^(?![0-9]+$)(?![a-zA-Z]+$)(?![^0-9a-zA-Z])[0-9a-zA-Z]*$");
            if(!planeExit){
                System.out.println("——无人机编号不符合规范");
            }
            /*count计数判定输入是否符合数量规范
             * 转化输入*/
            x = Integer.parseInt(strings[1]);
            count++;
            y = Integer.parseInt(strings[2]);
            count++;
            z = Integer.parseInt(strings[3]);
            count++;
            offsetX = Integer.parseInt(strings[4]);
            count++;
            offsetY = Integer.parseInt(strings[5]);
            count++;
            offsetZ = Integer.parseInt(strings[6]);
            count++;
        }catch (ArrayIndexOutOfBoundsException e){
        }catch (NumberFormatException e){
            /*输入类型错误*/
            exceptionFlag = true;
            System.out.println("输入类型错误");
        }
        if (planeExit) {
            if (!flag){
                FileUtil.inputText(planeId,str);
            }
            try{
                String indexOut  = strings[7];
                /*输入越界*/
                exceptionFlag = true;
                System.out.println("输入越界");
            }catch (ArrayIndexOutOfBoundsException e){
            }
            if (!exceptionFlag){
                if(count == 3) {
                    setPlaneSignals(planeId,x,y,z);
                } else if (count == 6) {
                    setPlaneSignals(planeId,x,y,z,offsetX,offsetY,offsetZ);
                } else {
                    /*参数数量不对*/
                    inputFault(planeId);
                }
            }else {
                inputFault(planeId);
            }
        }
    }

    /**
     * 获取无人机的信息
     * @param planeId 无人机id
     */
    public void getInfo(String planeId){
        System.out.println("请输入指定消息ID,exit退出服务：");
        Scanner input = new Scanner(System.in);
        String str = null;
        while (input.hasNext()){
            str = input.next();
            if(str.equals("exit")){
                break;
            }
            getInfo(planeId,str);
        }
    }

    /**
     * 设置无人机某次信息
     * @param planeId 无人机id
     * @param x x轴坐标
     * @param y y轴坐标
     * @param z z轴坐标
     */
    private void setPlaneSignals(String planeId, int x, int y, int z){
        Plane plane = getPlane(planeId);
        int count = plane.getCount();
        if (plane.isStatus() && count !=0){
            setFault(plane);
        }else {
            setPlaneNowInfo(plane,x,y,z);
        }
        /*设置单次信息*/
        PlaneSignal signal = new PlaneSignal(count,x,y,z,plane.isStatus());
        /*添加信息至plane*/
        plane.getPlaneSignals().add(signal);
        plane.setCount(count+1);
    }

    /**
     * 设置无人机某次信息
     * @param planeId 无人机id
     * @param x x轴坐标
     * @param y y轴坐标
     * @param z z轴坐标
     * @param offsetX x轴偏移量
     * @param offsetY y轴偏移量
     * @param offsetZ z轴偏移量
     */
    private void setPlaneSignals(String planeId, int x, int y, int z, int offsetX, int offsetY, int offsetZ) {
        Plane plane = getPlane(planeId);
        int count = plane.getCount();
        if ( plane.isStatus() && plane.getCount() != 0 && (x != plane.getX() || y != plane.getY() || z != plane.getZ())){
            setFault(plane);
        }else {
            setPlaneNowInfo(plane,x+offsetX,y+offsetY,z+offsetZ);
        }
        /*设置信息*/
        PlaneSignal signal = new PlaneSignal(count,x,y,z,offsetX,offsetY,offsetZ,plane.isStatus());
        /*添加信息至plane*/
        plane.getPlaneSignals().add(signal);
        plane.setCount(count+1);
    }

    /**
     * 设置无人机当前状态为故障
     * @param plane 无人机类对象
     */
    private void setFault(Plane plane){
        plane.setStatus(false);
        plane.setX("NA");
        plane.setY("NA");
        plane.setZ("NA");
    }

    /**
     * 设置无人机某次信息为故障
     * @param planeId 无人机id
     */
    private void inputFault(String planeId){
        Plane plane = getPlane(planeId);
        int count = plane.getCount();
        setFault(plane);
        PlaneSignal signal = new PlaneSignal(count,plane.isStatus());
        plane.getPlaneSignals().add(signal);
        plane.setCount(count+1);
    }

    /**
     * 设置无人机当前状态
     * @param plane 无人机类对象
     * @param x x轴坐标
     * @param y y轴坐标
     * @param z z轴坐标
     */
    private void setPlaneNowInfo(Plane plane,int x, int y, int z){
        if(plane.isStatus()){
            plane.setX(String.valueOf(x));
            plane.setY(String.valueOf(y));
            plane.setZ(String.valueOf(z));
        }
    }

    /**
     *
     * @param planeId 无人机id
     * @return 当前id的无人机对象
     */
    private Plane getPlane(String planeId){
        if(planes.containsKey(planeId)){
            return planes.get(planeId);
        }else {
            Plane plane = new Plane(planeId);
            planes.put(planeId,plane);
            return plane;
        }
    }

    /**
     * 获取无人机某次信息
     * @param planeId 无人机id
     * @param infoId 信息id
     */
    private void getInfo(String planeId, String infoId){
        try{
            int id = Integer.parseInt(infoId);
            Plane plane = getPlane(planeId);
            PlaneSignal signal = plane.getPlaneSignals().get(id);
            if(!signal.isStatus()){
                System.out.println("Error: " + infoId);
            }else {
                System.out.print(planeId + " " + infoId + " ");
                if (id==0){
                    System.out.println(signal.getX() + " " + signal.getY()+ " " + signal.getZ());
                }else {
                    System.out.println((signal.getX()+signal.getOffsetX()) + " " + (signal.getY()+signal.getOffsetY()) + " " + (signal.getZ()+signal.getOffsetZ()));
                }
            }
        }catch (NumberFormatException e){
            System.out.println("请输入整数");
        }catch (IndexOutOfBoundsException e){
            System.out.println("Cannot find " + infoId);
        }
    }
}
