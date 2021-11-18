package cn.cgcc.service;

import cn.cgcc.api.impl.MonitorMetricsImpl;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
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
    private HttpClint clint;

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
            String res = clint.doGet(url);
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
