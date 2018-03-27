package zjx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zjx.util.MemcachedUtils;

import java.util.Date;

/**
 * @author zjx
 * @Title:
 * @Package
 * @Description:
 * @date 2018/3/2614:38
 */
@Service
public class MemCachedService {

    public MemcachedUtils memCachedClient = new MemcachedUtils();
    public Object get(String key){
        return memCachedClient.get(key);
    }
    public boolean set(String key,Object value){
        return memCachedClient.set(key,value);
    }
    public boolean set(String key,Object value,Date expise){
        return memCachedClient.set(key,value,expise);
    }
}
