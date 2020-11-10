package com.lovo.Provider;

import com.lovo.model.Disease;
import org.apache.ibatis.jdbc.SQL;

public class DiseaseProvider {
    public String selectSQL(Disease d){
        return new SQL(){
            {
                SELECT("*");
                FROM("t_disease");
                if(d.getDiseaseName() != null){
                    AND().WHERE("disease_name like '%${diseaseName}%'");
                }
                if (d.getDiseaseSymptom() != null) {
                    AND().WHERE("disease_symptom like '%${diseaseSymptom}%'");
                }
            }
        }.toString();
    }
}
