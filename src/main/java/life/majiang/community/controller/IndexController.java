package life.majiang.community.controller;

import jdk.nashorn.internal.parser.Token;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping(value = "/")
    public  String Index (HttpServletRequest request){
        //获取cookie得到数组
        Cookie[] cookies = request.getCookies();
        //遍历数组
        for (Cookie cookie : cookies) {
            //获取cookie的name
            if (cookie.getName().equals("token")){//和token一直
                //拿到cookie的值
                String token = cookie.getValue();
                //通过cookie的值token查询登录者
                User user=userMapper.findByToken(token);
                //如果数据库中存在user
                if (user!=null){
                    //将user设置到session中
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        return "index";
    }
}
