package com.dec22.cbt;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@RepositoryDefinition(domainClass = Order.class, idClass = String.class)
public class MyRepositoryImpl implements MyRepository<Order,String>
{


    @Override
    public OrderView fetchSellerInfo()
    {


       return new OrderView();
    }
}
