package cn.fishei.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseController extends HttpServlet {

    /**
     * 直接将传入的对象序列化json，并传回客户端
     * @param obj
     */
    public void writeValue(Object obj, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), obj);
    }

    /**
     * 将传入的对象序列化JSON后，返回方法的调用者
     * @param obj
     * @param response
     * @return
     * @throws JsonProcessingException
     */
    public String writeValueAsString(Object obj, HttpServletResponse response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}
