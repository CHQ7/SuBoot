package com.yunqi.core.provider;

import com.yunqi.starter.security.provider.IAuthProvider;
import com.yunqi.system.service.ISysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 实现权限验证接口扩展
 * Created by @author CHQ on 2022/6/15
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
