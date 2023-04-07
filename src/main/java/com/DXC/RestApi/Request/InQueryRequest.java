package com.DXC.RestApi.Request;

import java.util.List;
//Request Payload getter & Setter
public class InQueryRequest {
    private List<String> firstNames;

    public List<String> getFirstNames() {
        return firstNames;
    }

    public void setFirstNames(List<String> firstNames) {
        this.firstNames = firstNames;
    }
}
