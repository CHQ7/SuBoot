package com.yunqi.common.config.nutz;

import com.yunqi.common.config.DataSourceConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.resource.Scans;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *     Nutz初始值设定项
 * </p>
 * Created by @author JsckChin on 2022/1/22
 */
@Slf4j
@Configuration
@ConditionalOnBean({Dao.class})
@AutoConfigureAfter({DataSourceConfiguration.class})
public class NutzDatabaseInitializer {

    @Resource
    private Dao dao;

    @Resource
    private SpringResourceLoaction springResourceLoaction;

    @PostConstruct
    public void Initializer() {
        boolean create = true;
        boolean migration = true;
        List<String> basepackageList = new ArrayList<>();
        basepackageList.add("com.yunqi");
        basepackageList.add("BOOT-INF.classes.com.yunqi");
        String[] arr = basepackageList.toArray(new String[basepackageList.size()]);
        Arrays.stream(arr).forEach(pkg -> {
            if (create) {
                Daos.createTablesInPackage(dao, pkg, false);
            }
            if (migration) {
                Daos.migration(dao, pkg, true, false, false);
            }
        });
        Scans.me().addResourceLocation(springResourceLoaction);
    }

}
