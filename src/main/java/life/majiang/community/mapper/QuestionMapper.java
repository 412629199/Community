package life.majiang.community.mapper;

import life.majiang.community.dto.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface QuestionMapper {
    /**
     * 添加问题数据
     * @param question 添加的问题
     */
    @Insert("insert into question (title, discription, gmt_created, gmt_modified, creator, comment_count, view_count, like_count, tag) values (#{title}, #{discription}, #{gmtCreated}, #{gmtModified}, #{creator}, #{commentCount}, #{viewCount}, #{likeCount}, #{tag})")
    void createQuestion(Question question);

    /**
     * 查询问题列表
     * @param offSet
     * @param size 每页展示的数量
     * @return
     */
    @Select("select  * from question limit #{offSet},#{size}")
    List<Question> list(@RequestParam(name = "offSet") Integer offSet,@RequestParam(name = "size") Integer size);

    /**
     * 查询所有问题数
     * @return 返回查询的条数
     */
    @Select("select count(1) from question")
    Integer count();
}
