package com.lovo.Provider;

import com.lovo.model.Worm;
import org.apache.ibatis.jdbc.SQL;

public class WormProvider {
    public String selectSQL(Worm worm){
        return new SQL(){
            {
                SELECT("*");
                FROM("t_worm");
                if(worm.getWormName() != null){
                    AND().WHERE("worm_name like '%${wormName}%'");
                }
                if (worm.getWormHost() != null) {
                    AND().WHERE("worm_host like '%${wormHost}%'");
                }
            }
        }.toString();
    }
}
