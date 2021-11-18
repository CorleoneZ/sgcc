package cn.cgcc.util;

import cn.cgcc.model.Info;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Transform {

    public static Info XmlToJson(String xml) throws JsonProcessingException {
        JacksonXmlModule module = new JacksonXmlModule();
        XmlMapper mapper = new XmlMapper(module);
        Info info = mapper.readValue(xml, Info.class);
        return info;
    }
}
