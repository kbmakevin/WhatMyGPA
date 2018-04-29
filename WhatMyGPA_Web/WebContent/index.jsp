<%@ include file="./partials/header.jsp"%>
<div class='row'>
	<h1 class="display-1">What My GPA</h1>
	<h3>
		This simple but very useful app will help you to calculate your GPA.
		<c:if test="${empty sessionScope.user }">
		Please login with your username and password, or if this is
		your first time, please register to proceed with GPA calculations
	
	</h3>
	<hr>
</div>
<div class='row'>
	<a class="btn btn-outline-primary" href="Login">Login</a> <a
		class="btn btn-outline-primary" href="Register">Register</a>
	</c:if>
</div>
<!-- if logged in -->
<c:if test="${not empty sessionScope.user }">
	<hr>
	<!-- if user -->
	<c:if test="${sessionScope.user.type eq 'user'}">
		<div class='row'>
			<h2>Welcome, ${sessionScope.user.name}!</h2>
		</div>
		<div class='row'>
			<h4>
				<c:if test="${sessionScope.user.gpa gt 0}">
			Overall GPA: ${sessionScope.user.gpa }			
			</c:if>
				<c:if test="${sessionScope.user.gpa eq 0}">
					You need to go to Transcript and add your courses to see your GPA!
				</c:if>
			</h4>
		</div>
		<div class='row'>
			<a class="btn btn-outline-info" href="Transcript">View Transcript</a>
		</div>
	</c:if>

	<!-- if admin -->
	<c:if test="${sessionScope.user.type eq 'admin'}">

		<div class='row'>
			<h2>Summary</h2>
		</div>
		<div class='row'>
			# Courses Added to this Application: ${requestScope.numberOfCourses }
			<br> # Students Registered with this Application:
			${requestScope.numberOfUsers }
		</div>
		<br>
		<div class='row'>
			<a class="btn btn-outline-info" href="Courses">View Courses</a>
		</div>
	</c:if>

</c:if>

<%@ include file="./partials/footer.jsp"%>