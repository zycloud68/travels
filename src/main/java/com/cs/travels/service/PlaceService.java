package com.cs.travels.service;

import com.cs.travels.entity.Place;

import java.util.List;

public interface PlaceService {
    // 根据id来查询省份信息
   List<Place> findByProvinceIdPage (Integer page,Integer rows, String provinceId);

   // 根据id来查询所有的景点总数
    Integer findByProvinceIdCounts( String provinceId);

    // 添加景点信息
    void add(Place place);

    // 查询景点信息
    Place findOne (String id);

    //更新景点信息
    void update(Place place);

    // 删除景点信息
    void delete(String id);

}
