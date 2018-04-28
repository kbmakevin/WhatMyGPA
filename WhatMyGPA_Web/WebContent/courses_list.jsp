<%@ include file="./partials/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container wrapper" style="margin-top: 65px">
	<div class="row">
		<h1 class="display-4">Courses List</h1>
	</div>

	<div class="row">
		<c:choose>
			<c:when test="${sessionScope.courses == null}">
				<p>
					There are currently no courses in the database... <br> Please
					add a course by click on the <span class='text-success'>Add
						Course</span> button.
				</p>
			</c:when>
			<c:otherwise>
				<table class="table table-hover table-dark text-center">
					<thead>
						<tr>
							<th scope="col">Course Code</th>
							<th scope="col">Credits</th>
							<th scope="col"></th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<c:forEach items="${sessionScope.courses}" var="course"
								varStatus="counter">
								<form action="Courses" method="post">
									<td scope="row">${course.code}</td>
								<td scope="row">${course.credits}</td>
								<td><input type="submit" class="btn btn-primary pull-right"
									value="Add To Cart" style="font-size: 10px;"
									id="addToCart${counter.count}" disabled /></td>
								</form>
							</c:forEach>
						</tr>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>

</div>

<%@ include file="./partials/footer.jsp"%>