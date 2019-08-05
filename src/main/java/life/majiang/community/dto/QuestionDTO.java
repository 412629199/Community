package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String  discription;
    private String gmtCreated;
    private String gmtModified;
    private Integer creator ;
    private Integer commentCount ;
    private Integer viewCount ;
    private Integer likeCount;
    private String tag;
    private User user;

}
