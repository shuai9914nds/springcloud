package com.devops.api.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.devops.api.entity.Role;
import com.devops.base.common.Result;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/8/31
 * @description：
 */
@Api(value = "API - QueryMenuFeignApi", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@FeignClient(name = "role", path = "/role", contextId = "queryRole")
public interface QueryRoleFeignApi {

    /**
     * 查询角色列表
     *
     * @param roleIds 角色id列表
     * @return Result<List < Role>>
     */
    @GetMapping(value = "/role/list")
    Result<List<Role>> selectRoleList(@RequestParam("roleIds") List<Integer> roleIds);

    /**
     * 分页查询用户信息
     *
     * @param current  当前页
     * @param size     每页显示行数
     * @param roleName 角色名称
     * @return Result<IPage < Role>>
     */
    @GetMapping(value = "/role/page")
    Result<Page<Role>> selectRolePage(@RequestParam("current") Long current, @RequestParam("size") Long size,
                                      @RequestParam(value = "roleName", required = false) String roleName, @RequestParam(value = "orderBy", required = false) String orderBy);

    /**
     * 查询一个角色
     *
     * @param roleId   角色id
     * @param roleName 角色名称
     * @return Result<Role>
     */
    @GetMapping(value = "/role")
    Result<Role> selectOne(@RequestParam(value = "roleId", required = false) Integer roleId, @RequestParam(value = "roleName", required = false) String roleName);

    /**
     * 查询一个用户拥有的角色
     *
     * @param uid 用户id
     * @return Result<Role>
     */
    @GetMapping(value = "/role/list/{uid}")
    Result<List<Role>> selectRoleListByUid(@PathVariable(value = "uid") Integer uid);

    /**
     * 查询全部角色列表
     *
     * @return Result<List < Role>>
     */
    @GetMapping("/role/all/list")
    Result<List<Role>> selectRoleList();
}
