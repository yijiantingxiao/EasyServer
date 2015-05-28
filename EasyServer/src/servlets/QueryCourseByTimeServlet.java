package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CourseInfo;
import beans.Time;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class QueryCourseByTime
 */
@WebServlet("/queryCourseByTime")
public class QueryCourseByTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryCourseByTimeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String json = "", line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		while ((line = reader.readLine()) != null) {
		    json += line;
		}
		System.out.println(json);
		
		Time time = new Time(1, 1);
		CourseInfo courseInfo1 = new CourseInfo(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 
				UUID.randomUUID().toString(), UUID.randomUUID().toString(), 2, UUID.randomUUID().toString(), time, 30);
		CourseInfo courseInfo2 = new CourseInfo(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 
				UUID.randomUUID().toString(), UUID.randomUUID().toString(), 2, UUID.randomUUID().toString(), time, 30);
		CourseInfo[] courses = {courseInfo1, courseInfo2};
		JSONObject object = new JSONObject();
		object.accumulate("courses", courses);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(object);
		out.flush();
		out.close();
	}

}
