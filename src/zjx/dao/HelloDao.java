package zjx.dao;

import zjx.entry.HelloEntry;

import java.util.Map;

/**
 * @author zjx
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/3/2310:38
 */
public interface HelloDao {
    HelloEntry getOneById(Map params);
}
