package com.yunqi.system.service;


import com.yunqi.starter.common.constant.GlobalConstant;
import com.yunqi.starter.common.exception.BizException;
import com.yunqi.starter.common.lang.Strings;
import com.yunqi.starter.database.service.BaseServiceImpl;
import com.yunqi.system.models.SysDept;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @author CHQ on 2022/6/15
 */
@Service
public class ISysDeptService extends BaseServiceImpl<SysDept> {


    /**
     * 查询全部组织
     * @return 组织列表
     */
    public List<SysDept> all() {
        List<SysDept> list = this.query(Cnd.NEW().asc("location").asc("createdAt"));
        return this.getChildPerms(list,"");
    }

    /**
     * 创建组织
     * @param dept  组织
     * @return      插入后的对象
     */
    @Transactional
    public SysDept create(SysDept dept) {
        // 检查:组织名称是否存在
        if (this.count(Cnd.where("name",EQ, dept.getName())) > 0) {
            throw new BizException("组织名称已存在");
        }
        // 检查:组织唯一编码是否存在
        if (this.count(Cnd.where("code",EQ, dept.getCode())) > 0) {
            throw new BizException("组织唯一编码已存在");
        }
       return this.insert(dept);
    }

    /**
     * 更新组织
     * @param dept  组织
     * @return      返回实际被更新的记录条数，返回 1，否则，返回 0
     */
    @Transactional
    public int update(SysDept dept) {
        SysDept oldDept = this.fetch(dept.getId());
        if(oldDept == null){
            throw new BizException("未知组织");
        }
        // 检查组织名称是否唯一
        if(!Strings.sBlank(oldDept.getName()).equalsIgnoreCase(dept.getName())) {
            if (this.count(Cnd.where("name",EQ, dept.getName())) > 0) {
                throw new BizException("组织名称已存在");
            }
        }
        // 检查组织唯一编码是否唯一
        if (Strings.sBlank(oldDept.getCode()).equalsIgnoreCase(dept.getCode())) {
            if (this.count(Cnd.where("code",EQ, dept.getCode())) > 0) {
                throw new BizException("组织唯一编码已存在");
            }
            // 默认组织禁止更改
            if(!dept.getCode().equalsIgnoreCase(GlobalConstant.DEFAULT_SYSADMIN_DEPT)){
                throw new BizException("默认组织禁止更改");
            }
        }
        return this.updateIgnoreNull(dept);
    }

    /**
     * 删除组织
     * @param id    组织ID
     * @return      返回实际被更新的记录条数，返回 1，否则，返回 0
     */
    @Transactional
    public int deleteById(String id) {
        SysDept dept = this.fetch(id);
        // 默认组织禁止删除
        if(dept != null && dept.getCode().equalsIgnoreCase(GlobalConstant.DEFAULT_SYSADMIN_DEPT)){
            throw new BizException("默认组织禁止删除");
        }
        // 存在下级组织,不允许删除
        if(this.count(Cnd.where("parentId",EQ, id)) > 0){
            throw new BizException("存在下级组织,不允许删除");
        }
        return this.delete(id);
    }


    /**
     * 组织排序
     * @param ids   组织ID数组
     */
    @Transactional
    public void sort(String[] ids){
        int i = 0;
        // 批量更新
        this.execute(Sqls.create("update ims_sys_dept set location=0"));
        for (String s : ids) {
            if (Strings.isNotBlank(s)) {
                this.update(Chain.make("location", i), Cnd.where("id", EQ, s));
                i++;
            }
        }
    }


    /**
     * 根据父节点的ID获取所有子节点
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<SysDept> getChildPerms(List<SysDept> list, String parentId) {
        List<SysDept> returnList = new ArrayList<>();
        for (SysDept t : list) {
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
    private void recursionFn(List<SysDept> list, SysDept t) {
        // 得到子节点列表
        List<SysDept> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysDept tChild : childList) {
            // 判断是否有子节点
            if (hasChild(list, tChild)) {
                for (SysDept n : childList) {
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
    private List<SysDept> getChildList(List<SysDept> list, SysDept t) {
        List<SysDept> tlist = new ArrayList<>();
        for (SysDept n : list) {
            if (n.getParentId().equals(t.getId())) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysDept> list, SysDept t) {
        return getChildList(list, t).size() > 0;
    }

}
