package com.testenglish.model.test;

import java.util.Comparator;

public class TestRecordComparator implements Comparator<TestRecord> {
	
	private String order = "NEWEST_TAKEN_DATE";
	
	/**
	 * Return a TestRecordComparator object used to sort TestRecord Collections by the specified order.
	 * 
	 * @param order - the specified order, "NEWEST_TAKEN_DATE" by default
	 * @return this TestRecordComparator object
	 */
	public TestRecordComparator sortBy(String order) {
		this.order = order;
		return this;
	}

	@Override
	public int compare(TestRecord tr1, TestRecord tr2) {
		return compare(tr1, tr2, order);
	}
	
	private int compare(TestRecord tr1, TestRecord tr2, String order) {
		int returnVal = 0;
		TestResult r1 = tr1.getResult();
		TestResult r2 = tr2.getResult();
		
		switch (order) {
			case "NEWEST_TAKEN_DATE":
				if (tr1.getTakenDate().isBefore(tr2.getTakenDate())) returnVal = -1;
				if (tr1.getTakenDate().isAfter(tr2.getTakenDate())) returnVal = 1;
				break;
			case "OLDEST_TAKEN_DATE":
				if (tr1.getTakenDate().isAfter(tr2.getTakenDate())) returnVal = -1;
				if (tr1.getTakenDate().isBefore(tr2.getTakenDate())) returnVal = 1;
				break;
			case "HIGHEST_SCORE":
				returnVal = r2.getListeningScore() + r2.getReadingScore()
					- r1.getListeningScore() - r1.getReadingScore();
				break;
			case "LOWEST_SCORE":
				returnVal = r1.getListeningScore() + r1.getReadingScore()
					- r2.getListeningScore() - r2.getReadingScore();
				break;
			default:
				returnVal = 0;
				break;
		}
		return returnVal;
	}
	
}