package com.cs.travels.controller;

import com.cs.travels.entity.Place;
import com.cs.travels.entity.Result;
import com.cs.travels.service.PlaceService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin  // 进行跨域操作
@RequestMapping("place")
public class PlaceController {

    @Value("${upload.dir}")

    private  String realPath;

    @Autowired
    private PlaceService placeService;

    /**
     * 获取所有的景点列表
     * @param page
     * @param rows
     * @param provinceId
     * @return
     */
    @GetMapping("findAllPlace")
    public Map<String,Object> findAllPlace(Integer page,Integer rows,String provinceId){
        HashMap<String ,Object> map = new HashMap<>();
        // 当景点信息只有一页或者为空的时候
        page = page == null ? 1 : page;
        rows = rows == null ? 3 : rows;
        // 景点集合
        List<Place> places = placeService.findByProvinceIdPage(page,rows,provinceId);
        // 处理分页信息
        Integer counts = placeService.findByProvinceIdCounts(provinceId);
        // 总页数信息的展示
        Integer totalPage  = counts % rows == 0 ? counts / rows : counts / rows +1;
        map.put("places",places);
        map.put("page",page);
        map.put("counts",counts);
        map.put("totalPage",totalPage);
        return map;
    }

    /**
     * 查询景点信息
     * @param id
     * @return
     */
    @RequestMapping("findOne")
    public Place findOne(String id){
        return placeService.findOne(id);
    }

    /**
     * 增加景点信息
     * @param pic
     * @param place
     * @return
     * @throws IOException
     */
    @RequestMapping("add")
    public Result add(MultipartFile pic,Place place) throws IOException {
        Result result = new Result();
        try {
            // 对文件进行上传处理
            String extension  =  FilenameUtils.getExtension(pic.getOriginalFilename());
            // 对文件进行重新命名
            String newFileName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+extension;
            // 对上传的图片进行base64编码处理
            place.setPicpath(Base64Utils.encodeToString(pic.getBytes()));
            // 进行文件上传处理
            File file = new File(realPath);
            pic.transferTo(new File(file,newFileName));
            // 保存景点place信息
            placeService.add(place);
            result.setMsg("添加景点信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg("添加景点信息失败");
        }
        return  result;
    }

    @RequestMapping("delete")
    public Result delete(String id){
        Result result = new Result();
        try {
            placeService.delete(id);
             result.setState(true).setMsg("删除景点信息成功");
        } catch (Exception e) {
            e.printStackTrace();
             result.setState(false).setMsg("删除景点信息失败，请重试！");
        }
        return result;
    }

    /**
     * 更新景点信息
     * @param pic
     * @param place
     * @return
     * @throws IOException
     */
    @RequestMapping("update")
    public Result update(MultipartFile pic,Place place) throws IOException {
        Result result = new Result();
        try {
            String picPath = Base64Utils.encodeToString(pic.getBytes());
            place.setPicpath(picPath);
            // 对文件进行处理
            String extension  =  FilenameUtils.getExtension(pic.getOriginalFilename());
            // 对文件进行重新命名
            String newFileName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+extension;
            File file = new File(realPath);
            pic.transferTo(new File(file,newFileName));
            // 修改文件信息
            placeService.update(place);
            result.setState(true).setMsg("景点信息修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(false).setMsg("修改失败，请重试！");
        }
        return result;
    }
}
