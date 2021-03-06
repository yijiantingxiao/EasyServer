package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.SchoolInfo;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class AddSchoolInfoServlet
 */
@WebServlet(name = "AddSchoolInfoServlet", urlPatterns = "/addSchoolInfo")
public class AddSchoolInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSchoolInfoServlet() {
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
		//System.out.println(json);
		JSONObject jsonObject = JSONObject.fromObject(json);
		JSONObject object = new JSONObject();
		SchoolInfo schoolInfo = (SchoolInfo) JSONObject.toBean(jsonObject, SchoolInfo.class);
		if (schoolInfo.getSchoolName() != null && schoolInfo.getCreditRequirement() != 0) {
			object.accumulate("success", true);
			object.accumulate("failReason", "");
		} else {
			jsonObject.accumulate("success", false);
			object.accumulate("failReason", "");
		}		
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(object);
		out.flush();
		out.close();
	}

}
