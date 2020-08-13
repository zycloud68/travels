package com.cs.travels.mapper;

import com.cs.travels.entity.Place;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface PlaceMapper extends BaseMapper<Place, String> {
    List<Place> findByProvinceIdPage(@Param("start") Integer start, @Param("rows") Integer rows, @Param("provinceId") String provinceId);

    Integer findByProvinceIdCounts(@Param("provinceId") String provinceId);
}
