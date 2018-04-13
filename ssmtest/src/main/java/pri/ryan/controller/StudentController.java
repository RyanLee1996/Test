package pri.ryan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pri.ryan.po.Student;
import pri.ryan.serivce.StudentService;

import java.util.List;

@Controller
@RequestMapping("/st")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @RequestMapping(path = "/queryInfoByName", method = RequestMethod.GET)
    public ModelAndView queryStudentList(String name){
        /*数据对象*/
        List<Student> list = studentService.queryStudentByName(name);
        /*获取ModelAndView*/
        ModelAndView modelAndView = new ModelAndView();
        /*添加需要返回的数据对象*/
        modelAndView.addObject("studentList",list);
        /*制定仕途*/
        modelAndView.setViewName("/WEB-INF/jsp/simple.jsp");
        return modelAndView;
    }
    @RequestMapping(value = "/queryInfoById", method = RequestMethod.GET)
    public ModelAndView queryStudentSingle(int id){
        List<Student> list = studentService.queryUserById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("studentList",list);
        modelAndView.setViewName("/WEB-INF/jsp/simple.jsp");
        return modelAndView;
    }
}
