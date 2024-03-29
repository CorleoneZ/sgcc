package cn.cgcc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "Info")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Info {
    @JacksonXmlProperty(isAttribute = true)
    private String CorporationCode;
    @JacksonXmlProperty(isAttribute = true)
    private String Time;
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Name> api;
}
