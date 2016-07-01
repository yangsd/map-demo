package com.map.demo.service;

import com.map.demo.bean.Location;
import com.map.demo.dao.LocationDao;
import com.map.demo.util.JsonUtil;
import com.map.demo.util.LocalHttpClient;
import com.map.demo.vo.ReverseResultVo;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
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

    protected static Header jsonHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());

    public ReverseResultVo  geocoding() throws IOException {
        HttpUriRequest httpUriRequest = RequestBuilder.get()
                .setHeader(jsonHeader)
                .setUri("http://api.map.baidu.com/geocoder/v2/")
                .addParameter("ak", "5d5367903660d715ec23ffe0")
                .addParameter("callback","renderReverse")
                .addParameter("location","39.983424,116.322987")
                .addParameter("output","json")
                .addParameter("pois","1")
                .build();
        CloseableHttpResponse response =  LocalHttpClient.execute(httpUriRequest);
        HttpEntity entity = response.getEntity();
        String json = EntityUtils.toString(entity);
        json = json.substring(json.indexOf("{"),json.length()-1);//剔除无效字符

        ReverseResultVo result = JsonUtil.parseObject(json,ReverseResultVo.class);
        return  result;
    }
}
