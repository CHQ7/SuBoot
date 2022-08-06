package com.yunqi.system.service;


import com.yunqi.starter.common.constant.GlobalConstant;
import com.yunqi.starter.common.exception.BizException;
import com.yunqi.starter.common.lang.Strings;
import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.page.Pagination;
import com.yunqi.starter.database.service.BaseServiceImpl;
import com.yunqi.system.models.SysMenu;
import com.yunqi.system.models.SysRole;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @author CHQ on 2022/6/15
 */
@Service
public class ISysRoleService extends BaseServiceImpl<SysRole> {

    /**
     * 角色列表
     * @param query         请求参数
     * @return              分页列表
     */
    public Pagination<SysRole> list(QueryBody query ) {
        Cnd cnd =  Cnd.NEW();
        // 模糊查询:角色名称
        if(Strings.isNotBlank(query.getString("name"))){
            cnd.and("name", "like", "%" + query.getString("name") + "%");
        }
        // 模糊查询:角色唯一编码
        if(Strings.isNotBlank(query.getString("code"))){
            cnd.and("code", "like", "%" + query.getString("code") + "%");
        }
        // 创建时间倒序
        cnd.desc("location").desc("createdAt");
        return this.listPage(query.page(),query.pageSize(), cnd);
    }


    /**
     * 查询全部角色
     * @return 角色列表
     */
    public List<SysRole>  all() {
        return this.query(Cnd.NEW().desc("location").asc("createdAt"));
    }


    /**
     * 创建角色
     * @param role 角色
     * @return     插入后的对象
     */
    @Transactional
    public SysRole create(SysRole role) {
        // 检查:角色名称是否存在
        if (this.count(Cnd.where("name","=", role.getName())) > 0) {
            throw new BizException("角色名称已存在");
        }
        // 检查:角色唯一编码是否存在
        if (this.count(Cnd.where("code","=", role.getCode())) > 0) {
            throw new BizException("角色唯一编码已存在");
        }
       return this.insert(role);
    }

    /**
     * 查询角色信息
     * @param code  唯一编码
     * @return      角色信息
     */
    public SysRole fetchByCode(String code){
        return this.fetch(Cnd.where("code","=",code));
    }

    /**
     * 更新角色
     * @param role 角色
     * @return     返回实际被更新的记录条数，返回 1，否则，返回 0
     */
    @Transactional
    public int update(SysRole role) {
        SysRole oldRole = this.fetch(role.getId());
        if(oldRole == null){
            throw new BizException("未知角色");
        }
        // 默认角色禁止操作
        if(oldRole.getCode().equalsIgnoreCase(GlobalConstant.DEFAULT_SYSADMIN_ROLE)){
            throw new BizException("超级管理员角色禁止操作");
        }
        // 检查角色名称是否唯一
        if(!Strings.sBlank(oldRole.getName()).equalsIgnoreCase(role.getName())) {
            if (this.count(Cnd.where("name","=", role.getName())) > 0) {
                throw new BizException("角色名称已存在");
            }
        }
        // 检查角色唯一编码是否唯一
        if (!Strings.sBlank(oldRole.getCode()).equalsIgnoreCase(role.getCode())) {
            if (this.count(Cnd.where("code","=", role.getCode())) > 0) {
                throw new BizException("角色唯一编码已存在");
            }
        }
        return this.updateIgnoreNull(role);
    }

    /**
     * 删除角色
     * @param id    角色ID
     * @return      返回实际被更新的记录条数，返回 1，否则，返回 0
     */
    @Transactional
    public int deleteById(String id) {
        SysRole role = this.fetch(id);
        // 默认角色禁止操作
        if(role != null && role.getCode().equalsIgnoreCase(GlobalConstant.DEFAULT_SYSADMIN_ROLE)){
            throw new BizException("超级管理员角色禁止操作");
        }
        return this.delete(id);
    }

    /**
     * 保存角色菜单
     * @param menuIds   菜单ID数组字符串
     * @param roleId    角色ID
     */
    @Transactional
    public void saveMenu(String[] menuIds, String roleId) {
        SysRole role = this.fetch(roleId);
        // 默认角色禁止操作
        if(role != null && role.getCode().equalsIgnoreCase(GlobalConstant.DEFAULT_SYSADMIN_ROLE)){
            throw new BizException("超级管理员角色禁止操作");
        }
        // 清除角色菜单关联数据
        this.clear("ims_sys_role_menu", Cnd.where("roleId", "=", roleId));
        for (String s : menuIds) {
            // 重新添加角色菜单关联数据
            this.insert("ims_sys_role_menu", Chain.make("roleId", roleId).add("menuId", s));
        }
    }

    /**
     * 获取角色权限
     * @param role 角色
     * @return     权限列表
     */
    public List<String> getPermissionList(SysRole role) {
        // 根据角色查询权限
        this.fetchLinks(role, "menus", Cnd.where("disabled", "=", true));
        List<String> list = new ArrayList<>();
        for (SysMenu menu : role.getMenus()) {
            if (Strings.isNotBlank(menu.getPermission())) {
                list.add(menu.getPermission());
            }
        }
        return list;
    }

    /**
     * 获取超级管理员角色ID
     * @return 超级管理员角色ID
     */
    public String fetchByDefaultSysadminRoleId() {
        return this.fetch(Cnd.where("code","=", GlobalConstant.DEFAULT_SYSADMIN_ROLE)).getId();
    }
}
