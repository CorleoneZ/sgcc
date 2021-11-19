package cn.cgcc.api.impl;

import cn.cgcc.api.MonitorMetrics;
import cn.cgcc.model.Info;
import cn.cgcc.model.LoginUser;
import cn.cgcc.model.XmlRes;
import cn.cgcc.model.Value;
import cn.cgcc.service.ServiceSend;
import cn.cgcc.util.Transform;
import com.fasterxml.jackson.core.JsonProcessingException;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

/**
 * https://ares-monitor-api.prcdn.pyfeng.com/api/v1/alarm/data_sync/influxdb?
 * token=fsderbtrhrge&user=monitor&format=pretty&metric=GDCDN_DB_INFO
 * &query=SELECT mean("BusinessDataTableSpace") as 欧 FROM "GDCDN_DB_INFO" WHERE "type" = 'diskused' AND "user" = '/data/mysql/data'
 * GROUP BY time(5m) order by time desc limit 2
 */
@RestController
@RequestMapping("/v1")
public class MonitorMetricsImpl implements MonitorMetrics {

    private static final Logger logger = LoggerFactory.getLogger(MonitorMetricsImpl.class);
    private String temp;
    private String metricName;

    @Autowired
    private ServiceSend serviceSend;

    @Override
    @RequestMapping(path = "/value", consumes = { MediaType.APPLICATION_XML_VALUE },
            produces = MediaType.APPLICATION_XML_VALUE,method = RequestMethod.GET)
    public String getKPIValue(@RequestBody String param) throws JAXBException, JsonProcessingException {

        List<Value> values = new ArrayList<>();
        XmlRes res;
        LoginUser loginUser;
        Info info = Transform.XmlToObject(param);

        int len = info.getApi().size();
        for (int i = 0; i < len; ++i) {
            metricName = info.getApi().get(i).getName();
            /* three common http get requests */
            if (metricName.equals("BusinessDataTableSpace") || metricName.equals("BusinessSystemDBTime") || metricName.equals("BusinessSystemRunningTime")) {
                temp = serviceSend.send(info.getApi().get(i).getName());
                logger.info("temp: " + temp);

                JSONArray jsonArray = JSONArray.fromObject(temp);
                Object o = jsonArray.get(1);
                JSONObject jsonObject = JSONObject.fromObject(o);
                logger.info("jsonObject: " + jsonObject);
                Object v = jsonObject.get(info.getApi().get(i).getName());
                Value value = Value.builder().name(info.getApi().get(i).getName()).value(v).build();
                values.add(value);
                logger.info("v: " + value);
                /* six es http requests */
            } else {
                /* build userInfoList */
                String num = null;
            }
        }
        /* common return */
        if (!info.getApi().get(0).getName().equals("BusinessSystemLoginRoll") || !info.getApi().get(0).getName().equals("BusinessSystemOnlineRoll")) {
            try {
                //1.transform java object
                res = XmlRes.builder().status("success").message("").reason("")
                        .Corporation(info.getCorporationCode()).api(values).build();
                logger.info("res: " + res);
                //2.transform xml return
                String xml = Transform.ObjectToXml(res);
                return xml;
            } catch (Exception e) {
                res = XmlRes.builder().status("failure")
                        .message(e.getMessage()).reason("").build();
                String xml = Transform.ObjectToXml(res);
                return xml;
            }
        /* huge data return */
        } else {
            try {
                /* LoginUser return */
                loginUser = LoginUser.builder().userInfoList(null).build();

                String xml = Transform.ObjectToXml(loginUser);
                return xml;
            } catch (Exception e) {
                loginUser = LoginUser.builder().build();
                String xml = Transform.ObjectToXml(loginUser);
                return xml;
            }
        }
    }

    @Override
    @RequestMapping(path = "/status", method = RequestMethod.GET)
    public int getStatus() {
        return 0;
    }
}
