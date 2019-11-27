package com.example.demo.main;

import com.example.demo.utils.M;
import com.example.demo.utils.PassToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/upload/")
public class FileController {
    @PassToken
    @RequestMapping("/img")
    @ResponseBody
    public M uploadImg(@RequestParam("file") MultipartFile file){
        System.out.println(1);
        String file_address = "";
        if(!file.isEmpty()) {
            try {
                /*
                 * 这段代码执行完毕之后，图片上传到了工程的跟路径； 大家自己扩散下思维，如果我们想把图片上传到
                 * d:/files大家是否能实现呢？ 等等;
                 * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如： 1、文件路径； 2、文件名；
                 * 3、文件格式; 4、文件大小的限制;
                 */
                //String base = "http://www.manyuem.cn:81/file_server/img/";
                String base = "Z:\\项目\\漫悦博客\\boke\\img-server\\";
                //String filepath = "C:\\inetpub\\wwwroot\\file_server\\img\\";
                String filepath = "Z:\\项目\\漫悦博客\\boke\\img-server\\";
                @SuppressWarnings("static-access")
                String k =new String().valueOf((int)((Math.random()*9+1)*100000));
                String newfilename = System.currentTimeMillis() / 1000+"_"+ k + ".";
                String fileName = file.getOriginalFilename();

                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);//文件后缀
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(filepath+newfilename+suffix)));
                file_address = base+newfilename+suffix;
                out.write(file.getBytes());
                out.flush();
                out.close();
            }catch(FileNotFoundException e) {
                e.printStackTrace();

                return M.error(500,"500");
            } catch (IOException e) {
                e.printStackTrace();

                return M.error(500,"500");
            }
        }else {

            return M.error(500,"500");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("img", file_address);
        return M.ok(map);

    }
}
