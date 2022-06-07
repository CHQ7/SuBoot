package com.yunqi.system.service;

import com.yunqi.starter.common.constant.GlobalConstant;
import com.yunqi.starter.common.constant.Globals;
import com.yunqi.starter.common.exception.BizException;
import com.yunqi.starter.common.lang.Strings;
import com.yunqi.starter.database.service.BaseServiceImpl;
import com.yunqi.system.models.SysConfig;
import org.nutz.dao.Cnd;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统参数
 * Created by @author JsckChin on 2021/10/1
 */
@Service
public class ISysConfigService extends BaseServiceImpl<SysConfig> {

    /**
     * 系统参数列表
     * @param page       页码
     * @param pageSize   每页几条数据
     * @param conf       name:用户账号,nickname:用户姓名
     * @return           分页列表
     */
    public Object list(Integer page, int pageSize, SysConfig conf) {
        Cnd cnd =  Cnd.NEW();
        // 模糊查询:系统参数唯一编码
        if(Strings.isNotBlank(conf.getConfigKey())){
            cnd.and("configKey", LIKE, "%" + conf.getConfigKey() + "%");
        }
        // 模糊查询:系统参数值
        if(Strings.isNotBlank(conf.getConfigValue())){
            cnd.and("configValue", LIKE, "%" + conf.getConfigValue() + "%");
        }
        // 模糊查询:系统标题名称
        if(Strings.isNotBlank(conf.getConfigName())){
            cnd.and("configName", LIKE, "%" + conf.getConfigName() + "%");
        }
        // 创建时间倒序
        cnd.desc("createdAt");
        return this.listPage(page, pageSize, cnd);
    }

    /**
     * 创建系统参数
     * @param conf  参数
     * @return      插入后的对象
     */
    @Transactional
    public SysConfig create(SysConfig conf) {
        // 检查:标题名称是否存在
        if (this.count(Cnd.where("configName",EQ, conf.getConfigName())) > 0) {
            throw new BizException("标题已存在");
        }
        conf =  this.insert(conf);
        // 初始化变量
        this.initSysParam();
        return conf;
    }

    /**
     * 更新系统参数
     * @param conf  参数
     */
    @Transactional
    public void update(SysConfig conf) {
        SysConfig oldConf = this.fetch(conf.getConfigKey());
        if(oldConf == null){
            throw new BizException("未知参数");
        }
        // 检查标题名称是否唯一
        if(!Strings.sBlank(oldConf.getConfigName()).equalsIgnoreCase(conf.getConfigName())) {
            if (this.count(Cnd.where("configName",EQ, conf.getConfigName())) > 0) {
                throw new BizException("标题已存在");
            }
        }
        if(this.updateIgnoreNull(conf) > 0){
            this.initSysParam();
        }
    }

    /**
     * 删除系统参数
     * @param configKey    系统参数ID
     */
    @Transactional
    public void deleteById(String configKey) {
        if (Strings.sBlank(configKey).startsWith(GlobalConstant.DEFAULT_SYSTEM_PARAM)) {
            throw new BizException("系统参数禁止删除");
        }

        if(this.delete(configKey)>0){
            this.initSysParam();
        }
    }

    /**
     * 初始化系统信息
     */
    public void initSysParam() {
        Globals.CustomConfig.clear();
        List<SysConfig> configList = this.query();
        for (SysConfig sysConfig : configList) {
            switch (Strings.sNull(sysConfig.getConfigKey())) {
                case "AppName":
                    Globals.AppName = sysConfig.getConfigValue();
                    break;
                case "AppDomain":
                    Globals.AppDomain = sysConfig.getConfigValue();
                    break;
                case "AppUploadPath":
                    Globals.AppUploadPath = sysConfig.getConfigValue();
                    break;
                default:
                    break;
            }
            Globals.CustomConfig.put(sysConfig.getConfigKey(), sysConfig.getConfigValue());
        }
    }

}
