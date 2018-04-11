##SpringMVC配置
####DispatcherServlet—前端控制器
```java
<!--WEB-INF/web.xml-->
<!--spring前端控制器-->
<servlet>
  <servlet-name>springmvc</servlet-name>
  <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  <!--如果不配置，默认加载WEB-INF/${servlet-name}-servlet.xml-->
  <init-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:spring-mvc.xml</param-value>
  </init-param>
</servlet>
<!--spring前端控制器映射方式-->
<servlet-mapping>
  <servlet-name>springmvc</servlet-name>
  <url-pattern>*.do</url-pattern>
</servlet-mapping>
```
####HandlerAdapter—处理器适配器
```java
<!--resources/springmvc.xml-->
<!--处理器适配器-->
    <bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"/>
```
>处理器适配器如何判断可以支持?

```java
<!--SimpleControllerHandlerAdapter.class-->
public class SimpleControllerHandlerAdapter implements HandlerAdapter {
    public SimpleControllerHandlerAdapter() {
    }

    public boolean supports(Object handler) {
        return handler instanceof Controller;
    }

    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return ((Controller)handler).handleRequest(request, response);
    }

    public long getLastModified(HttpServletRequest request, Object handler) {
        return handler instanceof LastModified ? ((LastModified)handler).getLastModified(request) : -1L;
    }
}
```
可以看到如果处理器是一个Controller就支持
```java
public boolean supports(Object handler) {
    return handler instanceof Controller;
}
```
```java
<!--Controller.class-->
public interface Controller {
    ModelAndView handleRequest(HttpServletRequest var1, HttpServletResponse var2) throws Exception;
}
```
#####Controller—控制层编写
```java
public class SimpleController implements Controller{
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        /*数据对象*/
        /*一般由Service层返回数据对象，此为最简单案例*/
        List list = new ArrayList();
        /*获取ModelAndView*/
        ModelAndView modelAndView = new ModelAndView();
        /*添加需要返回的数据对象*/
        modelAndView.addObject("Object",list);
        /*制定仕途*/
        modelAndView.setViewName("/WEB-INF/jsp/simple.jsp");
        return modelAndView;
    }
}
```
####Handler—
