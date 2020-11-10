package com.lovo.model;

import lombok.Data;

@Data
public class OutwareHouseDrug {
    private Integer outwarehouseDrugId;

    private Integer fkOutwarehouseId;

    private Drug drugs;

    private Integer outwarehouseDrugNumber;

    public OutwareHouseDrug() {
    }

    public OutwareHouseDrug(Integer fkOutwarehouseId, Drug drugs, Integer outwarehouseDrugNumber) {
        this.fkOutwarehouseId = fkOutwarehouseId;
        this.drugs = drugs;
        this.outwarehouseDrugNumber = outwarehouseDrugNumber;
    }
}