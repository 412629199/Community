package life.majiang.community.service;

import life.majiang.community.dto.Question;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题业务层
 */
@Service
public class QestionService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDTO> questionDTOList(){
        //获取所有的question
        List<Question>questionList=questionMapper.list();
        //创建questionDTOlist
        List<QuestionDTO>questionDTOList=new ArrayList<>();
        //遍历集合questionList
        for (Question question : questionList) {
            //查询登录的用户
            User user=userMapper.findById(question.getCreator());
            //创建QuestionDTO对象
            QuestionDTO questionDTO= new QuestionDTO();
            //通过spring中的BeanUtils工具复制question到questionDTO
            BeanUtils.copyProperties(question,questionDTO);
            //将将查询到的user设置到questionDTO
            questionDTO.setUser(user);
            //将questionDTO添加到集合中
            questionDTOList.add(questionDTO);
        }
        //返回questionDTOList集合
        return  questionDTOList;
    }
}
