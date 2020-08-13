package com.cs.travels;

import com.cs.travels.entity.User;
import com.cs.travels.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = TravelsApplication.class)
@RunWith(SpringRunner.class)
public class TestUserService {
    @Autowired
    private UserService userService;

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("zhangyan");
        user.setPassword("zhangyan");
        user.setEmail("zhangyan@qq.com");
        userService.register(user);
    }
}
