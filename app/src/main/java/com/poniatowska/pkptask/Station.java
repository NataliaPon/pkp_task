package com.poniatowska.pkptask;

public class Station {
    private int ibnrCode;
    private Integer epaCode=null;
    private String name;
    private StationType stationType;

    public Station(int ibnrCode, int epaCode, String name, StationType stationType) {
        this.ibnrCode = ibnrCode;
        this.epaCode = epaCode;
        this.name = name;
        this.stationType = stationType;
    }

    public Station(int ibnrCode, String name, StationType stationType) {
        this.ibnrCode = ibnrCode;
        this.name = name;
        this.stationType = stationType;
    }

    public int getIbnrCode() {
        return ibnrCode;
    }

    public void setIbnrCode(int ibnrCode) {
        this.ibnrCode = ibnrCode;
    }

    public Integer getEpaCode() {
        return epaCode;
    }

    public void setEpaCode(Integer epaCode) {
        this.epaCode = epaCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StationType getStationType() {
        return stationType;
    }

    public void setStationType(StationType stationType) {
        this.stationType = stationType;
    }
}

