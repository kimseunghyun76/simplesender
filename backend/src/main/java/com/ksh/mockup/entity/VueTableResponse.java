package com.ksh.mockup.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class VueTableResponse {
    private int totalRecords = 0;
    private List<MccLog> data = new ArrayList<MccLog>();

    public VueTableResponse(int totalRecords, List<MccLog> data) {
        this.totalRecords = totalRecords;
        this.data = data;
    }
}
