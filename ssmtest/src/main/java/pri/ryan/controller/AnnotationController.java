package pri.ryan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AnnotationController {
    @RequestMapping("/queryData")
    public ModelAndView queryData() throws Exception{
        /*数据对象*/
        List list = new ArrayList();
        /*获取ModelAndView*/
        ModelAndView modelAndView = new ModelAndView();
        /*添加需要返回的数据对象*/
        modelAndView.addObject("Object",list);
        /*制定仕途*/
        modelAndView.setViewName("/WEB-INF/jsp/simple.jsp");
        return modelAndView;
    }
}
