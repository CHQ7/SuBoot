package com.yunqi.system.service;

import com.yunqi.starter.common.exception.BizException;
import com.yunqi.starter.common.lang.Lang;
import com.yunqi.starter.common.lang.Strings;
import com.yunqi.starter.common.lang.util.NutMap;
import com.yunqi.starter.database.service.BaseServiceImpl;
import com.yunqi.system.models.SysMenu;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by @author CHQ on 2022/6/15
 */
@Service
public class ISysMenuService extends BaseServiceImpl<SysMenu> {

    @Resource
    ISysRoleService sysRoleService;

    /**
     * 查询全部组织
     * @return 组织列表
     */
    public List<SysMenu> all() {
        List<SysMenu> list = this.query(Cnd.NEW().asc("location"));
        return this.getChildPerms(list,"");
    }

    /**
     * 创建菜单
     * @param menu  菜单
     */
    @Transactional
    public void create(SysMenu menu) {
        // 检查:菜单权限唯一编码是否存在
        if (this.count(Cnd.where("permission","=", menu.getPermission())) > 0) {
            throw new BizException("权限唯一编码已存在");
        }
        this.insert(menu);
        // 添加菜单权限给超级管理员
        this.insert("ims_sys_role_menu", Chain.make("roleId", sysRoleService.fetchByDefaultSysadminRoleId()).add("menuId", menu.getId()));
    }

    /**
     * 获取菜单
     * @param id    菜单ID
     * @return      菜单
     */
    public NutMap fetchById(String id){
        SysMenu menu = this.fetch(id);
        NutMap map = Lang.obj2nutmap(menu);
        map.put("parentName", "无");
        if (Strings.isNotBlank(menu.getParentId())) {
            map.put("parentName", this.fetch(menu.getParentId()).getName());
        }
        return map;
    }

    /**
     * 更新菜单
     * @param menu  菜单
     * @return      返回实际被更新的记录条数，返回 1，否则，返回 0
     */
    @Transactional
    public int update(SysMenu menu) {
        SysMenu oldMenu = this.fetch(menu.getId());
        if(oldMenu == null){
            throw new BizException("未知菜单");
        }
        // 检查角色名称是否唯一
        if (!Strings.sBlank(oldMenu.getPermission()).equalsIgnoreCase(menu.getPermission())) {
            if (this.count(Cnd.where("permission","=", menu.getPermission())) > 0) {
                throw new BizException("权限唯一编码已存在");
            }
        }
        return this.updateIgnoreNull(menu);
    }

    /**
     * 删除菜单
     * @param id    菜单ID
     * @return      返回实际被更新的记录条数，返回 1，否则，返回 0
     */
    @Transactional
    public int deleteById(String id) {
        return this.delete(id);
    }

    /**
     * 删除菜单和子菜单
     * 并且删除关联角色菜单
     * @param id    菜单ID
     */
    @Transactional
    public void deleteAndChild(String id) {
        SysMenu menu = this.fetch(id);
        // 获取所有数据
        List<SysMenu> list = this.query(Cnd.NEW().asc("location"));
        // 获取ID数组
        List<String> array = new ArrayList<>();
        // 添加父节点
        array.add(menu.getId());
        // 查找子节点
        List<String> stringList = this.getIds(list ,menu.getId(), array);
        String[] ids= stringList.toArray(new String[stringList.size()]);
        // 清除关联菜单
        this.dao().clear("ims_sys_menu", Cnd.where("id", "in", ids));
        // 清除角色关联菜单
        this.dao().clear("ims_sys_role_menu", Cnd.where("menuId", "in", ids));
    }


    /**
     * 菜单排序
     * @param ids   菜单ID数组字符串
     */
    @Transactional
    public void sort(String ids){
        if(Strings.isNotBlank(ids)){
            String[] menuIds =  ids.split(",");
            int i = 0;
            // 批量更新
            this.execute(Sqls.create("update ims_sys_menu set location=0"));
            for (String s : menuIds) {
                if (Strings.isNotBlank(s)) {
                    this.update(Chain.make("location", i), Cnd.where("id", "=", s));
                    i++;
                }
            }
        }
    }

    /**
     * 根据父节点的ID获取所有子节点 IDS
     * @param list
     * @param pid
     * @param ids
     * @return
     */
    public List<String> getIds(List<SysMenu> list, String pid, List<String> ids) {
        for (SysMenu t : list) {
            //遍历出父id等于参数的id，add进子节点集合
            if(pid.equals(t.getParentId())){
                //递归遍历下一级
                ids.add(t.getId());
                getIds(list, t.getId() ,ids);
            }
        }
        return ids;
    }

