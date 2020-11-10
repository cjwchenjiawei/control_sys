package com.lovo.Provider;

import com.lovo.model.Mouse;
import org.apache.ibatis.jdbc.SQL;

public class MouseProvider {
    public String selectSQL(Mouse m){
        return new SQL(){
            {
                SELECT("*");
                FROM("t_mouse");
                if(m.getMouseName() != null){
                    AND().WHERE("mouse_name like '%${mouseName}%'");
                }
            }
        }.toString();
    }
}
