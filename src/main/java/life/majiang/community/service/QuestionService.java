package life.majiang.community.service;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.Question;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 问题业务层
 */
@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO questionDTOList(Integer page, Integer size){
        //创建pagination 对象
        PaginationDTO paginationDTO=new PaginationDTO();
        //获取总共的问题条数
        Integer totalCount=questionMapper.count();
        //设置分页
        paginationDTO.setPagination(page,totalCount,size);

        //设置页面页数容错
        if(page<1){
            //页数小于1 等时候也显示第一页
            page=1;
        }
        if(paginationDTO.getTotalPage()!=null){
            if(page>paginationDTO.getTotalPage()){
                //当页数大于最大页的时候显示最后一页
                page=paginationDTO.getTotalPage();
            }
        }

        //设置offSet
        Integer offSet=size*(page-1);

        //获取所有的question 并且传入参数
        List<Question>questionList=questionMapper.list(offSet,size);

        //创建questionDTOlist
        List<QuestionDTO>questionDTOList=new ArrayList<>();

        //遍历集合questionList
        for (Question question : questionList) {

            //查询登录的用户
            User user=userMapper.findById(question.getId());
            //创建QuestionDTO对象
            QuestionDTO questionDTO= new QuestionDTO();
            //通过spring中的BeanUtils工具复制question到questionDTO
            BeanUtils.copyProperties(question,questionDTO);
            if(user!=null){
                //将将查询到的user设置到questionDTO
                questionDTO.setUser(user);
            }else {
                questionDTO.setUser(new User());
            }
            //将questionDTO添加到集合中
            questionDTOList.add(questionDTO);

        }
        paginationDTO.setQuestionDTOList(questionDTOList);
        //返回paginationDTO
        return  paginationDTO;
    }

    public PaginationDTO listByUserId(Integer userId, Integer page, Integer size) {
        //创建pagination 对象
        PaginationDTO paginationDTO=new PaginationDTO();
        //获取总共的问题条数
        Integer totalCount=questionMapper.countByUserId(userId);
        //设置分页
        paginationDTO.setPagination(page,totalCount,size);

        //设置页面页数容错
        if(page<1){
            //页数小于1 等时候也显示第一页
            page=1;
        }
        if(paginationDTO.getTotalPage()!=null){
            if(page>paginationDTO.getTotalPage()){
                //当页数大于最大页的时候显示最后一页
                page=paginationDTO.getTotalPage();
            }
        }

        //设置offSet
        Integer offSet=size*(page-1);

        //获取所有的question 并且传入参数
        List<Question>questionList=questionMapper.listByUserId(userId,offSet,size);
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
            if(user!=null){
                //将将查询到的user设置到questionDTO
                questionDTO.setUser(user);
            }else {
                questionDTO.setUser(new User());
            }
            //将questionDTO添加到集合中
            questionDTOList.add(questionDTO);

        }

        paginationDTO.setQuestionDTOList(questionDTOList);
        //返回paginationDTO
        return  paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
       Question question= questionMapper.getById(id);
        //创建QuestionDTO对象
        QuestionDTO questionDTO= new QuestionDTO();
        //通过spring中的BeanUtils工具复制question到questionDTO
        BeanUtils.copyProperties(question,questionDTO);
        //查询登录的用户
        User user=userMapper.findById(question.getCreator());
        if(user!=null){
            //将将查询到的user设置到questionDTO
            questionDTO.setUser(user);
        }else {
            questionDTO.setUser(new User());
        }
        return  questionDTO;
    }
}
