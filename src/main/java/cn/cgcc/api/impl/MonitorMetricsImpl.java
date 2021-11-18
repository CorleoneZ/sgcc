package cn.cgcc.api.impl;

import cn.cgcc.api.MonitorMetrics;
import cn.cgcc.model.Info;
import cn.cgcc.service.HttpClint;
import cn.cgcc.service.ServiceSend;
import cn.cgcc.util.Transform;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * https://ares-monitor-api.prcdn.pyfeng.com/api/v1/alarm/data_sync/influxdb?
 * token=fsderbtrhrge&user=monitor&format=pretty&metric=GDCDN_DB_INFO
 * &query=SELECT mean("BusinessDataTableSpace") as 欧 FROM "GDCDN_DB_INFO" WHERE "type" = 'diskused' AND "user" = '/data/mysql/data'
 * GROUP BY time(5m) order by time desc limit 2
 */
@RestController
@RequestMapping("/v1")
public class MonitorMetricsImpl implements MonitorMetrics {

    private ServiceSend send;

    @Override
    @RequestMapping(path = "/value", method = RequestMethod.GET)
    public String getKPIValue(String param) throws JsonProcessingException {

        Info info = Transform.XmlToJson(param);

        send.send();
        return null;
    }


    @Override
    @RequestMapping(path = "/status", method = RequestMethod.GET)
    public int getStatus() {
        return 0;
    }
}
