package com.dec22.cbt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CredentialDAO {


    public String getPassword(String username) throws SQLException, Throwable
    {
        String passwordHex=null;

        Connection connection = new DbConnection().getDbconnection();

        String query = "SELECT password FROM credential WHERE username='"+username+"'";
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next())
        {
            passwordHex = resultSet.getString(1);
        }

        if(passwordHex == null)
        {
            throw new Throwable(){

                @Override
                public String getMessage() {
                    return "Username Does not Exist";
                }
            };
        }

        return passwordHex;

    }


    public boolean exists(String username) throws SQLException
    {
        boolean exists = false;

        Connection connection = new DbConnection().getDbconnection();

        String query = "SELECT username FROM credential WHERE username='"+username+"'";
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next())
        {
            exists = true;
        }

        return exists;
    }

    public void saveCredential(Credential credential) throws SQLException
    {
        Connection connection = new DbConnection().getDbconnection();
        String query = "INSERT INTO credential(username,password) values('"+credential.getUsername()+"','"+credential.getPassword()+"')";
        Statement statement = connection.createStatement();
        statement.execute(query);
    }



}
