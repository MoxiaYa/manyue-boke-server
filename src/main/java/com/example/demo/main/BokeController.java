package com.example.demo.main;

import com.example.demo.bean.Boke;
import com.example.demo.bean.Discuss;
import com.example.demo.service.BokesService;
import com.example.demo.service.DiscussService;
import com.example.demo.utils.AccountLoginToken;
import com.example.demo.utils.M;
import com.example.demo.utils.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/admin/boke/")
/**
 * 后台博客相关的请求
 */
public class BokeController {
    @Autowired
    BokesService bs;

    @Autowired
    DiscussService ds;

    /**
     * 根据博客查找评论列表
     * @return
     */
    @UserLoginToken
    @ResponseBody
    @RequestMapping(path={"/selDisByBoke"},method = {RequestMethod.GET})
    public M selDisByBoke(@RequestParam("id") Integer id){
        return ds.selByBoke(id);
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    @UserLoginToken
    @ResponseBody
    @RequestMapping(path={"/delDis"},method = {RequestMethod.GET})
    public M delDiscuss(@RequestParam("id") Integer id){
        return ds.delDiscuss(id);
    }

    /**
     * 作者发表评论
     * @param dis
     * @return
     */
    @UserLoginToken
    @ResponseBody
    @RequestMapping(path={"/addDis"},method = {RequestMethod.POST})
    public M addDiscuss(@RequestBody Discuss dis){
        return ds.addDiscuss(dis);
    }

    /**
     * 获取所有博客
     * @param request
     * @return
     */
    @UserLoginToken
    @ResponseBody
    @RequestMapping(path={"/sel"},method = {RequestMethod.GET})
    public M selAllBoke(HttpServletRequest request){
        String userID = (String)request.getAttribute("userID");
        System.out.println("收到"+userID+"的查询所有博客请求");
        return M.ok(bs.SelAll());
    }

    /**
     * 添加博客
     * @param boke
     * @return
     */
    @UserLoginToken
    @ResponseBody
    @RequestMapping(path={"/add"},method = {RequestMethod.POST})
    public M addBoke(@RequestBody Boke boke){
        System.out.println("收到添加博客请求:"+boke);
        boke.setCreate_time(getStringDateShort());
        return bs.add(boke);
    }


    /**
     * 批量删除
     * @param ids
     * @return
     */
    @UserLoginToken
    @ResponseBody
    @RequestMapping(path={"/delMore"},method = {RequestMethod.POST})
    public M delBoke(@RequestBody List<Integer> ids){
        return bs.delMore(ids);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @UserLoginToken
    @ResponseBody
    @RequestMapping(path={"/del"},method = {RequestMethod.GET})
    public M del(@RequestParam("id") Integer id){
        return bs.del(id);
    }

    @UserLoginToken
    @ResponseBody
    @RequestMapping(path={"/editEnabled"},method = {RequestMethod.GET})
    public M editEnabled(@RequestParam("id") Integer id,@RequestParam("is_enabled") Integer is_enabled){
        return bs.editEnabled(id,is_enabled);
    }

    @UserLoginToken
    @ResponseBody
    @RequestMapping(path={"/update"},method = {RequestMethod.POST})
    public M update(@RequestBody Boke boke){
        return bs.update(boke);
    }
    @UserLoginToken
    @ResponseBody
    @RequestMapping(path={"/selByTitle"},method = {RequestMethod.GET})
    public M selByTitle(@RequestParam("title") String title){
        return bs.selByTitle(title);
    }

    @UserLoginToken
    @ResponseBody
    @RequestMapping(path={"/selById"},method = {RequestMethod.GET})
    public M selById(@RequestParam("id") Integer id){
        return bs.selById(id);
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
