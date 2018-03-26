package zjx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zjx.dao.HelloDao;
import zjx.entry.HelloEntry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zjx
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/3/2310:35
 */
@Service
public class HelloService {
    @Autowired
    private HelloDao helloDao;
    public HelloEntry getOneById(int id){
        Map<String,Integer> parms = new HashMap<>();
        parms.put("id",id);
        return helloDao.getOneById(parms);
    }
}
