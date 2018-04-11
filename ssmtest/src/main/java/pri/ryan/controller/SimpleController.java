package pri.ryan.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SimpleController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
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
