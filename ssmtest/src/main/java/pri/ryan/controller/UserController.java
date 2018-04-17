package pri.ryan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pri.ryan.po.User;
import pri.ryan.serivce.UserService;

@Controller
public class UserController {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService service;
    @RequestMapping(value = "/userLoad",method = RequestMethod.POST)
    public String userLoad(User user){
        int flag = service.userLoad(user);
        if (flag == 2){
            return "redirect:queryAllHouseBooking.do";
        }
        return ""+flag;
    }
}
