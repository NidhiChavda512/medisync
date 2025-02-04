<nav class="navbar navbar-expand-lg navbar-dark"
	style="background-color: #303137;">
	<div class="container-fluid">
		<a class="navbar-brand" href="#"><img
			src="<%=request.getContextPath()%>/images/logo-3.png" alt="MEDI SYNC Logo" height="30"
			class="d-inline-block align-text-top"> MediSync</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<%
		if (session.getAttribute("username") == null) {
		%>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="login.jsp"> <i
						class="fa-solid fa-arrow-right-to-bracket"></i> Login
				</a></li>
			</ul>
			<%
			} else {
			String logoutPath = request.getContextPath() + "/logout";
			%>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="<%=logoutPath%>"> <i
							class="fa-solid fa-arrow-right-to-bracket"></i> Logout
					</a></li>
				</ul>
				<%
				}
				%>

			</div>
		</div>
	</div>
</nav>