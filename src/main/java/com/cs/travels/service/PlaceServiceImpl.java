package com.cs.travels.service;

import com.cs.travels.entity.Place;
import com.cs.travels.entity.Province;
import com.cs.travels.mapper.PlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceMapper placeMapper;
    @Autowired
    private  ProvinceService provinceService;
    @Override
    public List<Place> findByProvinceIdPage(Integer page, Integer rows, String provinceId) {
        int start = (page-1) * rows;
        return placeMapper.findByProvinceIdPage(start,rows,provinceId);

    }

    @Override
    public Integer findByProvinceIdCounts(String provinceId) {

        return placeMapper.findByProvinceIdCounts(provinceId);
    }

    /**
     * 添加景点信息业务层逻辑
     * @param place
     */
    @Override
    public void add(Place place) {
      // 保存景点
        placeMapper.add(place);
        // 查询原始省份信息
        Province province = provinceService.findOne(place.getProvinceid());
        // 更新景点个数
        province.setPlacecounts(province.getPlacecounts()+1);  // 在原有景点的个数上面加1
        provinceService.update(province);
    }

    /**
     * 查找单条景点信息
     * @param id
     * @return
     */
    @Override
    public Place findOne(String id) {
        return placeMapper.findOne(id);
    }

    /**
     * 更新景点信息
     * @param place
     */
    @Override
    public void update(Place place) {
         placeMapper.updateProvinceInfo(place);
    }

    /**
     * 根据id来删除景点信息
     * @param id
     */
    @Override
    public void delete(String id) {
        placeMapper.deleteProvinceInfo(id);
    }
}
