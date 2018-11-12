package pre.ryan.fgo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @auther Ryan
 * @date 2018/11/12 15:55
 * @description
 * @updateUser
 * @updateDate
 */
@RestController
@RequestMapping("/swagger")
@Api(value="/swagger", tags="测试接口模块")
public class SwaggerController {

    @ApiOperation(value="展示首页信息1", notes = "展示首页信息2")
    @GetMapping("/show")
    public Object showInfo(){
        return "hello world";
    }

    @ApiOperation("添加用户信息1")
    @ApiImplicitParam(name="user", value="N个用户信息", required = true, dataType = "string")
    @PostMapping("/addUser")
    public Object addUser(@RequestBody Object user){
        return "success";
    }
}
