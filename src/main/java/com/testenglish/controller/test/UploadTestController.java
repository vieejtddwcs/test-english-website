package com.testenglish.controller.test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testenglish.dao.impl.TestDAOImpl;
import com.testenglish.model.test.Test;
import com.testenglish.model.test.Test.Type;
import com.testenglish.model.user.User;
import com.testenglish.util.MyIOUtils;
import com.testenglish.util.MyStringUtils;

@WebServlet("/uploadtest")
public class UploadTestController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TestDAOImpl testDAO;
	private Map<String, String> URLs;
	private Map<String, String> webpages;
	
	public UploadTestController() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		testDAO = new TestDAOImpl();
		URLs = (HashMap<String, String>)getServletContext().getAttribute("URLs");
		webpages = (HashMap<String, String>)getServletContext().getAttribute("webpages");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(webpages.get("UPLOAD_TEST")).forward(request, response);
	}
	
	/*
	 * Do INSERT when user uploads new test
	 * Do UPDATE when user edits existing test
	 * Always redirect to /viewtest
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		String strRequestBody = MyIOUtils.readToString(request.getReader());
		Map<String, String> requestBody = MyStringUtils.parseHttpRequestBody(strRequestBody);
		
		Type type = Type.valueOf(requestBody.get("test_type"));
		String name = URLDecoder.decode(requestBody.get("test_name"), "UTF-8");
		String url = requestBody.get("test_file");
		String listeningFileUrl = requestBody.get("listening_file");
		String answer = parseAnswer(URLs.get("UPLOAD_HOST")
								+ requestBody.get("answer_file") + "/");
		int uploaderId = user.getId();
		String dbOperation = requestBody.get("DB_OPERATION");
		
		Test test = new Test();
		test.setType(type);
		test.setName(name);
		test.setUrl(url);
		test.setListeningFileUrl(listeningFileUrl);
		test.setAnswer(answer);
		test.setUploaderId(uploaderId);
		test.setUploadDate(LocalDate.now());
		test.setTakenCount(0);
		
		if (dbOperation.equals("INSERT")) {
			synchronized (getServletContext()) {
				test.setId(testDAO.count() + 1);
				testDAO.insert(test);
			}
		}
		if (dbOperation.equals("UPDATE")) {
			test.setId(Integer.parseInt(requestBody.get("test_id")));
			testDAO.update(test);
		}
		
		response.sendRedirect(URLs.get("VIEW_TEST") + "?id=" + test.getId());
	}
	
	/*
	 * Parse the answer file at the given URL into a String of continuous correct answers.
	 * This method eliminates any character sequence matches this regex: [^A-Za-z]+
	 */
	private static String parseAnswer(String fileURL) throws IOException {
		StringBuilder answer = new StringBuilder();
		URL file = new URL(fileURL);
		String fileContent = MyIOUtils.readToString(new InputStreamReader(file.openStream()));
		for (String ans : fileContent.replaceAll("\\s","").split("[^A-Za-z]+")) answer.append(ans);
		return answer.toString().toUpperCase();
	}

}