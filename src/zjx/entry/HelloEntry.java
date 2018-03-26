package zjx.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zjx
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/3/2310:38
 */
public class HelloEntry implements Serializable {
    private int id;
    private String name;
    private int age;
    private Date date;
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return this.age;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public Date getDate(){
        return this.date;
    }
}
