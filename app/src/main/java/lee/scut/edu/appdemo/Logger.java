package lee.scut.edu.appdemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhengxian.lzx@alibaba-inc.com on 2016/6/29.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Logger {
    int flag() ;
    String type();
}
