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
			<c:when test="${sessionScope.user.courses.size() eq 0}">
				<p>
					You currently have no courses on your transcript...<br> Please
					add a course by click on the <span class='text-success'>Add
						Course</span> button.
				</p>
			</c:when>
		</c:choose>
	</div>

</div>

<%@ include file="./partials/footer.jsp"%>