package com.lovo.Provider;

import com.lovo.model.Drug;
import org.apache.ibatis.jdbc.SQL;

public class DrugProvider {
    public String selectSQL(Drug d){
        return new SQL(){
            {
                SELECT("*");
                FROM("t_drug");
                if (d.getDrugName() != null&&!d.getDrugName().equals("")){
                    AND().WHERE("drug_name like '%${drugName}%'");
                }
                if (d.getDrugControl()!=null&&!d.getDrugControl().equals("")){
                    AND().WHERE("drug_control like '%${drugControl}%'");
                }
                if (d.getDrugType() != null&&!d.getDrugType().equals("")){
                    AND().WHERE("drug_type like '%${drugType}%'");
                }
            }
        }.toString();
    }
}
