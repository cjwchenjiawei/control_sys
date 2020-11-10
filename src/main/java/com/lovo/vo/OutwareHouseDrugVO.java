package com.lovo.vo;

import com.lovo.model.Drug;
import lombok.Data;

@Data
public class OutwareHouseDrugVO {
    private Integer outwarehouseDrugId;

    private Integer fkOutwarehouseId;

    private String drugName;

    private String drugType;

    private String drugControl;

    private Integer outwarehouseDrugNumber;
}