    /**
     * 根据父节点的ID获取所有子节点
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysMenu> getChildPerms(List<SysMenu> list, String parentId) {
        List<SysMenu> returnList = new ArrayList<>();
        for (SysMenu t : list) {
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId().equals(parentId)) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenu> list, SysMenu t) {
        // 得到子节点列表
        List<SysMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenu tChild : childList) {
            // 判断是否有子节点
            if (hasChild(list, tChild)) {
                for (SysMenu n : childList) {
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     * @param list
     * @param t
     * @return
     */
    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t) {
        List<SysMenu> tlist = new ArrayList<>();
        for (SysMenu n : list) {
            if (n.getParentId().equals(t.getId())) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenu> list, SysMenu t) {
        return getChildList(list, t).size() > 0;
    }


    /**
     * 查询角色的所有的启用权限
     * @param roleId
     * @return
     */
    public List<SysMenu> getMenusAndButtons(String roleId) {
        Sql sql = Sqls.create("select distinct a.* from ims_sys_menu a,ims_sys_role_menu b where a.id=b.menuId and" +
                " b.roleId=@roleId and a.disabled=@f order by a.location ASC");
        sql.params().set("roleId", roleId);
        sql.params().set("f", true);
        return this.listEntity(sql);
    }

    /**
     * 查询角色的按钮所有的启用权限
     * @param roleId
     * @return
     */
    public List<SysMenu> getDatas(String roleId) {
        Sql sql = Sqls.create("select distinct a.* from ims_sys_menu a,ims_sys_role_menu b where a.id=b.menuId and" +
                " b.roleId=@roleId and a.type='data' and a.disabled=@f order by a.location ASC");
        sql.params().set("roleId", roleId);
        sql.params().set("f", true);
        return this.listEntity(sql);
    }

    /**
     * 查询角色的按钮所有权限,包含开启停止状态的权限
     * @return
     */
    public List<SysMenu> getDatas() {
        Sql sql = Sqls.create("select distinct a.* from ims_sys_menu a,ims_sys_role_menu b where a.id=b.menuId and a.type='data' order by a.location ASC");
        return this.listEntity(sql);
    }

    /**
     * 查询角色,根据权限父级PID查询子级所有的启用权限
     * @param roleId
     * @param pid
     * @return
     */
    public List<SysMenu> getRoleMenus(String roleId, String pid) {
        Sql sql = Sqls.create("select distinct a.* from ims_sys_menu a,ims_sys_role_menu b where a.id=b.menuId and " +
                "$m and b.roleId=@roleId and a.disabled=@f order by a.location ASC");
        sql.params().set("roleId", roleId);
        sql.params().set("f", true);
        if (Strings.isNotBlank(pid)) {
            sql.vars().set("m", "a.parentId='" + pid + "'");
        } else {
            sql.vars().set("m", "(a.parentId='' or a.parentId is null)");
        }
        return this.listEntity(sql);
    }

    /**
     * 查询角色,根据权限父级PID统计子级所有的启用权限数量
     * @param roleId
     * @param pid
     * @return
     */
    public boolean hasChildren(String roleId, String pid) {
        Sql sql = Sqls.create("select count(*) from ims_sys_menu a,ims_sys_role_menu b where a.id=b.menuId and " +
                "$m and b.roleId=@roleId and a.disabled=@f order by a.location ASC");
        sql.params().set("roleId", roleId);
        sql.params().set("f", true);
        if (Strings.isNotBlank(pid)) {
            sql.vars().set("m", "a.parentId='" + pid + "'");
        } else {
            sql.vars().set("m", "(a.parentId='' or a.parentId is null)");
        }
        return this.count(sql) > 0;
    }


    /**
     * 查询用户的所有的启用权限
     * @param userId
     * @return
     */
    public List<SysMenu> getUserMenusAndButtons(String userId) {
        Sql sql = Sqls.create("select distinct a.* from ims_sys_menu a,ims_sys_role_menu b where a.id=b.menuId and " +
                " b.roleId in(select c.roleId from ims_sys_user_role c,ims_sys_role d where c.roleId=d.id and c.userId=@userId and d.disabled=@f) and a.disabled=@f order by a.location ASC");
        sql.params().set("userId", userId);
        sql.params().set("f", true);
        return this.listEntity(sql);
    }

    /**
     * 查询用户的菜单权限启用权限的所有权限
     * @param userId
     * @return
     */
    public List<SysMenu> getUserMenus(String userId) {
        Sql sql = Sqls.create("select distinct a.* from ims_sys_menu a,ims_sys_role_menu b where a.id=b.menuId and a.type=@type and" +
                " b.roleId in(select c.roleId from ims_sys_user_role c,ims_sys_role d where c.roleId=d.id and c.userId=@userId and d.disabled=@f) and a.disabled=@f order by a.location ASC");
        sql.params().set("type", "menu");
        sql.params().set("userId", userId);
        sql.params().set("f", true);
        return this.listEntity(sql);
    }

    /**
     * 查询用户,根据权限父级PID查询子级所有的启用权限
     * @param userId
     * @param pid
     * @return
     */
    public List<SysMenu> getUserRoleMenus(String userId, String pid) {
        Sql sql = Sqls.create("select distinct a.* from ims_sys_menu a,ims_sys_role_menu b where a.id=b.menuId and " +
                "$m and b.roleId in(select c.roleId from ims_sys_user_role c,ims_sys_role d where c.roleId=d.id and c.userId=@userId and d.disabled=@f) and a.disabled=@f order by a.location ASC");
        sql.params().set("userId", userId);
        sql.params().set("f", true);
        if (Strings.isNotBlank(pid)) {
            sql.vars().set("m", "a.parentId='" + pid + "'");
        } else {
            sql.vars().set("m", "(a.parentId='' or a.parentId is null)");
        }
        return this.listEntity(sql);
    }

    /**
     * 查询用户,根据权限父级PID统计子级所有的启用权限数量
     * @param userId
     * @param pid
     * @return
     */
    public boolean userHasChildren(String userId, String pid) {
        Sql sql = Sqls.create("select count(*) from ims_sys_menu a,ims_sys_role_menu b where a.id=b.menuId and " +
                "$m and b.roleId in(select c.roleId from ims_sys_user_role c,ims_sys_role d where c.roleId=d.id and c.userId=@userId and d.disabled=@f) and a.disabled=@f order by a.location ASC");
        sql.params().set("userId", userId);
        sql.params().set("f", true);
        if (Strings.isNotBlank(pid)) {
            sql.vars().set("m", "a.parentId='" + pid + "'");
        } else {
            sql.vars().set("m", "(a.parentId='' or a.parentId is null)");
        }
        return this.count(sql) > 0;
    }

}
