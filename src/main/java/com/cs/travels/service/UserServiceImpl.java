package com.cs.travels.service;

import com.cs.travels.entity.User;
import com.cs.travels.mapper.UserDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDaoMapper userDaoMapper;

    // 用户的注册业务层逻辑
    @Override
    public void register(User user) {
     if (userDaoMapper.findByUsername(user.getUsername()) == null){
         userDaoMapper.registerSave(user);
     }else{
         throw new RuntimeException("用户名已经存在！");
     }
    }

    // 用户登录的业务层逻辑
    @Override
    public User login(User user) {
        User userDB = userDaoMapper.userLogin(user.getUsername());
        // 判断userDB是否为空
        if (userDB !=null){
            // 判断密码是否正确
            if (userDB.getPassword().equals(user.getPassword())){
                return userDB;
            }else{
                throw new RuntimeException("密码输入错误");
            }
        }else{
           throw new RuntimeException("用户名输入错误");
        }
    }
}
