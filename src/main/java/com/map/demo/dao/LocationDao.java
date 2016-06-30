package com.map.demo.dao;

import com.map.demo.bean.Location;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sdyang on 2016/6/30.
 */
public interface LocationDao extends CrudRepository<Location,Long> {
}
