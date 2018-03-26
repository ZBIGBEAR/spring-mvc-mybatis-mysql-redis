package zjx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPoolConfig;

import java.io.Serializable;

/**
 * @author zjx
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/3/2319:25
 */
@Service
public class RedisService {
    @Autowired
    JedisPoolConfig poolConfig;
    @Autowired
    private RedisTemplate<Serializable, Object> redisTemplate;
    public RedisService(){
        /*
        try {
            JedisConnectionFactory conn = new JedisConnectionFactory();
            conn.setHostName("127.0.0.1");
            conn.setPort(6379);
            conn.setDatabase(0);
            conn.setPoolConfig(poolConfig);
            redisTemplate = new RedisTemplate();
            redisTemplate.setConnectionFactory(conn);
        }catch(Exception e){
            System.out.println("===============");
            //e.printStackTrace();
        }*/
    }

    public void remove(String key){
        redisTemplate.delete(key);
    }
    public void removes(String ...keys){
        for(String key:keys){
            remove(key);
        }
    }
    public boolean set(String key,Object value){
        ValueOperations<Serializable,Object> operations = redisTemplate.opsForValue();
        operations.set(key,value);
        return true;
    }
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }
}
