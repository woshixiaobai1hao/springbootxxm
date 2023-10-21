package com.yh.service.impl;

import com.yh.mapper.TravellerMapper;
import com.yh.model.Traveller;
import com.yh.service.TravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TravellerServiceImpl implements TravellerService {
    @Autowired
    private TravellerMapper travellerMapper;

    @Override
    public List<Traveller> findAll() {
        return  travellerMapper.findAll();
    }
}
