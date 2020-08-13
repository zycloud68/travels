package com.cs.travels.service;

import com.cs.travels.entity.Province;
import com.cs.travels.mapper.ProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProvinceServiceImpl implements  ProvinceService{

    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public List<Province> findByPage(Integer page, Integer rows) {
        int start = (page-1)*rows;
        return  provinceMapper.findByPage(start,rows);
    }

    @Override
    public Integer findTotals() {

        return provinceMapper.findTotals();
    }

    @Override
    public void delete(String id) {
        provinceMapper.deleteProvinceInfo(id);

    }

    @Override
    public Province findOne(String id) {
        return provinceMapper.findOne(id);
    }

    @Override
    public void add(Province province) {
        // 首先我们先设置省份中景点个数为0
        province.setPlacecounts(0);
        provinceMapper.add(province);
    }

    @Override
    public void update(Province province) {
        provinceMapper.updateProvinceInfo(province);
    }
}
