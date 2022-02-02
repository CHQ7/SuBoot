package com.yunqi.starter.redis.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.List;

/**
 * Created by @author JsckChin on 2022/2/2
 */
@Data
@ConfigurationProperties(prefix = "su.redis")
public class RedisAutoConfigurationProperties {

    /**
     * 是否启动
     */
    boolean enabled = true;

    /**
     * 连接工厂使用的数据库索引
     */
    private int database = 0;

    /**
     * 连接URL。覆盖主机、端口和密码。用户被忽略。例子：
     * redis://user:password@example.com:6379
     */
    private String url;

    /**
     * Redis服务器主机
     */
    private String host = "localhost";

    /**
     * redis服务器的登录用户名.
     */
    private String username;

    /**
     * redis服务器的登录密码.
     */
    private String password;

    /**
     * Redis服务器端口.
     */
    private int port = 6379;

    /**
     * 是否启用SSL支持.
     */
    private boolean ssl;

    /**
     * 读取超时.
     */
    private Duration timeout;

    /**
     * 连接超时.
     */
    private Duration connectTimeout;

    /**
     * 要在具有客户端SETNAME的连接上设置的客户端名称.
     */
    private String clientName;

    /**
     * 要使用的客户端类型。默认情况下，根据类路径自动检测.
     */
    private ClientType clientType;

    private Sentinel sentinel;

    private Cluster cluster;

    private final Jedis jedis = new Jedis();

    private final Lettuce lettuce = new Lettuce();

    /**
     * 要使用的Redis客户端的类型
     */
    public enum ClientType {

        /**
         * 使用redis客户端.
         */
        LETTUCE,

        /**
         * 使用JEDIS客户端。
         */
        JEDIS

    }

    /**
     * Pool properties.
     */
    @Data
    public static class Pool {

        /**
         * Whether to enable the pool. Enabled automatically if "commons-pool2" is
         * available. With Jedis, pooling is implicitly enabled in sentinel mode and this
         * setting only applies to single node setup.
         */
        private Boolean enabled;

        /**
         * Maximum number of "idle" connections in the pool. Use a negative value to
         * indicate an unlimited number of idle connections.
         */
        private int maxIdle = 8;

        /**
         * Target for the minimum number of idle connections to maintain in the pool. This
         * setting only has an effect if both it and time between eviction runs are
         * positive.
         */
        private int minIdle = 0;

        /**
         * Maximum number of connections that can be allocated by the pool at a given
         * time. Use a negative value for no limit.
         */
        private int maxActive = 8;

        /**
         * Maximum amount of time a connection allocation should block before throwing an
         * exception when the pool is exhausted. Use a negative value to block
         * indefinitely.
         */
        private Duration maxWait = Duration.ofMillis(-1);

        /**
         * Time between runs of the idle object evictor thread. When positive, the idle
         * object evictor thread starts, otherwise no idle object eviction is performed.
         */
        private Duration timeBetweenEvictionRuns;

    }

    /**
     * 群集属性.
     */
    @Data
    public static class Cluster {

        /**
         * 要从中引导的“主机：端口”对的逗号分隔列表。这代表着一种
         * 群集节点的“初始”列表，需要至少有一个条目
         */
        private List<String> nodes;

        /**
         * 在整个系统中执行命令时要遵循的最大重定向数
         * 集群.
         */
        private Integer maxRedirects;
    }

    /**
     * Redis 哨兵属性.
     */
    @Data
    public static class Sentinel {

        /**
         * Redis服务器的名称.
         */
        private String master;

        /**
         * 以逗号分隔的“主机：端口”对列表.
         */
        private List<String> nodes;

        /**
         * 用于通过sentinel进行身份验证的密码.
         */
        private String password;

    }

    /**
     * Jedis client properties.
     */
    @Data
    public static class Jedis {

        /**
         * Jedis pool configuration.
         */
        private final Pool pool = new Pool();
    }

    /**
     * Lettuce client properties.
     */
    @Data
    public static class Lettuce {

        /**
         * Shutdown timeout.
         */
        private Duration shutdownTimeout = Duration.ofMillis(100);

        /**
         * Lettuce pool configuration.
         */
        private final Pool pool = new Pool();

        private final Lettuce.Cluster cluster = new Lettuce.Cluster();


        public static class Cluster {

            private final Lettuce.Cluster.Refresh refresh = new Lettuce.Cluster.Refresh();

            public Lettuce.Cluster.Refresh getRefresh() {
                return this.refresh;
            }

            @Data
            public static class Refresh {

                /**
                 * Whether to discover and query all cluster nodes for obtaining the
                 * cluster topology. When set to false, only the initial seed nodes are
                 * used as sources for topology discovery.
                 */
                private boolean dynamicRefreshSources = true;

                /**
                 * Cluster topology refresh period.
                 */
                private Duration period;

                /**
                 * Whether adaptive topology refreshing using all available refresh
                 * triggers should be used.
                 */
                private boolean adaptive;


            }

        }

    }

}
