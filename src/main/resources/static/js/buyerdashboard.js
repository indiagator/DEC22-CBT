
    //alert("productList javascript runs");

    var testHttpRequest;

    function ajaxTestHandler()
    {
        if (testHttpRequest.readyState === XMLHttpRequest.DONE)
        {
            if (testHttpRequest.status === 200) {
                //alert(testHttpRequest.responseText);

                let message = JSON.parse(testHttpRequest.responseText);
                alert(message.message);

                //for(let product of product_list)
                //{
                //  let product_info = product.chapter +" <b>"+product.hscode+"</b> "+product.description+" "+product.unit;
                //   let para = document.createElement("p")
                //  para.innerHTML = product_info;
                //  document.getElementById("product_list").appendChild(para);
                // }

                //let user_details_obj = JSON.parse(testHttpRequest.responseText);


            } else {
                alert('There was a problem with the request.');
            }
        }
    }

    function makeTestRequest()
    {

        testHttpRequest = new XMLHttpRequest();
        if (!testHttpRequest) {
            alert('Giving up :( Cannot create an XMLHTTP instance');
            return false;
        }

        // alert("Ajax request Created");

        let fname = document.getElementById("detailsForm").elements[0].value;
        let lname =document.getElementById("detailsForm").elements[1].value;
        let phone = document.getElementById("detailsForm").elements[2].value;
        let type = document.getElementById("detailsForm").elements[3].value;
        dataString = "fname="+fname+"&lname="+lname+"&phone="+phone+"&type="+type;

        testHttpRequest.onreadystatechange = ajaxTestHandler;
        testHttpRequest.open('POST','http://localhost:8085/updateuserdetails',true);
        testHttpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        testHttpRequest.send(dataString);


    }
    // document.getElementById("show_profile_button").addEventListener("click",makeTestRequest);

    var testHttpRequest1;

    function ajaxTestHandler1()
    {
        if (testHttpRequest1.readyState === XMLHttpRequest.DONE)
        {
            if (testHttpRequest1.status === 200) {
                //alert(testHttpRequest.responseText);

                let message = JSON.parse(testHttpRequest1.responseText);
                alert(message.message);

                //for(let product of product_list)
                //{
                //  let product_info = product.chapter +" <b>"+product.hscode+"</b> "+product.description+" "+product.unit;
                //   let para = document.createElement("p")
                //  para.innerHTML = product_info;
                //  document.getElementById("product_list").appendChild(para);
                // }

                //let user_details_obj = JSON.parse(testHttpRequest.responseText);


            } else {
                alert('There was a problem with the request.');
            }
        }
    }

    function createOrder()
    {

        testHttpRequest1 = new XMLHttpRequest();
        if (!testHttpRequest1) {
            alert('Giving up :( Cannot create an XMLHTTP instance');
            return false;
        }

        // alert("Ajax request Created");

        let offerid = document.getElementById("offerForm").elements[0].value;
        dataString = "offerid="+offerid;

        testHttpRequest1.onreadystatechange = ajaxTestHandler1;
        testHttpRequest1.open('POST','http://localhost:8085/createorder',true);
        testHttpRequest1.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        testHttpRequest1.send(dataString);


    }

    var testHttpRequest2;

    function ajaxTestHandler2()
    {
        if (testHttpRequest2.readyState === XMLHttpRequest.DONE)
        {
            if (testHttpRequest2.status === 200) {
                //alert(testHttpRequest.responseText);

                let message = JSON.parse(testHttpRequest2.responseText);
                alert(message.message);

                //for(let product of product_list)
                //{
                //  let product_info = product.chapter +" <b>"+product.hscode+"</b> "+product.description+" "+product.unit;
                //   let para = document.createElement("p")
                //  para.innerHTML = product_info;
                //  document.getElementById("product_list").appendChild(para);
                // }

                //let user_details_obj = JSON.parse(testHttpRequest.responseText);


            } else {
                alert('There was a problem with the request.');
            }
        }
    }

    function sendMessage()
    {
        testHttpRequest2 = new XMLHttpRequest();
        if (!testHttpRequest2) {
            alert('Giving up :( Cannot create an XMLHTTP instance');
            return false;
        }

        // alert("Ajax request Created");

        let offerid = document.getElementById("offerForm").elements[0].value;
        let message = document.getElementById("offerForm").elements[1].value;
        dataString = "offerid="+offerid+"&message="+message;

        testHttpRequest2.onreadystatechange = ajaxTestHandler2;
        testHttpRequest2.open('POST','http://localhost:8085/savemessage',true);
        testHttpRequest2.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        testHttpRequest2.send(dataString);
    }








