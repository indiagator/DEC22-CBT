package com.dec22.cbt;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.codec.digest.DigestUtils;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController
{
    @GetMapping("hello")
    public String greet(Model model)
    {

        //Jsonb jsonb = JsonbBuilder.create();


        model.addAttribute("greeting","Hello TCS from EDUREKA");
        return "hello";
    }

    @PostMapping("login")
    String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model model) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();


        //String username = request.getParameter("username");
        //String password = request.getParameter("password");
        String passwordHash= DigestUtils.md5Hex(password).toUpperCase();

        List<Credential> credentialList = new ArrayList<>();

        BufferedReader credentialReader = Files.newBufferedReader(Paths.get("D:\\SOftware Dev and Training Material\\NewWinCode\\tcsdec22\\cbt\\src\\main\\resources\\data\\credential.csv"));
        String line="";
        while ( (line = credentialReader.readLine()) != null )
        {
            String[] credentialData =  line.split(",");

            Credential credential = new Credential();
            credential.setUsername(credentialData[0]);
            credential.setPassword(credentialData[1]);
            credentialList.add(credential);
        }

        Optional<Credential> authUsername;
        if(  (authUsername =credentialList.stream().filter(credential -> credential.getUsername().equals(username)).findFirst()).isPresent() )
        {
            if(authUsername.get().getPassword().equals(passwordHash))
            {
                session.setAttribute("username",username);

                BufferedReader detailsReader = Files.newBufferedReader(Paths.get("D:\\SOftware Dev and Training Material\\NewWinCode\\tcsdec22\\cbt\\src\\main\\resources\\data\\userdetails.txt"));
                String detaiLine;

                List<User> userList = new ArrayList<>();

                while ( (detaiLine = detailsReader.readLine())!=null)
                {
                    userList.add(objectMapper.readValue(detaiLine,User.class));
                }

                String userType = userList.stream().filter(user -> user.getUsername().equals(username)).findFirst().get().getType();

                if(userType.equals("BUYER"))
                {
                    return "buyerdashboard";
                }
                else if (userType.equals("SELLER"))
                {
                    OfferDAO offerDAO = new OfferDAO();
                    model.addAttribute("offerList", offerDAO.fetchOffersSellerwise(username));
                    return "sellerdashboard";
                }
                else if(userType.equals("ADMIN"))
                {
                    return "dashboard";
                }
                else
                {
                    return "landingpage";
                }



                //response.setContentType("text/html");
                //PrintWriter out = response.getWriter();
                //out.write("You are Authenticated");
                //out.flush();
            }
            else
            {
                model.addAttribute("errMsgLogin","Incorrect Password");
               return "dashboard";

                //response.setContentType("text/html");
                //PrintWriter out = response.getWriter();
                //out.write("Incorrect Password");
                //out.flush();
            }
        }
        else
        {

            model.addAttribute("errMsgLogin","Invalid Username");
            return "dashboard";

            //response.setContentType("text/html");
            //PrintWriter out = response.getWriter();
            //out.write("Invalid Username");
            //out.flush();
        }
    }

    @PostMapping("logindb")
    public String loginDb(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model model) throws Throwable
    {

        CredentialDAO credentialDAO = new CredentialDAO();
        String passwordHash= DigestUtils.md5Hex(password).toUpperCase();

        if(credentialDAO.exists(username))
        {
            if(passwordHash.equals(credentialDAO.getPassword(username)))
            {
                session.setAttribute("username",username);

                TypeDao typeDao = new TypeDao();

                switch (typeDao.geType(username))
                {
                    case "BUYER"  : return "buyerdashboard";
                    case "SELLER" : return "sellerdashboard";
                    case "ADMIN"  : return "dashboard";
                    default  : return "landingpage";
                }
            }
            else return "landingpage";
        }
        else return "landinpage";

    }

        @PostMapping("logout")
    String logout(HttpSession session)
    {
        session.invalidate();
        return "landingpage";
    }

    @PostMapping("signup")
    public String signup(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model model) throws IOException {

        String passwordHash= DigestUtils.md5Hex(password).toUpperCase();

        session.setAttribute("username",username);

        List<Credential> credentialList = new ArrayList<>();

        BufferedReader credentialReader = Files.newBufferedReader(Paths.get("D:\\SOftware Dev and Training Material\\NewWinCode\\tcsdec22\\cbt\\src\\main\\resources\\data\\credential.csv"));
        String line="";
        while ( (line = credentialReader.readLine()) != null )
        {
            String[] credentialData =  line.split(",");

            Credential credential = new Credential();
            credential.setUsername(credentialData[0]);
            credential.setPassword(credentialData[1]);

            credentialList.add(credential);
        }

        Optional<Credential> authUsername;
        if(  (authUsername =credentialList.stream().filter(credential -> credential.getUsername().equals(username)).findFirst()).isPresent() )
        {
            model.addAttribute("errMsgSignup","Username Exists");
            return "dashboard";
        }
        else
        {
            Credential credential = new Credential();
            credential.setUsername(username);
            credential.setPassword(passwordHash);
            credentialList.add(credential);

            BufferedWriter credentialWriter = Files.newBufferedWriter(Paths.get("D:\\SOftware Dev and Training Material\\NewWinCode\\tcsdec22\\cbt\\src\\main\\resources\\data\\credential.csv"));

            credentialList.forEach(credential1 -> {
                try {
                    credentialWriter.write(credential1.getUsername()+","+credential1.getPassword()+"\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            credentialWriter.flush();

            session.setAttribute("username",username);
            return "dashboard";
        }

    }

    @PostMapping("signupdb")
    public String signupdb(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model model) throws IOException, SQLException {

            Credential credential = new Credential();
            credential.setUsername(username);
            String passwordHash= DigestUtils.md5Hex(password).toUpperCase();
            credential.setPassword(passwordHash);

            CredentialDAO credentialDAO = new CredentialDAO();
            credentialDAO.saveCredential(credential);

            session.setAttribute("username",username);

            return "updateprofile";

    }

    @PostMapping("updateuserdetailsmvcdb")
    public String updateuserdetails(@RequestParam("fname") String fname, @RequestParam("lname") String lname, @RequestParam("phone") String phone,@RequestParam("email") String email, @RequestParam("type") String type, HttpSession session) throws IOException, SQLException {

        User user = new User();
        user.setUsername((String)session.getAttribute("username"));
        user.setFname(fname);
        user.setLname(lname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setType(type);

        UserDetailsDAO userDetailsDAO = new UserDetailsDAO();
        userDetailsDAO.saveDetails(user);

        switch (type)
        {
            case "BUYER"  : return "buyerdashboard";
            case "SELLER" : return "sellerdashboard";
            case "ADMIN"  : return "dashboard";
            default  : return "landingpage";
        }

    }






    }
