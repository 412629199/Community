package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_created,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreated},#{gmtModified},#{avatarUrl})")
    void insert(User user);
    @Select("select * from  user where token=#{token} ")
    User findByToken(@Param("token")  String token);
    @Select("select * from user where id=#{id}")
    User findById(@Param("id") Integer id);
    @Select("select * from user where account_id=#{accountId} ")
    User findByAccountId(@Param("accountId") String accountId);
    @Update("update user set gmt_modified = #{gmtModified} ,avatar_url=#{avatarUrl} ,name=#{name} ,token=#{token} where id=#{id}")
    void update(User user);
}
