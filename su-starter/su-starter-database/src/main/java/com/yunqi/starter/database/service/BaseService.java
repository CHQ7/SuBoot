package com.yunqi.starter.database.service;

import org.nutz.dao.Dao;

import java.util.List;

/**
 * Service接口
 * Created by @author JsckChin on 2022/1/29
 */
public interface BaseService<T>{

    /**
     * 获取dao实例
     * @return dao实例
     */
    Dao dao();

    /**
     * 获取全部数据
     * @return 全部数据
     */
    List<T> query();
}
