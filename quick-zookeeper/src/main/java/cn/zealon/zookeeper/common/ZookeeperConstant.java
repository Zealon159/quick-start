package cn.zealon.zookeeper.common;

/**
 * @auther: Zealon
 * @Date: 2018-06-22 10:33
 */
public class ZookeeperConstant {

    //zk服务器连接地址
    public static final String CONNECT_ADDR = "47.104.241.41:2181";

    public static final String ROOT_PATH = "/";

    public static final String CHARSET = "UTF-8";

    public static final String EMPTY_STRING = "EMPTY";

    //心跳检测时间周期（毫秒）
    public static final Integer BASE_SLEEP_TIME_MS = 1000;

    public static final Integer MAX_RETRIES = 6;

    public static final Integer ONE = 1;

    public static final String DEFAULT_PAGE_NUMBER = "1";

    public static final Integer DEFAULT_PAGE_NUMBER_INTEGER = 1;

    public static final String DEFAULT_PAGE_SIZE = "10";
}
