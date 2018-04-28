<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="./partials/header.jsp" />
<div class="col-md-6">
	<h3>Login</h3>
	<form action="/Login" method="POST">
		<div class="form-group">
			<label for="username">Username</label> <input class="form-control"
				type="text" name="username" />
		</div>
		<div class="form-group">
			<label for="password">Password</label> <input class="form-control"
				type="password" name="password" />
		</div>
		<c:if test="${ not empty error }">
		<div class="alert alert-danger">
			<c:out value="${ error }"></c:out>
		</div>
		</c:if> 
		<div class="form-group">
			<input class="btn btn-primary" type="submit" value="Login" />
		</div>
	</form>
</div>
<jsp:include page="./partials/footer.jsp" />