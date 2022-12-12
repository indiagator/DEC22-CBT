package com.dec22.cbt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductOfferDAO
{
    public void save(Productoffer offer) throws SQLException
    {
        Connection connection = new DbConnection().getDbconnection(); // Start Session
        String query = "INSERT INTO productoffers VALUES ('"+offer.getId()+"','"+offer.getUsername()+"','"+offer.getHscode()+"','"+offer.getOffername()+"',"+offer.getQty()+",'"+offer.getUnit()+"',"+offer.getUnitprice()+")";
        Statement statement = connection.createStatement();
        statement.execute(query);
        connection.close(); // End Session
    }


    public List<Productoffer> fetchAllOffers() throws SQLException
    {
        List<Productoffer> productoffers = new ArrayList<>();

        Connection connection = new DbConnection().getDbconnection();
        String query = "SELECT offerid,(fname,lname),hscode,offername,qty,unit,unitprice FROM productoffers NATURAL JOIN userdetails";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next())
        {
            Productoffer offer = new Productoffer();
            offer.setId(resultSet.getString(1));
            offer.setUsername(resultSet.getString(2));  // | fname,lname |  no username
            offer.setHscode(resultSet.getString(3));
            offer.setOffername(resultSet.getString(4));
            offer.setQty(Integer.valueOf(resultSet.getString(5)));
            offer.setUnit(resultSet.getString(6));
            offer.setUnitprice(Float.valueOf(resultSet.getString(7)));

            productoffers.add(offer);

        }

        return productoffers;

    }

    public List<Productoffer> fetchAllOffersByHscode(String hscode) throws SQLException
    {
        List<Productoffer> productoffers = new ArrayList<>();

        Connection connection = new DbConnection().getDbconnection();
        String query = "SELECT offerid,(fname,lname),hscode,offername,qty,unit,unitprice FROM productoffers NATURAL JOIN userdetails WHERE hscode='"+hscode+"'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next())
        {
            Productoffer offer = new Productoffer();
            offer.setId(resultSet.getString(1));
            offer.setUsername(resultSet.getString(2));  // | fname,lname |  no username
            offer.setHscode(resultSet.getString(3));
            offer.setOffername(resultSet.getString(4));
            offer.setQty(Integer.valueOf(resultSet.getString(5)));
            offer.setUnit(resultSet.getString(6));
            offer.setUnitprice(Float.valueOf(resultSet.getString(7)));

            productoffers.add(offer);

        }

        return productoffers;

    }

    public List<Productoffer> fetchAllOffersSellerwise(String username) throws SQLException
    {
        List<Productoffer> productoffers = new ArrayList<>();

        Connection connection = new DbConnection().getDbconnection();
        String query = "SELECT * FROM productoffers WHERE username='"+username+"'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next())
        {
            Productoffer offer = new Productoffer();
            offer.setId(resultSet.getString(1));
            offer.setUsername(resultSet.getString(2));
            offer.setHscode(resultSet.getString(3));
            offer.setOffername(resultSet.getString(4));
            offer.setQty(Integer.valueOf(resultSet.getString(5)));
            offer.setUnit(resultSet.getString(6));
            offer.setUnitprice(Float.valueOf(resultSet.getString(7)));

            productoffers.add(offer);

        }

        return productoffers;

    }

}
