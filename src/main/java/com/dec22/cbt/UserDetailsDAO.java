package com.dec22.cbt;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDetailsDAO
{


    public void saveDetails(User user) throws SQLException
    {
        Connection connection = new DbConnection().getDbconnection();

        String query1 = "INSERT INTO userdetails(username,fname,lname,email,phone) values('"+user.getUsername()+"','"+user.getFname()+"','"+user.getLname()+"','"+user.getEmail()+"','"+user.getPhone()+"')";
        Statement statement = connection.createStatement();
        statement.execute(query1);

        String query2 = "INSERT INTO usertype(username,type) values('"+user.getUsername()+"','"+user.getType()+"')";
        statement.execute(query2);

        connection.close();


    }

}
