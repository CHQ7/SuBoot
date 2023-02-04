package com.yunqi.core;

import com.yunqi.starter.common.constant.GlobalConstant;
import com.yunqi.starter.common.constant.Globals;
import com.yunqi.system.models.SysDept;
import com.yunqi.system.models.SysRole;
import com.yunqi.system.models.SysUser;
import com.yunqi.system.service.ISysTaskService;
import lombok.extern.slf4j.Slf4j;
import org.nutz.dao.Chain;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.FileSqlManager;
import org.nutz.dao.sql.Sql;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 服务启动时初始化
 * Created by @author CHQ on 2022/6/15
 */
@Slf4j
@Component
public class Setup implements ApplicationRunner {

    @Resource
    private Dao dao;

    @Resource
    private ISysTaskService sysTaskService;

    /**
     * 初始化数据表
     */
    private void initSys() {
        // 检测用户是否有数据
        if (dao.count(SysUser.class) == 0) {
            // 初始化机构
            SysDept dept = new SysDept();
            dept.setParentId("");
            dept.setName("总部");
            dept.setCode(GlobalConstant.DEFAULT_SYSADMIN_DEPT);
            dept.setNote("总部");
            SysDept dbdept = dao.insert(dept);

            // 初始化角色
            SysRole role = new SysRole();
            role.setName("超级管理员");
            role.setCode(GlobalConstant.DEFAULT_SYSADMIN_ROLE);
            role.setNote("系统最高管理员,拥有所有权限");
            role.setDisabled(true);
            SysRole dbrole = dao.insert(role);

            // 初始化超管用户
            SysUser user = new SysUser();
            user.setNickname("超级管理员");
            user.setNote("超级管理员,拥有所有操作权限 ^_^");
            user.setUsername(GlobalConstant.DEFAULT_SYSADMIN_NAME);
            user.setSalt("61ho2vejfah1pq6dh781jds0t5");
            user.setPassword("dd02eeb82a6d4084fde09796915afbb79f7a4d0afd3ed211d52ab1a736e91ade");
            user.setDisabled(true);
            user.setDeptId(dbdept.getId());
            SysUser dbuser =  dao.insert(user);

            // 不同的插入数据方式(安全)
            // 用户关联部门
            dao.insert("ims_sys_user_dept", Chain.make("userId", dbuser.getId()).add("deptId", dbdept.getId()));
            // 用户关联角色
            dao.insert("ims_sys_user_role", Chain.make("userId", dbuser.getId()).add("roleId", dbrole.getId()));

            // 执行菜单SQL脚本
            FileSqlManager fm = new FileSqlManager("db/");
            fm.setByRow(true);
            List<Sql> sqlList = fm.createCombo(fm.keys());
            Sql[] sqls = sqlList.toArray(new Sql[sqlList.size()]);
            for (Sql sql : sqls) {
                dao.execute(sql);
            }

            // 菜单关联到角色
            dao.execute(Sqls.create("INSERT INTO ims_sys_role_menu(roleId,menuId) SELECT @roleId,id FROM ims_sys_menu").setParam("roleId", dbrole.getId()));
        }
    }

    /**
     * 初始化定时任务
     */
    private void initSysTask() {
        // 初始化定时任务
        sysTaskService.init();
    }


    private void initGlobals(){
        // 当前项目路径的地址
        Globals.AppRoot = System.getProperty("user.dir");
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Initialize system environment");
        // 初始化数据表
        initSys();
        // 初始化定时任务
        initSysTask();

    }
}
