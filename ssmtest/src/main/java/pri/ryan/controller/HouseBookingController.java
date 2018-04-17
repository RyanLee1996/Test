package pri.ryan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pri.ryan.po.HouseBooking;
import pri.ryan.serivce.HouseBookingService;

import java.util.List;

@Controller
public class HouseBookingController {
    @Autowired
    @Qualifier("houseBookingServiceImpl")
    private HouseBookingService service;

    @RequestMapping(path = "/queryAllHouseBooking")
    public ModelAndView queryAllHouseBooking(){
        /*数据对象*/
        List<HouseBooking> list = service.getAllInfo();
        /*获取ModelAndView*/
        ModelAndView modelAndView = new ModelAndView();
        /*添加需要返回的数据对象*/
        modelAndView.addObject("houseBookingList",list);
        /*制定仕途*/
        modelAndView.setViewName("/WEB-INF/jsp/simple.jsp");
        return modelAndView;
    }
    @RequestMapping(path = "/queryHouseBooking", method = RequestMethod.GET)
    public ModelAndView queryHouseBooking(int id,int flag){
        /*数据对象*/
        HouseBooking houseBooking = service.getInfoById(id);
        /*获取ModelAndView*/
        ModelAndView modelAndView = new ModelAndView();
        /*添加需要返回的数据对象*/
        modelAndView.addObject("houseBooking",houseBooking);
        /*制定仕途*/
        if (flag==0){
            modelAndView.setViewName("/WEB-INF/jsp/single.jsp");
        }else {
            modelAndView.setViewName("/WEB-INF/jsp/update.jsp");
        }
        return modelAndView;
    }
    @RequestMapping(path = "/addHouseBooking", method = RequestMethod.POST)
    public String addHouseBooking(HouseBooking item){
        System.out.println(item);
        service.insertInfo(item);
        return "index.jsp";
    }
    @RequestMapping(path = "/updateHouseBooking")
    public String updateHouseBooking(int id,int status){
        service.updateStatus(id,status);
        return "redirect:queryHouseBooking.do?id="+id+"&flag=0";
    }
    @RequestMapping("getAddView")
    public String getAddView(){
        System.out.println("getAddView");
        return "/WEB-INF/html/add.html";
    }
}
