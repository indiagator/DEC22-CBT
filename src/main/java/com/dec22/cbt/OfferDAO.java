package com.dec22.cbt;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OfferDAO
{
    List<Productoffer> fetchOffersSellerwise(String username) throws IOException
    {
        List<Productoffer> offerList = new ArrayList<>();

        BufferedReader reader = Files.newBufferedReader(Paths.get("D:\\SOftware Dev and Training Material\\NewWinCode\\tcsdec22\\cbt\\src\\main\\resources\\data\\offers.txt"));
        String line;

        ObjectMapper objectMapper = new ObjectMapper();

        while( (line = reader.readLine())!=null )
        {
            offerList.add(objectMapper.readValue(line,Productoffer.class));
        }
        return offerList.stream().filter(offer-> offer.getUsername().equals(username)).collect(Collectors.toList());
    }

    public void save(Productoffer offer)
    {

    }
}
