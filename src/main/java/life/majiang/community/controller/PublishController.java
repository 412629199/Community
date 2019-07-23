package life.majiang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 问题发起
 */
@Controller
public class PublishController {
    @RequestMapping("/publish")
    public String publish(){

        return "publish";
    }
}
