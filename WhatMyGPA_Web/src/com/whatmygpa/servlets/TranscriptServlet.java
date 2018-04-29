package com.whatmygpa.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whatmygpa.dao.CourseEnrollmentServices;
import com.whatmygpa.dao.CoursesService;
import com.whatmygpa.models.CourseEnrollment;
import com.whatmygpa.models.Courses;
import com.whatmygpa.models.Users;

/**
 * Servlet implementation class TranscriptServlet
 */
@WebServlet("/Transcript")
public class TranscriptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// need all courses student took
		List<Courses> transcriptCourses = new ArrayList();

		for (CourseEnrollment courseEnrollment : CourseEnrollmentServices
				.getAllCourseEnrollmentsWithUser((Users) request.getSession().getAttribute("user"))) {
			transcriptCourses.add(courseEnrollment.getCourse());
		}
		request.getSession().setAttribute("transcriptCourses", transcriptCourses);

		request.getRequestDispatcher("transcript.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("showAddCourseForm") != null) {

			// show add course form
			request.setAttribute("allCourses", CoursesService.getAllCourses());
			request.setAttribute("operationHeader", "Add");
			request.getRequestDispatcher("marks_form.jsp").forward(request, response);

		} else if (request.getParameter("addCourse") != null) {

			addCourse(request, response);

		} else if (request.getParameter("showUpdateCourseForm") != null) {

			// show update course form
			String courseCode = request.getParameter("courseCode").toUpperCase();
			request.setAttribute("course", CoursesService.getCourse(courseCode));
			CourseEnrollment ce = CourseEnrollmentServices.getOneCourseEnrollment(
					(Users) request.getSession().getAttribute("user"), CoursesService.getCourse(courseCode));
			request.setAttribute("gradeReceived", ce.getGradeReceived());
			request.setAttribute("operationHeader", "Update");
			request.getRequestDispatcher("marks_form.jsp").forward(request, response);

		} else if (request.getParameter("updateCourse") != null) {

			updateCourse(request, response);

		}

	}

	protected void updateCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String courseCode = request.getParameter("code").toUpperCase();
		int gradeReceived = Integer.parseInt(request.getParameter("gradeReceived"));

		// CourseEnrollmentServices.addCourseEnrollment((Users)
		// request.getSession().getAttribute("user"),
		// CoursesService.getCourse(courseCode), gradeReceived);
		//
		// request.setAttribute("resultMessage", "Course: " + courseCode + " has been
		// added.");
		//
		// // re-populate table with updated data
		// doGet(request, response);

		// request.setAttribute("course", CoursesService.getCourse(courseCode));
		CourseEnrollment ce = CourseEnrollmentServices.getOneCourseEnrollment(
				(Users) request.getSession().getAttribute("user"), CoursesService.getCourse(courseCode));
		CourseEnrollmentServices.updateCourseEnrollment(ce, gradeReceived);

		// CoursesService.updateCourse(CoursesService.getCourse(request.getParameter("code").toUpperCase()),
		// Integer.parseInt(request.getParameter("credits")));
		// re-populate table with updated data
		doGet(request, response);
	}

	protected void addCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String courseCode = request.getParameter("code").toUpperCase();
		int gradeReceived = Integer.parseInt(request.getParameter("gradeReceived"));

		CourseEnrollmentServices.addCourseEnrollment((Users) request.getSession().getAttribute("user"),
				CoursesService.getCourse(courseCode), gradeReceived);

		request.setAttribute("resultMessage", "Course: " + courseCode + " has been added.");

		// re-populate table with updated data
		doGet(request, response);
	}

}