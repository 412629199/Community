package life.majiang.community.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * 个人中心
 */
@Controller
public class ProfileController {
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable (name = "action") String  action, Model model){
        if("questions".equals(action)){
            model.addAttribute("action","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)){
            model.addAttribute("action","replies");
            model.addAttribute("sectionName","最新回复");
        }
        return "profile";
    }

}
