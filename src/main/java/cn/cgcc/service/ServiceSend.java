package cn.cgcc.service;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;

/**
 * https://ares-monitor-api.prcdn.pyfeng.com/api/v1/alarm/data_sync/influxdb?
 * token=fsderbtrhrge&user=monitor&format=pretty&metric=GDCDN_DB_INFO&
 * query=SELECT mean("BusinessDataTableSpace") as BusinessDataTableSpace
 * FROM "GDCDN_DB_INFO" WHERE "type" = 'diskused' AND "user" = '/data/mysql/data' GROUP BY time(5m) order by time desc limit 2
 */
@Service
public class ServiceSend {

    private static final Logger logger = LoggerFactory.getLogger(ServiceSend.class);

    private String token = "fsderbtrhrge";
    private String user = "monitor";
    private String format = "pretty";
    private String metric;
    private String query;

    @Autowired
    private HttpClient client;

    public String send(String name) {
        try {
            if (name.equals("BusinessSystemDBTime")) {
                query = String.format("SELECT mean(\"%s\") as %s FROM \"GDCDN_DB_INFO\" WHERE \"type\" = 'sysinfo'   GROUP BY time(5m)  order by time desc limit 2", name, name);
                query = URLEncoder.encode(query);
                metric = "GDCDN_DB_INFO";
            } else if (name.equals("BusinessDataTableSpace")) {
                query =  String.format("SELECT mean(\"%s\") as %s FROM \"GDCDN_DB_INFO\" WHERE \"type\" = 'diskused' AND \"user\" = '/data/mysql/data'  GROUP BY time(5m) order by time desc limit 2",name,name);
                query = URLEncoder.encode(query);
                metric = "GDCDN_DB_INFO";
            } else if (name.equals("BusinessSystemRunningTime")) {
                query =  String.format("SELECT mean(\"%s\") as %s FROM \"GDSCDN_SERVER_CHECK\" GROUP BY time(5m) order by time desc limit 2",name,name);
                query = URLEncoder.encode(query);
                metric = "GDSCDN_SERVER_CHECK";
            }
            String url =
                    String.format("https://ares-monitor-api.prcdn.pyfeng.com/api/v1/alarm/data_sync/influxdb?token=%s&user=%s&format=%s&metric=%s&query=%s"
                            ,token,user,format,metric,query);

            logger.info("url: " + url);
            String res = client.doGet(url);
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String esQuery(String name) {
        try {
            if (name.equals("BusinessUserRegNum")) {
                query = String.format("_count?pretty");
                String url = String.format("http://172.18.9.176:9200/nginx-2021-11-18/%s", query);
                String res = client.doGet(url);
                JSONObject jsonObject = JSONObject.parseObject(res);
                return  String.valueOf(jsonObject.get("count"));

            } else if (name.equals("BusinessSystemOnlineNum")) {
                query = String.format("");
                return null;
            } else if (name.equals("BusinessVisitCount")) {
                query = String.format("");
                return null;
            } else if (name.equals("BusinessDayLoginNum")) {
                query = String.format("");
                return null;
            } else if (name.equals("BusinessSystemSessionNum")) {
                query = String.format("");
                return null;
            } else if (name.equals("BusinessSystemResponseTime")) {
                return null;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
