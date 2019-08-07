package life.majiang.community.service;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Mr.Yang
 * @version 1.0
 * @date 2019/8/5 15:12
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public void crateOrUpdate(User user) {
      User result=  userMapper.findByAccountId(user.getAccountId());
      if (result==null){
          //设置当前时间为创建时间
          user.setGmtCreated(System.currentTimeMillis());
          //设置创建登陆者的时间为修改时间
          user.setGmtModified(user.getGmtCreated());
          //插入操作
          userMapper.insert(user);
      }else{
          //设置当前时间为更新时间
          result.setGmtModified(System.currentTimeMillis());
          //设置更新头像
          result.setAvatarUrl(user.getAvatarUrl());
          //设置更新的用户名
          result.setName(user.getName());
          //设置token
          result.setToken(user.getToken());
          //更新操作
          userMapper.update(result);
      }

    }
}
