package com.dec22.cbt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderViewDAO
{

    public List<OrderView> fetchAllOrdersBuyerwise(String buyername) throws SQLException
    {
        List<OrderView> orderViewList = new ArrayList<>();

        Connection connection = new DbConnection().getDbconnection();
        String query = "SELECT productoffers.username,offername,unit,qty,(qty*unitprice) from productoffers inner join orders on productoffers.offerid=orders.offerid group by orders.username, offername, productoffers.username, unit, qty, unitprice having orders.username='"+buyername+"' order by productoffers.username;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next())
        {
            String sellername = resultSet.getString(1);
            String offername = resultSet.getString(2);
            String unit = resultSet.getString(3);
            String qty = resultSet.getString(4);
            String orderamnt = resultSet.getString(5);

            OrderView orderView = new OrderView();
            orderView.setSellername(sellername);
            orderView.setOffername(offername);
            orderView.setUnit(unit);
            orderView.setQty(Integer.parseInt(qty));
            orderView.setOrderamnt(Float.parseFloat(orderamnt));

            orderViewList.add(orderView);
        }

        return orderViewList;
    }
}
