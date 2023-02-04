package com.yunqi.member.service;

import com.yunqi.member.models.MemberLabel;
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
 * 用户标签
 * Created by @author CHQ on 2022/11/2
 */
@Service
public class IMemberLabelService extends BaseServiceImpl<MemberLabel> {

    /**
     * 标签列表
     * @param query         请求参数
     * @return              分页列表
     */
    public Pagination<MemberLabel> list(QueryBody query ) {
        Cnd cnd =  Cnd.NEW();
        // 模糊查询:标签名字
        if(Strings.isNotBlank(query.getString("name"))){
            cnd.and("name", "like", "%" + query.getString("name") + "%");
        }
        // 创建时间倒序
        cnd.desc("createdAt");
        return this.listPage(query.page(),query.pageSize(), cnd);
    }

    /**
     * 全部标签
     * @return      标签数据
     */
    public List<MemberLabel> all() {
        return this.query(Cnd.NEW().desc("createdAt"));
    }

    /**
     * 创建标签
     * @param label   新的标签
     * @return        标签
     */
    @Transactional
    public MemberLabel create(MemberLabel label) {
        // 检查标签名称
        if (countByName(label.getName()) > 0) {
            throw new BizException("标签名称已存在");
        }
        return this.insert(label);
    }


    /**
     * 统计标签名称
     * @param name 标签名称
     * @return      统计数量
     */
    private int countByName(String name){
        return this.count(Cnd.where("name","=", name));
    }

    /**
     * 更新标签
     * @param label  更新标签
     * @return       boolean
     */
    @Transactional
    public boolean update(MemberLabel label) {
        MemberLabel oldLabel = this.fetch(label.getId());
        // 检查标签名称
        if(!Strings.sBlank(oldLabel.getName()).equalsIgnoreCase(label.getName())) {
            if (countByName(label.getName()) > 0) {
                throw new BizException("标签名称已存在");
            }
        }
        return this.updateIgnoreNull(label) != 0;
    }

    /**
     * 删除标签
     * @param id    ID
     * @return      boolean
     */
    @Transactional
    public boolean deleteById(String id) {
        return this.delete(id) != 0;
    }

    /**
     * 批量删除标签
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
