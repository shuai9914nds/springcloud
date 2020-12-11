package com.springcloud.role.client;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.role.api.dto.MenuRoleDto;
import com.springcloud.role.entity.MenuRoleRel;
import com.springcloud.role.entity.Role;
import com.springcloud.role.service.IMenuRoleRelService;
import com.springcloud.role.service.IRoleService;
import common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: liushuai
 * @date: 2020/11/14
 * @description：
 */
@RestController
public class RoleClient {
    private static final Logger logger = LoggerFactory.getLogger(RoleClient.class);

    @Resource
    private IRoleService iRoleService;
    @Resource
    private IMenuRoleRelService iMenuRoleRelService;

    /**
     * 新增一个角色
     *
     * @param role 角色实体类
     * @return Result<Role>
     */
    @PutMapping(value = "/role")
    public Result<Role> addRole(@RequestBody Role role) {
        iRoleService.save(role);
        return new Result<>(role);
    }

    /**
     * 删除一个角色
     *
     * @param roleId 角色id
     * @return Result<Void>
     */
    @DeleteMapping(value = "/role")
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> deleteRole(@RequestParam(value = "roleId") Integer roleId) {
        iRoleService.removeById(roleId);
        LambdaQueryWrapper<MenuRoleRel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MenuRoleRel::getRoleId, roleId);
        iMenuRoleRelService.remove(queryWrapper);
        return new Result<>();
    }

    /**
     * 新增角色菜单关联关系
     *
     * @param menuRoleDto 菜单角色实体类
     * @return Result<Void>
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/menu/role")
    public Result<Void> updateMenuRole(@RequestBody MenuRoleDto menuRoleDto) {
        Role role = new Role();
        role.setRoleName(menuRoleDto.getRoleName());
        role.setRoleId(menuRoleDto.getRoleId());
        iRoleService.updateById(role);
        List<MenuRoleRel> menuRoleRels = new ArrayList<>();
        for (Integer menuId : menuRoleDto.getMenuIds()) {
            MenuRoleRel menuRoleRel = new MenuRoleRel();
            menuRoleRel.setMenuId(menuId);
            //TODO 从token中解析，获取用户名称
            menuRoleRel.setCreateBy("shuai");
            menuRoleRel.setRoleId(role.getRoleId());
            menuRoleRels.add(menuRoleRel);
        }
        iMenuRoleRelService.saveBatch(menuRoleRels);
        return new Result<>();
    }
}
