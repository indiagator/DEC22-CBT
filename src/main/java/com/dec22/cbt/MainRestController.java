package com.dec22.cbt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainRestController // Rest API includes all the URLs under the RestController
{
    @PostMapping("updateuserdetails")
    Message updateuserdetails(@RequestParam("fname") String fname, @RequestParam("lname") String lname, @RequestParam("phone") String phone, @RequestParam("type") String type, HttpSession session) throws IOException {

        User user = new User();
        user.setUsername((String)(session.getAttribute("username")));
        user.setFname(fname);
        user.setLname(lname);
        user.setPhone(phone);
        user.setType(type);

        ObjectMapper objectMapper = new ObjectMapper();


        //Jsonb jsonb = JsonbBuilder.create();
        //String userJson = jsonb.toJson(user);

        BufferedReader reader = Files.newBufferedReader(Paths.get("D:\\SOftware Dev and Training Material\\NewWinCode\\tcsdec22\\cbt\\src\\main\\resources\\data\\userdetails.txt"));
        String line;

        Map<String,User> userDetailsMap = new HashMap<>();

        while ((line = reader.readLine())!=null)
        {
            User tempUser = objectMapper.readValue(line,User.class);
            userDetailsMap.put(tempUser.getUsername(),tempUser);
        }
        userDetailsMap.put(user.getUsername(),user);

        BufferedWriter writer = Files.newBufferedWriter(Paths.get("D:\\SOftware Dev and Training Material\\NewWinCode\\tcsdec22\\cbt\\src\\main\\resources\\data\\userdetails.txt"));

        for (Map.Entry<String,User> entry: userDetailsMap.entrySet() )
        {
            writer.append(objectMapper.writeValueAsString(entry.getValue())+"\n");
        }

        writer.flush();

        Message message = new Message();
        message.setMessage("User Details Updated");

        return message;

    }

    @PostMapping("saveOffer")
    Message saveOffer(@RequestParam("hscode") String hscode, @RequestParam("offername") String offername, @RequestParam("unit") String unit, @RequestParam("unitprice") Integer unitprice, @RequestParam("qty") Integer qty, HttpSession session ) throws IOException {



        String offerid = String.valueOf((int) (Math.random()*10000));

        Productoffer offer = new Productoffer();
        offer.setId(offerid);
        offer.setUsername((String)session.getAttribute("username"));
        offer.setHscode(hscode);
        offer.setOffername(offername);
        offer.setUnit(unit);
        offer.setUnitprice(Float.valueOf(unitprice));
        offer.setQty(qty);

        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("D:\\SOftware Dev and Training Material\\NewWinCode\\tcsdec22\\cbt\\src\\main\\resources\\data\\offers.txt"));
        String line;

        List<Productoffer> offersList = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();

        while ((line = bufferedReader.readLine())!=null)
        {
            Productoffer tempOffer = objectMapper.readValue(line,Productoffer.class);
            offersList.add(tempOffer);
        }
        offersList.add(offer);

        BufferedWriter writer = Files.newBufferedWriter(Paths.get("D:\\SOftware Dev and Training Material\\NewWinCode\\tcsdec22\\cbt\\src\\main\\resources\\data\\offers.txt"));

        for (Productoffer tempOffer: offersList )
        {
            writer.append(objectMapper.writeValueAsString(tempOffer)+"\n");
        }

        writer.flush();

        Message message = new Message();
        message.setMessage("Offer Saved Successfully");
        return message;
    }

    @PostMapping("saveOfferDb")
    Message saveOfferDb(@RequestParam("hscode") String hscode, @RequestParam("offername") String offername, @RequestParam("unit") String unit, @RequestParam("unitprice") Integer unitprice, @RequestParam("qty") Integer qty, HttpSession session ) throws IOException
    {

        return new Message();
    }

    }
