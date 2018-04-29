package com.whatmygpa.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.whatmygpa.dao.CoursesService;
import com.whatmygpa.models.Course;

/**
 * Servlet implementation class Courses
 */
@WebServlet("/Courses")
public class CoursesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<Course> coursesQueryResults = CoursesService.getAllCourses();
		session.setAttribute("courses", coursesQueryResults);
		request.getRequestDispatcher("courses_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("showAddCourseForm") != null) {

			// show add course form
			request.setAttribute("operationHeader", "Add");
			request.getRequestDispatcher("courses_form.jsp").forward(request, response);

		} else if (request.getParameter("showUpdateCourseForm") != null) {

			// show update course form
			String courseCode = request.getParameter("courseCode").toUpperCase();
			request.setAttribute("course", CoursesService.getCourse(courseCode));
			request.setAttribute("operationHeader", "Update");
			request.getRequestDispatcher("courses_form.jsp").forward(request, response);

		} else if (request.getParameter("removeCourse") != null) {

			// delete a course
			deleteCourse(request, response);

		} else if (request.getParameter("updateCourse") != null) {

			updateCourse(request, response);

		} else if (request.getParameter("addCourse") != null) {

			addCourse(request, response);

		}
	}

	protected void addCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String courseCode = request.getParameter("code").toUpperCase();
		int credits = Integer.parseInt(request.getParameter("credits"));

		if (CoursesService.getCourse(courseCode) == null) {
			CoursesService.addCourse(courseCode, credits);
			request.setAttribute("resultMessage", "Course: " + courseCode + " has been added.");
		} else {
			request.setAttribute("resultMessage", "Course: " + courseCode + " already exists!");
		}
		// re-populate table with updated data
		doGet(request, response);
	}

	protected void updateCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CoursesService.updateCourse(CoursesService.getCourse(request.getParameter("code").toUpperCase()),
				Integer.parseInt(request.getParameter("credits")));
		// re-populate table with updated data
		doGet(request, response);
	}

	protected void deleteCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String courseCode = request.getParameter("courseCode").toUpperCase();
		if (CoursesService.removeOneCourse(courseCode)) {
			request.setAttribute("resultMessage", "Course: " + courseCode + " has been deleted.");
		} else {
			request.setAttribute("resultMessage", "Failed to delete Course: " + courseCode + ".");
		}

		// re-populate table with updated data
		doGet(request, response);
	}

}
