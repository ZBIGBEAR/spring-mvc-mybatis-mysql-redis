package zjx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import zjx.entry.HelloEntry;
import zjx.service.HelloService;
import zjx.service.RedisService;

import javax.annotation.Resource;

/**
 * Created by admin on 2018/3/9.
 */
@Controller
public class HelloController {
    @Resource
    private HelloService helloService;
    @Resource
    private RedisService redisService;
    @RequestMapping(value = "hello")
    public ModelAndView  hello(int id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hello");
        mv.addObject("msg","welcome");
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
}
