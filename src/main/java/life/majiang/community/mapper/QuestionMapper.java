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

    /**
     * 根据用户id查找question数据
     * @param userId 用户id
     * @param offSet 偏移量
     * @param size 每页展示多少
     * @return 返回question集合
     */
    @Select("select  * from question where creator = #{userId} limit #{offSet},#{size}")
    List<Question> listByUserId(@RequestParam(name = "userId") Integer userId,@RequestParam(name = "offSet") Integer offSet,@RequestParam(name = "size") Integer size);

    /**
     * 根据用户id统计问题个数
     * @param userId 用户id
     * @return 用户问题数量
     */
    @Select("select count(1) from question where creator = #{userId}")
    Integer countByUserId(@RequestParam(name = "userId") Integer userId);
}
