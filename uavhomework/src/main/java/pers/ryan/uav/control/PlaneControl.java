package pers.ryan.uav.control;

import pers.ryan.uav.service.PlaneService;
import pers.ryan.uav.tools.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 无人机控制类
 * @author Ryan
 */
public class PlaneControl {
    public static void main(String[] args) {
        /*选择测试方式*/
        System.out.println("如果自行输入数据请输入input , 导入文本请输入import:");
        Scanner input = new Scanner(System.in);
        String str  = null;
        boolean flag = true;
        while (input.hasNextLine()){
            str = input.nextLine();
            if(str.equals("input") || str.equals("import")){
                flag = false;
                break;
            }else {
                System.out.println("如果自行输入数据请输入input , 导入文本请输入import:");
            }
        }
        /*开启服务*/
        PlaneService planeService = new PlaneService();
        String planeId = null;
        /*input导入*/
        if(!flag && str.equals("input")){
            System.out.println("请输入无人机信息,exit退出输入：");
            Scanner input1 = new Scanner(System.in);
            String str1 = null;
            while (input1.hasNextLine()){
                str1 = input1.nextLine();
                if(str1.equals("exit")){
                    break;
                }
                planeService.inputInfo(str1,false);
                planeId = str1.split(" ")[0];
            }
            /*获取消息*/
            planeService.getInfo(planeId);
        }
        /*import导入*/
        else if(!flag && str.equals("import")){
            System.out.println("请输入无人机id：");
            Scanner input2 = new Scanner(System.in);
            boolean isExit = false;
            while (!isExit && input2.hasNextLine()){
                try {
                    /*获取路径*/
                    String realPath = FileUtil.getResourcesPath()+input2.next()+".txt";
                    Scanner sc=new Scanner(new File(realPath));
                    while (sc.hasNextLine()){
                        String str2 = sc.nextLine();
                        planeService.inputInfo(str2,true);
                        /*验证无人机id是否符合规范*/
                        if(str2.split(" ")[0].matches("^(?![0-9]+$)(?![a-zA-Z]+$)(?![^0-9a-zA-Z])[0-9a-zA-Z]*$"))
                        {
                            planeId = str2.split(" ")[0];
                        }
                        isExit = true;
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("请输入无人机id：");
                }
            }
            /*获取消息*/
            planeService.getInfo(planeId);
        }
    }
}
