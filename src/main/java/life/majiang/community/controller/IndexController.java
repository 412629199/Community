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


/**
 * 首页
 */
@Controller
public class IndexController {
   @Autowired
   private QuestionService questionService;
    @GetMapping(value = "/")
    public  String Index ( Model model,
                          @RequestParam(value = "page" ,defaultValue = "1") Integer page,
                          @RequestParam(value = "size" ,defaultValue = "5") Integer size
                          ){
        //查询paginationDTO 将其设置在model中
            PaginationDTO pagination= questionService.questionDTOList(page,size);
            model.addAttribute("pagination",pagination);
        return "index";
    }
}
