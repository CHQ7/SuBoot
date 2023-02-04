package com.yunqi.platform.service;

import com.yunqi.platform.models.PlatformNav;
import com.yunqi.starter.common.exception.BizException;
import com.yunqi.starter.common.lang.Strings;
import com.yunqi.starter.common.model.QueryBody;
import com.yunqi.starter.common.page.Pagination;
import com.yunqi.starter.database.service.BaseServiceImpl;
import org.nutz.dao.Cnd;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 导航列表
 * Created by @author CHQ on 2022/10/7
 */
@Service
public class IPlatformNavService extends BaseServiceImpl<PlatformNav> {


    /**
     * 导航列表
     * @param query         请求参数
     * @return              分页列表
     */
    public Pagination<PlatformNav> list(QueryBody query ) {
        Cnd cnd =  Cnd.NEW();
        // 模糊查询:导航标题
        if(Strings.isNotBlank(query.getString("title"))){
            cnd.and("title", "like", "%" + query.getString("title") + "%");
        }
        // 精确查询:状态
        if(Strings.isNotBlank(query.getString("status"))){
            cnd.and("status", "=", query.getBoolean("status"));
        }
        // 创建时间倒序
        cnd.desc("location").desc("createdAt");
        return this.listPage(query.page(),query.pageSize(), cnd);
    }

    /**
     * 全部导航
     * @return              导航数据
     */
    public List<PlatformNav> all() {
        return this.query(Cnd.where("status","=",true).desc("location").desc("createdAt"));
    }

    /**
     * 创建导航
     * @param nav   新的导航
     * @return      导航
     */
    @Transactional
    public PlatformNav create(PlatformNav nav) {
        // 检查标题是否唯一
        if (countByTitle(nav.getTitle()) > 0) {
            throw new BizException("导航标题已存在");
        }
        return this.insert(nav);
    }

    /**
     * 统计导航标题
     * @param title 导航标题
     * @return      统计数量
     */
    private int countByTitle(String title){
        return this.count(Cnd.where("title","=", title));
    }

    /**
     * 更新导航
     * @param nav  更新导航
     * @return     boolean
     */
    @Transactional
    public boolean update(PlatformNav nav) {
        PlatformNav oldNav = this.fetch(nav.getId());
        // 检查标题是否唯一
        if(!Strings.sBlank(oldNav.getTitle()).equalsIgnoreCase(nav.getTitle())) {
            if (countByTitle(nav.getTitle()) > 0) {
                throw new BizException("导航标题已存在");
            }
        }
        return this.updateIgnoreNull(nav) != 0;
    }

    /**
     * 删除导航
     * @param id    导航ID
     * @return      boolean
     */
    @Transactional
    public boolean deleteById(String id) {
        return this.delete(id) != 0;
    }

    /**
     * 批量删除导航
     * @param ids   ID数组字符串
     */
    @Transactional
    public void deleteByIds(String ids) {
        if(Strings.isNotBlank(ids)){
            String[] arr =  ids.split(",");
            this.delete(arr);
        }

    }


}
