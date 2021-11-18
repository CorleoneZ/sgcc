package cn.cgcc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * https://ares-monitor-api.prcdn.pyfeng.com/api/v1/alarm/data_sync/influxdb?
 * token=fsderbtrhrge&user=monitor&format=pretty&metric=GDCDN_DB_INFO&
 * query=SELECT mean("BusinessDataTableSpace") as BusinessDataTableSpace
 * FROM "GDCDN_DB_INFO" WHERE "type" = 'diskused' AND "user" = '/data/mysql/data' GROUP BY time(5m) order by time desc limit 2
 */
@Service
public class ServiceSend {

    private String token;
    private String user;
    private String format;
    private String metric;
    private String api;

    @Autowired
    private HttpClint clint;

    public StringBuffer send() {
        try {
            String query =  String.format("SELECT mean(%) as %",api,api);
            String url =
                    String.format("https://ares-monitor-api.prcdn.pyfeng.com/api/v1/alarm/data_sync/influxdb?token=%&user=%&format=%&metric=%&query=%"
                            ,token,user,format,metric,query);
            StringBuffer res = clint.doGet(url);
            return res;
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
