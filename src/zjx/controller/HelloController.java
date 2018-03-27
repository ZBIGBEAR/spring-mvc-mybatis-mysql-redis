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
        mv.addObject("msg","welcome,world");
        HelloEntry hello = (HelloEntry) redisService.get(id+"");
        if(hello==null) {
            hello = helloService.getOneById(id);
        }

        mv.addObject("hello",hello);
        redisService.set("curId",id);
        redisService.set(id+"",hello);
        return mv;
    }
    @RequestMapping(value = "hello1")
    public ModelAndView  hello1() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        Object id = redisService.get("curId");
        if(id!=null) {
            HelloEntry hello = (HelloEntry) redisService.get(id.toString());
            if(hello!=null) {
                mv.addObject("msg", hello.getName());
                mv.addObject("hello", hello);
            }
        }
        return mv;
    }
    @RequestMapping(value="testMemCached")
    private ModelAndView memCached(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("memCached");
        //从memcached中获取姓名
        Object name =memCachedService.get("name");
        String id = redisService.get("curId").toString();
        HelloEntry hello = (HelloEntry)memCachedService.get(id.toString());
        if(hello==null){
            hello = (HelloEntry) redisService.get(id.toString());
            if (hello == null) {
                hello = helloService.getOneById(Integer.parseInt(id.toString()));
            }
            memCachedService.set(id,hello);
            mv.addObject("hello",null);
        }else{
            mv.addObject("hello",hello);
        }
        if(name==null){
            mv.addObject("name","测试String：memcached为空");
            memCachedService.set("name",hello.getName());
        }else{
            mv.addObject("name","测试String："+name.toString());
        }
        return mv;
    }
}
