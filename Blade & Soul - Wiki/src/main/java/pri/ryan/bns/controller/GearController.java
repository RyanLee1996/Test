package pri.ryan.bns.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gear")
public class GearController {
    @RequestMapping
    public String addGear(){
        return "";
    }
}
