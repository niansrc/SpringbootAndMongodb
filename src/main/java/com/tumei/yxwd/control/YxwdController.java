package com.tumei.yxwd.control;
import com.tumei.ServersConfig;
import com.tumei.yxwd.model.account.LoginerBean;
import com.tumei.yxwd.model.account.LoginerBeanRepository;
import com.tumei.yxwd.model.account.ThirdReceiptsBean;
import com.tumei.yxwd.model.account.ThirdReceiptsBeanRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Map;

/**
 * Created by niannian on 2017/1/9.
 */
@Controller
public class YxwdController {
    @Autowired
    public LoginerBeanRepository login;
    @Autowired
    public ServersConfig sec;
    @Autowired
    public ThirdReceiptsBeanRepository thir;
    //注册查询
    @RequestMapping(value = "/yxwd",method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN','YXWD','OWNER','XXKG,YXWD')")
    public String yxwd(@RequestParam String account,ModelMap map){
        map.addAttribute("name",account);
        map.addAttribute("start", DateTime.now());
        map.addAttribute("end",DateTime.now());
        map.addAttribute("num",0);
        return "yxwd/index";
    }
    @RequestMapping(value = "/regsearch",method = RequestMethod.POST)
    public String regsearch(@RequestParam String account, String start, String end, ModelMap map){
        map.addAttribute("name",account);
        map.addAttribute("start",start);
        map.addAttribute("end",end);
        List<LoginerBean> bean=login.findByCreatetime(start,end);
        map.addAttribute("num",bean.size());
        return "yxwd/index";
    }
    //充值查询
    @RequestMapping(value = "/yxwd/charge",method = RequestMethod.GET)
    public String yxwdcharge(@RequestParam String account, ModelMap map){
        map.addAttribute("name",account);
        String[] con = sec.getIp();
        map.addAttribute("demo",con);
        map.addAttribute("start", DateTime.now());
        map.addAttribute("end",DateTime.now());
        map.addAttribute("num",0);
        return "yxwd/charge";
    }
    @RequestMapping(value = "/charsearch",method = RequestMethod.POST)
    public String charsearch(@RequestParam String account, String start, String end, Integer zone, ModelMap map){
        List<ThirdReceiptsBean> bean = thir.findByCon(start,end,zone);
//        System.out.println("<><><>"+bean.size());
        map.addAttribute("re",bean);
        map.addAttribute("name",account);
        String[] con = sec.getIp();
        map.addAttribute("demo",con);
        map.addAttribute("start",start);
        map.addAttribute("end",end);
        map.addAttribute("num",bean.size());
        return "/yxwd/charge";
    }
    //活跃度查询
    @RequestMapping(value = "/yxwd/active",method = RequestMethod.GET)
    public String yxwdactive(@RequestParam String account, ModelMap map){
        map.addAttribute("name",account);
        return "yxwd/active";
    }
    //用户查询
    @RequestMapping(value = "/yxwd/user",method = RequestMethod.GET)
    public String yxwduser(@RequestParam String account, ModelMap map){
        map.addAttribute("name",account);
        return "yxwd/user";
    }
    //分区查询
    @RequestMapping(value = "/yxwd/area",method = RequestMethod.GET)
    public String yxwdarea(@RequestParam String account, ModelMap map){
        map.addAttribute("name",account);
        return "yxwd/area";
    }
    //邮件发送
    @RequestMapping(value = "/yxwd/email",method = RequestMethod.GET)
    public String yxwdemail(@RequestParam String account, ModelMap map){
        map.addAttribute("name",account);
        return "yxwd/email";
    }
}
