package life.majiang.community.controller;

import life.majiang.community.dto.Question;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 问题发起
 */
@Controller

public class PublishController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping ("/publish")
    public String doPublish(
            @RequestParam(name = "title")  String title,
            @RequestParam(name = "discription")  String discription,
            @RequestParam(name = "tag")  String tag,
            HttpServletRequest request,
            Model model
    ){
        model.addAttribute("title",title);
        model.addAttribute("discription",discription);
        model.addAttribute("tag",tag);

        if (StringUtils.isEmpty(title)){
            model.addAttribute("error","标题不能为空！");
            return "publish";
        }

        if (StringUtils.isEmpty(discription)){
            model.addAttribute("error","问题补充不能为空！");
            return "publish";
        }

        if (StringUtils.isEmpty(tag)){
            model.addAttribute("error","标签不能为空！");
            return "publish";
        }

        User user=null;
        //获取cookie得到数组
        Cookie[] cookies = request.getCookies();
        //遍历数组
        for (Cookie cookie : cookies) {
            //获取cookie的name
            if (cookie.getName().equals("token")){//和token一直
                //拿到cookie的值
                String token = cookie.getValue();
                //通过cookie的值token查询登录者
                user=userMapper.findByToken(token);
                //如果数据库中存在user
                if (user!=null){
                    //将user设置到session中
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        //创建question对象
        Question question=new Question();
        question.setTitle(title);
        question.setDiscription(discription);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreated(String.valueOf(System.currentTimeMillis()));
        question.setGmtCreated(question.getGmtCreated());
         if(user==null){
            //若用户信息为空
            model.addAttribute("error","请登录以后执行该操作！");
             return "publish";
        }
        //将问题消息插入数据库
        questionMapper.createQuestion(question);
        return "redirect:/";
    }
}
