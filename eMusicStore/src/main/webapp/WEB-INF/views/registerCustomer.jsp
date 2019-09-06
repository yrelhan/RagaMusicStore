<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@include file="/WEB-INF/views/template/header.jsp" %>

<script type="text/javascript">
function setShipping() { 
    
              document.getElementById('shippingStreet').value = document.getElementById('billingStreet').value;   
              document.getElementById('shippingApartmentNumber').value = document.getElementById('billingApartmentNumber').value;   
              document.getElementById('shippingCity').value = document.getElementById('billingCity').value;   
              document.getElementById('shippingState').value = document.getElementById('billingState').value;   
              document.getElementById('shippingCountry').value = document.getElementById('billingCountry').value;  
              document.getElementById('shippingZip').value = document.getElementById('billingZip').value;   
    return false;
}  

function fun1()
{
	
	var a=document.getElementById('password').value;
	var b=document.getElementById('confirmpassword').value;
	if(a==b)
		{
		document.getElementById('confirmpasserror').innerHTML="";
		//document.getElementById('confirmpasserror').style.color = 'green';
		//document.getElementById('confirmpasserror').innerHTML="   Confirm password and password are same!";
		}
	else
		{
		document.getElementById('confirmpasserror').style.color = 'red';
		document.getElementById('confirmpasserror').innerHTML="   Confirm password and password are not same";
		}
	}
	
function checkemail() {
    var email_x = document.getElementById("email").value;
    filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if (filter.test(email.value)) {
    	document.getElementById('invalidemail').innerHTML="";
        //document.getElementById("email").style.border = "3px solid green";
        return true;
    } else {
    	document.getElementById('invalidemail').style.color = 'red';
		document.getElementById('invalidemail').innerHTML="   Please enter a valid email id";
        //document.getElementById("email").style.border = "3px solid red";
        return false;
    }
}

function checkphone() {
    var phone_x = document.getElementById("phone").value;
    filter = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
    if (filter.test(phone.value)) {
    	document.getElementById('invalidphone').innerHTML="";
        //document.getElementById("email").style.border = "3px solid green";
        return true;
    } else {
    	document.getElementById('invalidphone').style.color = 'red';
		document.getElementById('invalidphone').innerHTML="   Please enter a valid phone number";
        //document.getElementById("email").style.border = "3px solid red";
        return false;
    }
}

function fun2()
{
	if(document.getElementById('billingStreet')!= null)
	{
		document.getElementById('submitbutton').disabled=false;
		return true;
	}
	else
	{
		document.getElementById('submitbutton').disabled=true;
		return false;
	}
	}
</script>


<div class="container-wrapper">
    <div class="container">
        <div class="page-header">
            <h1>Register Customer</h1>

            <p class="lead">Please fill in your information below:</p>
        </div>

        <form:form action="${pageContext.request.contextPath}/register" method="post"
                   commandName="customer">

        <h3>Basic Info</h3>

        <div class="form-group">
            <label for="name">Name</label><form:errors path="customerName" cssStyle="color: #ff0000"/>
            <form:input path="customerName" id="name" class="form-Control"/>
        </div>

        <div class="form-group">
            <label for="email">Email</label><span style="color: #ff0000">${emailMsg}</span><form:errors
                path="customerEmail" cssStyle="color: #ff0000"/><span id="invalidemail"></span>
            <form:input path="customerEmail" id="email" class="form-Control" onchange="checkemail();"/>
        </div>

        <div class="form-group">
            <label for="phone">Phone</label><span id="invalidphone"></span>
            <form:input path="customerPhone" id="phone" class="form-Control" onchange="checkphone();"/>
        </div>

        <div class="form-group">
            <label for="username">Username</label><span style="color: #ff0000">${usernameMsg}</span><form:errors
                path="username" cssStyle="color: #ff0000"/>
            <form:input path="Username" id="username" class="form-Control"/>
        </div>

        <div class="form-group">
            <label for="password">Password</label><form:errors path="password" cssStyle="color: #ff0000"/>
            <form:password path="password" id="password" class="form-Control"/>
        </div>
        
        <div class="form-group">
            <label for="confirmpassword">Confirm Password</label><span id="confirmpasserror"></span>
            <input type="password" id="confirmpassword" class="form-control" onchange="fun1();"/>
            
        </div>


        <h3>Billing Address</h3>

        <div class="form-group">
            <label for="billingStreet">Street Name</label>
            <form:input path="billingAddress.streetName" id="billingStreet" class="form-Control" onchange="fun2();"/>
        </div>

        <div class="form-group">
            <label for="billingApartmentNumber">Apartment Number</label>
            <form:input path="billingAddress.apartmentNumber" id="billingApartmentNumber" class="form-Control" onchange="fun2();"/>
        </div>

        <div class="form-group">
            <label for="billingCity">City</label>
            <form:input path="billingAddress.city" id="billingCity" class="form-Control" onchange="fun2();"/>
        </div>

        <div class="form-group">
            <label for="billingState">State</label>
            <form:input path="billingAddress.state" id="billingState" class="form-Control" onchange="fun2();"/>
        </div>

        <div class="form-group">
            <label for="billingCountry">Country</label>
            <form:input path="billingAddress.country" id="billingCountry" class="form-Control" onchange="fun2();"/>
        </div>

        <div class="form-group">
            <label for="billingZip">Zipcode</label>
            <form:input path="billingAddress.zipCode" id="billingZip" class="form-Control" onchange="fun2();"/>
        </div>


		<a href="#" onclick="setShipping();return false;"> Click if Billing Address and Shipping Address are the same.</a>
		
        <h3>Shipping Address</h3>

        <div class="form-group">
            <label for="shippingStreet">Street Name</label>
            <form:input path="shippingAddress.streetName" id="shippingStreet" class="form-Control" onchange="fun2();"/>
        </div>

        <div class="form-group">
            <label for="shippingApartmentNumber">Apartment Number</label>
            <form:input path="shippingAddress.apartmentNumber" id="shippingApartmentNumber" class="form-Control" onchange="fun2();"/>
        </div>

        <div class="form-group">
            <label for="shippingCity">City</label>
            <form:input path="shippingAddress.city" id="shippingCity" class="form-Control" onchange="fun2();"/>
        </div>

        <div class="form-group">
            <label for="shippingState">State</label>
            <form:input path="shippingAddress.state" id="shippingState" class="form-Control" onchange="fun2();"/>
        </div>

        <div class="form-group">
            <label for="shippingCountry">Country</label>
            <form:input path="shippingAddress.country" id="shippingCountry" class="form-Control" onchange="fun2();"/>
        </div>

        <div class="form-group">
            <label for="shippingZip">Zipcode</label>
            <form:input path="shippingAddress.zipCode" id="shippingZip" class="form-Control" onchange="fun2();"/>
        </div>

        <br><br>
        
        <input type="submit" value="submit" id="submitbutton" class="btn btn-default" disabled="true">
        <a href="<c:url value="/" />" class="btn btn-default">Cancel</a>
        </form:form>


        <%@include file="/WEB-INF/views/template/footer.jsp" %>
