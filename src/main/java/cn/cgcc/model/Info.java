package cn.cgcc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "info")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Info {
    @JacksonXmlElementWrapper(useWrapping = false)
    private String CorporationCode;
    @JacksonXmlElementWrapper(useWrapping = false)
    private String Time;
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Api> api;
}
