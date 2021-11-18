package cn.cgcc.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("enter")
public interface MonitorMetrics {

    @ApiOperation("message")
    String getKPIValue(String param) throws JsonProcessingException;

    @ApiOperation(("status"))
    int getStatus();
}
