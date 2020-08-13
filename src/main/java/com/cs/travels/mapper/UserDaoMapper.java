package com.cs.travels.mapper;

import com.cs.travels.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDaoMapper {
    // 注册用户
    void registerSave(User user);

    // 根据用户名查询数据库
    User findByUsername(@Param("username") String username);

    User userLogin(@Param("username") String username);
}
