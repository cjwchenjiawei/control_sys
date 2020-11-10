package com.lovo.Provider;

import com.lovo.model.User;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
    public String selectSQL(User u){
        return new SQL(){
            {
                SELECT("*");
                FROM("t_user");
                if (u.getUserGrade() != null&&!u.getUserGrade().equals("")){
                    AND().WHERE("user_grade like '%${userGrade}%'");
                }
            }
        }.toString();
    }
}
