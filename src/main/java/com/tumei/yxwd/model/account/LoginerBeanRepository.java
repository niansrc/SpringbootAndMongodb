package com.tumei.yxwd.model.account;
import javafx.util.converter.DateStringConverter;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoDataIntegrityViolationException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by niannian on 2017/2/15.
 */
//public interface LoginerBeanRepository extends MongoRepository<LoginerBean,Long>{
//    LoginerBean findByAccount(String account);
//    List<LoginerBean> findAll();
////    LoginerBean findById(Integer id);
//    LoginerBean findByDigest(String digest);
//}
@Repository
public class LoginerBeanRepository {
    @Qualifier("yxwdaccountMongoTemplate")
    @Autowired
    public MongoTemplate mt;
    public List<LoginerBean> findByCreatetime(String start,String end) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date date=sdf.parse(start);
            Date date2=sdf.parse(end);
            List<LoginerBean> re = mt.find(query(where("createtime").gt(date).lt(date2)), LoginerBean.class);
            return re;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

