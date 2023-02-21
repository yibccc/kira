package com.kira.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kira.controller.utils.R;
import com.kira.domain.Store;
import com.kira.domain.User;
import com.kira.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @author Kira
 * @create 2023-01-280:06
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public R<User> login(HttpServletRequest request, @RequestBody User user){
        //login
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername());
        User one = userService.getOne(queryWrapper);

        if(one == null){
            return R.error("登录失败");
        }

        if(!one.getPassword().equals(password)){
            return R.error("密码错误");
        }

        request.getSession().setAttribute("user",one.getId());
        return R.success(one);
    }

    //login out
    @PostMapping("/logout")
    public R<String> loginOut(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return R.success("退出成功");
    }
    //add
    @PostMapping("/register")
    public R<String> save(@RequestBody User user){
        log.info("新增user，user信息：｛｝",user.toString());
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(password);
        userService.save(user);
        return R.success("注册成功");
    }
    //修改密码
    @PutMapping
    public R<String> update(@RequestBody User user){
        log.info(user.toString());
        userService.updateById(user);
        return R.success("修改成功！");
    }

    //删除
    @DeleteMapping
    public R<String> deleteById(Integer id){
        userService.removeById(id);
        return R.success("删除成功");
    }
}
