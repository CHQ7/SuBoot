package com.yunqi.common.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.yunqi.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.nutz.lang.Strings;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by @author JsckChin on 2022/1/22
 */
@Slf4j
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    SysUserService sysUserService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return sysUserService.getPermissionList(Strings.sNull(loginId));
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return sysUserService.getRoleList(Strings.sNull(loginId));
    }
}
