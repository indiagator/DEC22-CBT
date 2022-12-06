package com.dec22.cbt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TypeDao
{
    public String geType(String username) throws SQLException
    {
        String type=null;
        Connection connection = new DbConnection().getDbconnection();
        String query = "select type from usertype where username='"+username+"'";
        Statement  statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next())
        {
            type = resultSet.getString(1);
        }

        return type;
    }
}
