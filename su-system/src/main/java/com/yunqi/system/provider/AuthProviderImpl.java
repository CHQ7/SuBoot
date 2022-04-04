package com.yunqi.system.provider;

import com.yunqi.starter.security.provider.IAuthProvider;
import com.yunqi.system.service.ISysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 实现权限验证接口扩展
 * Created by @author JsckChin on 2022/2/16
 */
@Service
public class AuthProviderImpl implements IAuthProvider {

    @Resource
    private ISysUserService sysUserService;

    @Override
    public List<String> getPermissionList(String userId) {
        return sysUserService.getPermissionList(userId);
    }

    @Override
    public List<String> getRoleList(String userId) {
        return sysUserService.getRoleList(userId);
    }
}
