package com.stockapp.mainapi.models.nosql.dto;

import java.io.Serializable;

public class IdentifySpecieDTO implements Serializable {
    private String name;
    private String path;

    public IdentifySpecieDTO(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
