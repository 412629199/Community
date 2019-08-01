package life.majiang.community.controller;

import life.majiang.community.dto.Question;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

/**
 * 问题发起
 */
@Controller

public class PublishController {
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
        User user=(User) request.getSession().getAttribute("user");

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
