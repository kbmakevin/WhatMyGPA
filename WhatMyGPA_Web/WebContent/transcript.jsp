<%@ include file="./partials/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container wrapper" style="margin-top: 65px">
	<div class="row">
		<h1 class="display-4">Transcript for ${sessionScope.user.name}</h1>
	</div>

	${requestScope.resultMessage}

	<div class="row">
		<form action="Transcript" method="post">
			<input type="submit" class="btn btn-success" name="showAddCourseForm"
				value="Add Course" />
		</form>
	</div>

	<div class="row">
		<c:choose>
			<c:when test="${sessionScope.user.courseEnrollments.size() eq 0}">
				<p>
					You currently have no courses on your transcript...<br> Please
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
							<th scope="col">Grade Received</th>
							<th scope="col">Earned GPA</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
						<%-- <c:forEach items="${sessionScope.transcriptCourses}" var="course" --%>
						<c:forEach items="${sessionScope.transcriptCourseEnrollments}"
							var="courseEnrollment" varStatus="counter">
							<form action="Transcript" method="post">
								<input type="hidden" id="courseCode" name="courseCode"
									value="${courseEnrollment.course.code}" />
								<!-- 								<input type="hidden" id="courseCode" name="courseCode" -->
								<%-- 									value="${course.code}" /> --%>
								<tr>
									<%-- 									<td scope="row">${fn:toUpperCase(course.code)}</td> --%>
									<%-- 									<td scope="row">${course.credits}</td> --%>
									<td scope="row">${fn:toUpperCase(courseEnrollment.course.code)}</td>
									<td scope="row">${courseEnrollment.course.credits}</td>
									<%-- 									<td scope="row">${(course.courseEnrollments).id}</td> --%>
									<td scope="row">${courseEnrollment.gradeReceived}%</td>
									<td><input type="submit" class="btn btn-info"
										name="showUpdateCourseForm" value="Update" /> <input
										type="submit" class="btn btn-danger" name="removeCourse"
										value="Remove"
										<c:if test="${course.courseEnrollments.size() gt 0}">
										disabled="true"</c:if> /></td>
								</tr>
							</form>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>

</div>

<%@ include file="./partials/footer.jsp"%>