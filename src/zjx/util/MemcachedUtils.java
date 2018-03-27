package zjx.util;

import com.danga.MemCached.MemCachedClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

/**
 * @author zjx
 * @Title:
 * @Package
 * @Description:
 * @date 2018/3/2614:20
 */
public class MemcachedUtils {
    private static MemCachedClient cachedClient;

    static {
        if(cachedClient==null){
            cachedClient = new MemCachedClient("memcachedPool");
        }
    }
    public MemcachedUtils(){

    }
    public static boolean set(String key,Object value){
        return setExp(key,value,null);
    }
    public static boolean set(String key,Object value,Date expise){
        return setExp(key,value,expise);
    }
    private static boolean setExp(String key,Object value,Date expise){
        boolean flag = false;
        try {
            flag = cachedClient.set(key, value, expise);
        }catch (Exception e){
            //MemcachedLog.writeLog();
            e.printStackTrace();
        }
        return flag;
    }
    public static boolean add(String key,String value){
        return addExp(key,value,null);
    }
    public static boolean add(String key,String value,Date expise){
        return addExp(key,value,expise);
    }
    private static boolean addExp(String key,String value,Date expise){
        boolean flag = false;
        try {
            flag = cachedClient.add(key, value, expise);
        }catch (Exception e){
            //MemcachedLog.writeLog();
        }
        return flag;
    }
    public static boolean replace(String key,String value){
        return replaceExp(key,value,null);
    }
    public static boolean replace(String key,String value,Date expise){
        return replaceExp(key,value,expise);
    }
    private static boolean replaceExp(String key,String value,Date expise){
        boolean flag = false;
        try {
            flag = cachedClient.replace(key, value, expise);
        }catch (Exception e){
            //MemcachedLog.writeLog();
        }
        return flag;
    }
    public static Object get(String key) {
        Object obj = null;
        try {
            obj = cachedClient.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            //MemcachedLog.writeLog("Memcached get方法报错，key值：" + key + "\r\n" + exceptionWrite(e));
        }
        return obj;
    }
    public static boolean delete(String key){
        return deleteExp(key,null);
    }
    public static boolean delete(String key,Date expise){
        return deleteExp(key,expise);
    }
    private static boolean deleteExp(String key,Date expise){
        boolean flag = false;
        try {
            flag = cachedClient.delete(key, expise);
        }catch (Exception e){
            //MemcachedLog.writeLog();
        }
        return flag;
    }
    public static boolean flashAll() {
        boolean flag = false;
        try {
            flag = cachedClient.flushAll();
        } catch (Exception e) {
            //MemcachedLog.writeLog("Memcached flashAll方法报错\r\n" + exceptionWrite(e));
        }
        return flag;
    }
    private static String exceptionWrite(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }
}
