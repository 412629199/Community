package life.majiang.community.intercepter;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取cookie得到数组
        Cookie[] cookies = request.getCookies();
        if (cookies!=null&&cookies.length!=0){
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
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
