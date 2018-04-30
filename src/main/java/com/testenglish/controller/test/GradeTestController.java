package com.testenglish.controller.test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testenglish.dao.impl.TestDAOImpl;
import com.testenglish.dao.impl.TestRecordDAOImpl;
import com.testenglish.dao.impl.TestTypeDAOImpl;
import com.testenglish.model.test.Test;
import com.testenglish.model.test.TestRecord;
import com.testenglish.model.test.TestResult;
import com.testenglish.model.test.TestType;
import com.testenglish.model.user.User;
import com.testenglish.service.GradingStrategy;
import com.testenglish.service.GradingStrategyFactory;
import com.testenglish.service.TestGrader;

@WebServlet("/gradetest")
public class GradeTestController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TestDAOImpl testDAO;
	private TestTypeDAOImpl testTypeDAO;
	private TestRecordDAOImpl testRecordDAO;
	private Map<String, String> URLs;
	
	public GradeTestController() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		testDAO = new TestDAOImpl();
		testTypeDAO = new TestTypeDAOImpl();
		testRecordDAO = new TestRecordDAOImpl();
		URLs = (HashMap<String, String>)getServletContext().getAttribute("URLs");
	}
	
	/*
	 * If testId is invalid, redirect to /testlist
	 * Else redirect to /viewresult
	 * Only one test can be graded at a time
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		String strTestId = request.getParameter("test_id");
		try {
			int testId = Integer.parseInt(strTestId);
			int testRecordId = 0;
			synchronized (getServletContext()) {
				// Get required objects
				Test test = testDAO.select(testId);
				TestType testType = testTypeDAO.selectByType(test.getType());
				
				// Grade the test
				String userAnswer = getUserAnswer(request,
						testType.getTotalListeningQuestions() + testType.getTotalReadingQuestions());
				String correctAnswer = test.getAnswer();
				GradingStrategy strategy = GradingStrategyFactory.getStrategy(test.getType());
				
				TestResult result = TestGrader.grade(userAnswer, correctAnswer, strategy);
				
				// Create and save test result
				TestRecord testRecord = new TestRecord();
				testRecord.setId(testRecordDAO.count() + 1);
				testRecord.setUserId(user.getId());
				testRecord.setTestId(test.getId());
				testRecord.setUserAnswer(userAnswer);
				testRecord.setResult(result);
				testRecord.setTakenDate(LocalDate.now());
				
				testRecordDAO.insert(testRecord);
				testRecordId = testRecord.getId();
				
				// Update test taken count
				test.setTakenCount(test.getTakenCount() + 1);
				testDAO.update(test);
			}
			
			response.sendRedirect(URLs.get("VIEW_RESULT") + "?id=" + testRecordId);
			
		} catch (NumberFormatException | NullPointerException e) {
			response.sendRedirect(URLs.get("TEST_LIST"));
		}
	}
	
	/*
	 * Get user answers from request parameters as a String.
	 */
	private String getUserAnswer(HttpServletRequest request, int totalQuestions) {
		StringBuilder userAnswer = new StringBuilder();
		for (int i = 1; i <= totalQuestions; i++) {
			userAnswer.append(request.getParameter("answer" + i));
		}
		return userAnswer.toString();
	}

}