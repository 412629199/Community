package life.majiang.community.controller;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.service.QestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 首页
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
   @Autowired
   private QestionService qestionService;
    @GetMapping(value = "/")
    public  String Index (HttpServletRequest request, Model model){
        //获取cookie得到数组
        Cookie[] cookies = request.getCookies();
        if (cookies!=null&&cookies.length!=0)
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
        //查询questionDTOList 将其设置在model中
            List<QuestionDTO> questionDTOList=qestionService.questionDTOList();
            model.addAttribute("question",questionDTOList);
        return "index";
    }
}
