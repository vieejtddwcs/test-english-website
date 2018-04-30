package com.testenglish.controller.test;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testenglish.dao.impl.TestRecordDAOImpl;
import com.testenglish.dao.impl.UserDAOImpl;
import com.testenglish.model.test.TestRecord;
import com.testenglish.model.test.TestRecordComparator;
import com.testenglish.model.user.User;

@WebServlet("/testhistory")
public class ViewTestHistoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TestRecordDAOImpl testRecordDAO;
	private UserDAOImpl userDAO;
	private TestRecordComparator comparator;
	private Map<String, String> URLs;
	private Map<String, String> webpages;
	
	public ViewTestHistoryController() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		testRecordDAO = new TestRecordDAOImpl();
		userDAO = new UserDAOImpl();
		comparator = new TestRecordComparator();
		URLs = (HashMap<String, String>)getServletContext().getAttribute("URLs");
		webpages = (HashMap<String, String>)getServletContext().getAttribute("webpages");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		List<TestRecord> testHistory = null;
		String strUserId = request.getParameter("user_id");
		String strTestId = request.getParameter("test_id");
		String scoreOrder = request.getParameter("score");
		String takenDateOrder = request.getParameter("taken_date");
		String redirectURL = URLs.get("TEST_HISTORY") + "?user_id=$1&test_id=$2&score=$3&taken_date=$4";
		
		// Redirect non-params request
		if (strUserId == null || strTestId == null || scoreOrder == null || takenDateOrder == null) {
			response.sendRedirect(redirectURL.replace("$1", user.getId() + "")
											.replace("$2", "all")
											.replace("$3", "highest")
											.replace("$4", "newest"));
			return;
		}
		
		// Select testHistory
		int userId;
		try {
			userId = Integer.parseInt(strUserId);
			request.setAttribute("userName", userDAO.select(userId).getName());
		} catch (NumberFormatException e) {
			userId = user.getId();
			request.setAttribute("userName", user.getName());
		}
		
		int testId;
		try {
			testId = Integer.parseInt(strTestId);
		} catch (NumberFormatException e) {
			testId = 0;
		}
		
		if (testId == 0) testHistory = testRecordDAO.selectByUser(userId);
		else testHistory = testRecordDAO.selectByUserAndTest(userId, testId);
		
		// Sort testHistory by score
		if (scoreOrder.equals("lowest")) {
			Collections.sort(testHistory, comparator.sortBy("LOWEST_SCORE"));
			request.setAttribute("scoreOrder", scoreOrder);
		}
		else {
			Collections.sort(testHistory, comparator.sortBy("HIGHEST_SCORE"));
			request.setAttribute("scoreOrder", "highest");
		}
		
		// Sort testHistory by takenDate
		if (takenDateOrder.equals("oldest")) {
			Collections.sort(testHistory, comparator.sortBy("OLDEST_TAKEN_DATE"));
			request.setAttribute("takenDateOrder", takenDateOrder);
		}
		else {
			Collections.sort(testHistory, comparator.sortBy("NEWEST_TAKEN_DATE"));
			request.setAttribute("takenDateOrder", "newest");
		}
		
		request.setAttribute("testHistory", testHistory);
		request.getRequestDispatcher(webpages.get("TEST_HISTORY")).forward(request, response);
	}

}