package com.yunqi.common.config.nutz;

import org.nutz.dao.ConnCallback;
import org.nutz.dao.impl.sql.run.NutDaoRunner;
import org.nutz.lang.Lang;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * <p>
 *     集成Nutz事务和拦截链
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
public class SpringDaoRunner extends NutDaoRunner {

    @Override
    public void _run(DataSource dataSource, ConnCallback callback) {

        Connection con = DataSourceUtils.getConnection(dataSource);
        try {
            callback.invoke(con);
        }
        catch (Exception e) {
            throw Lang.wrapThrow(e);
        }
        finally {
            DataSourceUtils.releaseConnection(con, dataSource);
        }
    }

}
