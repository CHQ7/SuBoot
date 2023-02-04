package com.yunqi.platform.service;

import com.yunqi.platform.models.PlatformAdv;
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
 * 平台幻灯片
 * Created by @author CHQ on 2022/10/7
 */
@Service
public class IPlatformAdvService extends BaseServiceImpl<PlatformAdv> {


    /**
     * 幻灯片列表
     * @param query         请求参数
     * @return              分页列表
     */
    public Pagination<PlatformAdv> list(QueryBody query ) {
        Cnd cnd =  Cnd.NEW();
        // 模糊查询:幻灯片标题
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
     * 全部幻灯片
     * @return              幻灯片数据
     */
    public List<PlatformAdv> all() {
        return this.query(Cnd.where("status","=",true).desc("location").desc("createdAt"));
    }

    /**
     * 创建幻灯片
     * @param adv   新的幻灯片
     * @return      幻灯片
     */
    @Transactional
    public PlatformAdv create(PlatformAdv adv) {
        // 检查标题是否唯一
        if (countByTitle(adv.getTitle()) > 0) {
            throw new BizException("幻灯片标题已存在");
        }
        return this.insert(adv);
    }

    /**
     * 统计幻灯片标题
     * @param title 幻灯片标题
     * @return      统计数量
     */
    private int countByTitle(String title){
        return this.count(Cnd.where("title","=", title));
    }

    /**
     * 更新幻灯片
     * @param adv  幻灯片
     * @return     boolean
     */
    @Transactional
    public boolean update(PlatformAdv adv) {
        PlatformAdv oldAdv = this.fetch(adv.getId());
        // 检查标题是否唯一
        if(!Strings.sBlank(oldAdv.getTitle()).equalsIgnoreCase(adv.getTitle())) {
            if (countByTitle(adv.getTitle()) > 0) {
                throw new BizException("幻灯片标题已存在");
            }
        }
        return this.updateIgnoreNull(adv) != 0;
    }

    /**
     * 删除幻灯片
     * @param id    幻灯片ID
     * @return      boolean
     */
    @Transactional
    public boolean deleteById(String id) {
        return this.delete(id) != 0;
    }

    /**
     * 批量删除幻灯片
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
