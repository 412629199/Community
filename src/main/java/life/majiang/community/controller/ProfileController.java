package life.majiang.community.controller;
import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 个人中心
 */
@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/profile/{action}")
    public String profile(
                          @PathVariable (name = "action") String  action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(value = "page" ,defaultValue = "1") Integer page,
                          @RequestParam(value = "size" ,defaultValue = "5") Integer size
                        ){
       User user=(User) request.getSession().getAttribute("user");

        if(user==null){
            return  "redirect:/";
        }


        if("questions".equals(action)){
            model.addAttribute("action","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)){
            model.addAttribute("action","replies");
            model.addAttribute("sectionName","最新回复");
        }

        PaginationDTO pagination=questionService.listByUserId(user.getId(),page,size);
        model.addAttribute("pagination",pagination);
        return "profile";
    }

}
