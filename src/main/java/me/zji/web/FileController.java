package me.zji.web;

import me.zji.constants.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * 文件上传
 * Created by qian yun on 2018-03-07.
 */
@Controller
public class FileController {
    @Autowired
    @Qualifier("applicationProperties")
    Properties properties;

    @RequestMapping("/file/upload")
    @ResponseBody
    public Map upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String fileId = null;
        MultipartFile file = null;
        InputStream in = null;
        OutputStream out = null;
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator iter = multiRequest.getFileNames();
            Map param = new HashMap();
            // 默认图片只能一个一个上传
            if (iter.hasNext()) {
                try {
                    file = multiRequest.getFile(iter.next().toString());
                    if (file != null) {
                        in = file.getInputStream();
                        fileId = uuid + getSuffix(file.getOriginalFilename());
                        out = new FileOutputStream(getFile(fileId, properties.getProperty("file.path")));
                        byte[] buffer = new byte[10240];
                        int size = 0;
                        while ((size = in.read(buffer)) != -1) {
                            out.write(buffer, 0, size);
                        }
                    }
                } catch (IOException e) {
                    // 忽略
                } finally {
                    if (in != null) {
                        in.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                }
            }
        }
        Map map = new HashMap();
        map.put("resultCode", CommonConstants.RESULT_SUCEESS);
        map.put("data", fileId);
        return map;
    }

    private String getSuffix(String name) {
        return name.substring(name.lastIndexOf("."), name.length());
    }

    private File getFile(String fileName, String path) {
        String previewFilePath = path;
        File file = new File(previewFilePath + fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
            }
        }
        return file;
    }
}
