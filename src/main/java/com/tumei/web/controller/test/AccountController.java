//package com.tumei.web.controller.test;
//
//import com.tumei.web.model.AccountBean;
//import com.tumei.web.model.AccountBeanRepository;
//import com.tumei.web.params.AccountReturn;
//import com.tumei.web.params.RegisterAccount;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.web.bind.annotation.*;
//
///**
// * Created by leon on 2016/11/4.
// */
//@RestController
//public class AccountController {
//    @Autowired
//    public AccountBeanRepository repository;
//
//    /***
//     * 根据玩家Post的数据注册帐号
//     * @param account
//     * @return
//     */
//    @ApiOperation(value = "注册用户", notes = "")
//    @ApiImplicitParam(name = "account", value = "玩家注册帐号信息", required = true, dataType = "RegisterAccount")
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public AccountReturn Register(@RequestBody RegisterAccount account) {
//        // 对注册帐号函数进行保护，时间和IP间隔
//        AccountBean beans = repository.findByAccount(account.getName());
//        if (beans != null)
//        {
//            return new AccountReturn(0, String.format("Hello World: %s.", beans.id));
//        }
//        else
//        {
//            return new AccountReturn(1, "没有找到这个玩家");
//        }
//    }
//
//    @ApiOperation(value = "验证用户返回介入Token", notes = "")
//    @ApiImplicitParams({
//        @ApiImplicitParam(name = "account", value = "帐号", required = true, dataType = "String",
//            paramType = "path"),
//        @ApiImplicitParam(name = "passwd", value = "密码", required = true, dataType = "String",
//            paramType = "path")
//    })
//    @RequestMapping(value = "/logany/account/passwd/idfa", method = RequestMethod.GET)
//    public AccountReturn LogAny(@PathVariable String account, @PathVariable String passwd) {
//        // 对注册帐号函数进行保护，时间和IP间隔
//        AccountBean beans = repository.findByAccount(account);
//        if (beans != null)
//        {
//            return new AccountReturn(0, String.format("Hello World: %s.", beans.id));
//        }
//        else
//        {
//            return new AccountReturn(1, "没有找到这个玩家");
//        }
//    }
//}
