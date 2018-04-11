package pri.ryan.controller;

import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestController implements HttpRequestHandler{

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        /*数据对象*/
        /*一般由Service层返回数据对象，此为最简单案例*/
        List list = new ArrayList();
        /*设置数据*/
        httpServletRequest.setAttribute("Object",list);
        /*转发视图*/
        httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/simple.jsp").forward(httpServletRequest,httpServletResponse);

    }
}
