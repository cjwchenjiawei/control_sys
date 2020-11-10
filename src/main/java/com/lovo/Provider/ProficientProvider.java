package com.lovo.Provider;

import com.lovo.model.Proficient;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class ProficientProvider {
    public String selectSQL(Proficient p){
        return new SQL(){
            {
                SELECT("*");
                FROM("t_proficient");
                if (p.getProficientName() != null&&!p.getProficientName().equals("")){
                    AND().WHERE("t_proficient.proficient_name like '%${proficientName}%'");
                }
                if (p.getProficientSpeciality()!=null&&!p.getProficientSpeciality().equals("")){
                    AND().WHERE("t_proficient.proficient_speciality like '%${proficientSpeciality}%'");
                }
                if (p.getProficientUnit() != null&&!p.getProficientUnit().equals("")){
                    AND().WHERE("t_proficient.proficient_unit like '%${proficientUnit}%'");
                }
            }
        }.toString();
    }
}
