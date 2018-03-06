package me.zji.web;

import com.alibaba.fastjson.JSON;
import me.zji.constants.CommonConstants;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 控制器异常处理类
 * Created by imyu on 2017/3/19.
 */
@ControllerAdvice
public class AppWideExceptionHandler {
    /**
     * 全部请求的未处理异常，统一json格式输出提示
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException() {
        Map model = new HashMap();
        model.put("resultCode", CommonConstants.RESULT_FAILURE);
        model.put("errorInfo", "服务器开小差了");
        String jsonString = JSON.toJSONString(model);
        return jsonString;
    }
}
