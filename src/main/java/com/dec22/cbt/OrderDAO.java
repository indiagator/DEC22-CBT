package com.dec22.cbt;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderDAO
{
    public void create(Order order) throws SQLException
    {
        Connection connection = new DbConnection().getDbconnection();
        String query = "INSERT INTO orders VALUES ('"+order.getId()+"','"+order.getUsername()+"','"+order.getOfferid()+"')";
        Statement statement = connection.createStatement();
        statement.execute(query);
        connection.close();
    }
}
