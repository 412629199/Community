package life.majiang.community.mapper;

import life.majiang.community.dto.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title, discription, gmt_created, gmt_modified, creator, comment_count, view_count, like_count, tag) values (#{title}, #{discription}, #{gmtCreated}, #{gmtModified}, #{creator}, #{commentCount}, #{viewCount}, #{likeCount}, #{tag})")
    void createQuestion(Question question);
}
