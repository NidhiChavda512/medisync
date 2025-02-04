<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient Registration</title>
<%@include file="../component/allcss.jsp"%>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
}

.footer {
	position: scrollable;
	bottom: 0;
	width: 100%;
	background-color: #f5f5f5;
	text-align: center;
}

.error-message {
	color: red;
	font-size: 12px;
}

.button-color {
	background-color: #0cd268;
	color: white;
}
</style>
<script>


	function displayError(inputId, errorMessage) {
		var errorDiv = document.getElementById(inputId + "-error");
		errorDiv.innerHTML = errorMessage;
	}

	function clearError(inputId) {
		var errorDiv = document.getElementById(inputId + "-error");
		errorDiv.innerHTML = "";
	}

	function displayError(inputId, errorMessage) {
		var errorDiv = document.getElementById(inputId + "-error");
		errorDiv.innerHTML = errorMessage;
	}

	function clearError(inputId) {
		var errorDiv = document.getElementById(inputId + "-error");
		errorDiv.innerHTML = "";
	}

	function validateForm() {
    console.log("In Validateform");
    var fullname = document.getElementById("fullname").value;
    var dob = document.getElementById("dob").value;
    var contactno = document.getElementById("contactno").value;
    var email = document.getElementById("email").value;

    var isValid = true;

    let emailRegex = /^[a-z0-9]+@[a-z]+\.[a-z]{2,3}$/;
    var expr = /^(0|91)?[6-9][0-9]{9}$/;
    
    // Validation logic for each input field
    
    var dobDate = new Date(dob);
    var currentDate = new Date();

    // Set the time of currentDate to be 00:00:00 to compare only the dates
    currentDate.setHours(0, 0, 0, 0);

    if (dobDate >= currentDate) {
        displayError("dob", "Please Enter a Valid Date of Birth");
        isValid = false;
    } else {
        clearError("dob");
    }

    if (!(expr.test(contactno))) {
        displayError("contactno", "Please Enter Valid Mobile Number");
        isValid = false;
    } else {
        clearError("contactno");
    }

    if (!(emailRegex.test(email))) {
        displayError("email", "Please Enter Valid Email");
        isValid = false;
    } else {
        clearError("email");
    }
    
    return isValid;
}
</script>

</head>
<body>
	<%@include file="navbar.jsp"%>
	<div class="container-fluid p-3">
		<div class="row">
			<div class="col-md-5 offset-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<p class="fs-4 fw-bold text-center text-success">Register
							Patient</p>
						<%
						String errorMessage = (String) session.getAttribute("errorMessage");
						if (errorMessage != null) {
						%>
						<script>
								alert('<%=errorMessage%>
							');
						</script>
						<%
						session.removeAttribute("errorMessage");
						}
						%>
						<form action="<%=request.getContextPath()%>/add-patient"
							method="post" onsubmit="return validateForm()">
							<div class="mb-3">
								<label class="form-label">Full Name</label> <input type="text"
									required name="fullname" id="fullname" class="form-control">
								<div id="fullname-error" class="error-message"></div>
							</div>

							<div class="mb-3">
								<label class="form-label">DOB</label> <input type="date"
									required name="dob" id="dob" class="form-control">
								<div id="dob-error" class="error-message"></div>
							</div>

							<div class="mb-3">
								<label>Gender</label> <select class="form-control" name="gender"
									required>
									<option value="Male">Male</option>
									<option value="Female">Female</option>
								</select>
							</div>

							<div class="mb-3">
								<label class="form-label">Mob No</label> <input type="text"
									required name="contactno" id="contactno" class="form-control">
								<div id="contactno-error" class="error-message"></div>
							</div>

							<div class="mb-3">
								<label class="form-label">Email</label> <input type="text"
									required name="email" id="email" type="email"
									class="form-control">
								<div id="email-error" class="error-message"></div>
							</div>
							<center>
								<button type="submit" class="btn button-color">Submit</button>
							</center>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="footer">
		<%@include file="/component/footer.jsp"%>
	</div>

</body>
</html>

