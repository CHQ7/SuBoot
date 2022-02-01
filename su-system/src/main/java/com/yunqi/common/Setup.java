package com.yunqi.common;

import com.yunqi.common.utils.SpringUtil;
import com.yunqi.starter.common.constant.GlobalConstant;
import com.yunqi.system.models.SysDept;
import com.yunqi.system.models.SysRole;
import com.yunqi.system.models.SysUser;
import com.yunqi.system.service.SysConfigService;
import com.yunqi.system.service.SysTaskService;
import lombok.extern.slf4j.Slf4j;
import org.nutz.dao.Chain;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.FileSqlManager;
import org.nutz.dao.sql.Sql;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *     服务启动时初始化
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
@Slf4j
@Component
public class Setup implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Initialize system environment");
        Dao dao = SpringUtil.getBean(Dao.class);
        // 初始化数据表
        initSys(dao);
        // 初始化定时任务
        initSysTask(dao);

        // 初始化系统全局变量
        SysConfigService sysConfigService= SpringUtil.getBean(SysConfigService.class);
        sysConfigService.initSysParam();
    }


    /**
     * 初始化数据表
     */
    private void initSys(Dao dao) {
        // 检测用户是否有数据
        if (dao.count(SysUser.class) == 0) {
            // 初始化机构
            SysDept dept = new SysDept();
            dept.setParentId("");
            dept.setName("总部");
            dept.setCode(GlobalConstant.DEFAULT_SYSADMIN_DEPT);
            dept.setNote("总公司");
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
            user.setName(GlobalConstant.DEFAULT_SYSADMIN_NAME);
            user.setSalt("61ho2vejfah1pq6dh781jds0t5");
            user.setPassword("c63a2bcc1650a5cd762a3ae585ddb35b969512a4ae47da2b214cffa1899a15b3");
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
    private void initSysTask(Dao dao) {
        SysTaskService sysTaskService= SpringUtil.getBean(SysTaskService.class);
        if (!dao.exists("ims_sys_qrtz_triggers")) {
            log.info("执行Quartz SQL脚本");
            //执行Quartz SQL脚本
            String dbType = dao.getJdbcExpert().getDatabaseType();
            log.debug("dbType:::" + dbType);
            FileSqlManager fmq = new FileSqlManager("quartz/" + dbType.toLowerCase() + ".sql");
            List<Sql> sqlListq = fmq.createCombo(fmq.keys());
            Sql[] sqlsq = sqlListq.toArray(new Sql[sqlListq.size()]);
            for (Sql sql : sqlsq) {
                dao.execute(sql);
            }
        }
        // 初始化定时任务
        sysTaskService.init();
    }
}
