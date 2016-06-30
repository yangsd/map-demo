package com.map.demo.service;

import com.map.demo.bean.Location;
import com.map.demo.dao.LocationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sdyang on 2016/6/30.
 */
@Service
public class LocationService {

    @Autowired
    private LocationDao locationDao;

    public List<Location> findAll(){
        return  (List<Location>)locationDao.findAll();
    }


}
