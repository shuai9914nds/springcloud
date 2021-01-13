package com.devops.mvc.controller.user.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.devops.api.entity.User;
import com.devops.api.query.QueryUserFeignApi;
import com.devops.base.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: liushuai
 * @date: 2020/11/25
 * @description：对接前端页面的controller
 */
@RestController
public class QueryUserController {

    @Resource
    private QueryUserFeignApi queryUserFeignApi;

    /**
     * 分页查询用户信息
     *
     * @param current 当前页
     * @param size    每页显示行数
     * @param name    用户名称
     * @return Result<IPage < User>>
     */
    @GetMapping(value = "/mvc/user/page")
    public Result<Page<User>> selectUserPage(@RequestParam("current") Long current, @RequestParam("size") Long size,
                                             @RequestParam(value = "name", required = false) String name, @RequestParam(value = "orderBy", required = false) String orderBy) {
        return queryUserFeignApi.selectUserPage(current, size, name, orderBy);
    }
}
