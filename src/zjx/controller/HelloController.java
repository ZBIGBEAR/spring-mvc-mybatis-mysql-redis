package zjx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import zjx.entry.HelloEntry;
import zjx.service.HelloService;
import zjx.service.MemCachedService;
import zjx.service.RedisService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by admin on 2018/3/9.
 */
@Controller
public class HelloController {
    @Resource
    private HelloService helloService;
    @Resource
    private RedisService redisService;
    @Resource
    private MemCachedService memCachedService;
    @RequestMapping(value = "hello")
    public ModelAndView  hello(int id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        //从redis中获取当前ID对应的对象
        HelloEntry hello = (HelloEntry) redisService.get(id+"");
        //如果不存在，则从数据库中获取
        if(hello==null) {
            hello = helloService.getOneById(id);
            //将当前对象存到redis
            redisService.set(id+"",hello);
        }
        //将当前ID保存到redis
        redisService.set("id",id);
        mv.addObject("hello",hello);
        return mv;
    }
    @RequestMapping(value = "testMemCached")
    public ModelAndView  memCached() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("memCached");
        Object id = redisService.get("id");
        String curId="1";
        if(id==null || "".equals(id)){
            curId="1";
        }else{
            curId = id.toString();
        }
        //先从memcached中获取当前ID对应的对象
        HelloEntry hello = (HelloEntry)memCachedService.get(curId);
        if(hello==null){//如果memchaed中没有，则从redis中获取
            hello = (HelloEntry) redisService.get(curId);
            if (hello == null) {//如果redis中没有则从数据库中获取
                hello = helloService.getOneById(Integer.parseInt(curId));
                redisService.set(curId,hello);//保存到redis
            }
            memCachedService.set(curId,hello);//保存到memcached
        }
        mv.addObject("hello",hello);
        return mv;
    }
}
