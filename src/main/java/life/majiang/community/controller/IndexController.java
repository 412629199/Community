package life.majiang.community.controller;
import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 首页
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
   @Autowired
   private QuestionService questionService;
    @GetMapping(value = "/")
    public  String Index (HttpServletRequest request, Model model,
                          @RequestParam(value = "page" ,defaultValue = "1") Integer page,
                          @RequestParam(value = "size" ,defaultValue = "5") Integer size
                          ){
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

        //查询paginationDTO 将其设置在model中
            PaginationDTO pagination= questionService.questionDTOList(page,size);
            model.addAttribute("pagination",pagination);
        return "index";
    }
}
