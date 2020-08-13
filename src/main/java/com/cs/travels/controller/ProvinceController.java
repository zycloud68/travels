package com.cs.travels.controller;

import com.cs.travels.entity.Province;
import com.cs.travels.entity.Result;
import com.cs.travels.service.ProvinceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin // 进行跨域选择
@RequestMapping("province")
@Slf4j
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    /**
     * 添加省份信息
     * @param province
     * @return
     */
    @RequestMapping("add")
    public Result add(@RequestBody Province province){
        Result result = new Result();
        try {
            provinceService.add(province);
            result.setMsg("保存省份信息成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg("保存省份信息失败！");
        }
        return result;
    }

    /**
     * 更新省份信息
     * @param province
     * @return
     */
    @RequestMapping("update")
    public Result update(@RequestBody Province province){
        Result result = new Result();
        try {
            provinceService.update(province);
            result.setMsg("更新省份信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg("更新省份信息失败，请重试！");
        }
        return  result;
    }

    /**
     * 删除省份信息
     * @param id
     * @return
     */
    @RequestMapping("delete")
    public Result delete(String id){
        Result result = new Result();
        try {
            provinceService.delete(id);
            result.setMsg("删除省份信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg("删除省份信息失败");
        }
        return  result;
    }

    /**
     * 查询一个省份信息
     * @param id
     * @return
     */
     @RequestMapping("findOne")
     public Province findOne(String id){
        return provinceService.findOne(id);
     }


    /**
     * 查询所有省份信息
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("findByPage")
    public Map<String,Object> findByPage(Integer page,Integer rows){
        HashMap<String,Object> map  = new HashMap<>();
        // 当省份信息只有一页或者为空的时候
        page = page == null ? 1: page;
        rows = rows == null ? 5: rows;
        //分页处理
        List<Province> provinces = provinceService.findByPage(page,rows);
        // 计算总页数
        Integer totals = provinceService.findTotals();
        Integer totalPage = totals % rows == 0 ? totals / rows : totals / rows + 1;
        map.put("provinces",provinces);
        map.put("totals",totals);
        map.put("totalPage",totalPage);
        // 还需要把页数也分别返回给前端
        map.put("page",page);
        return map;
    }

}
