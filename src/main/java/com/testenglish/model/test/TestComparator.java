package com.testenglish.model.test;

import java.util.Comparator;

public class TestComparator implements Comparator<Test> {
	
	private String order = "NEWEST_UPLOAD_DATE";
	
	/**
	 * Return a TestComparator object used to sort Test Collections by the specified order.
	 * 
	 * @param order - the specified order, "NEWEST_UPLOAD_DATE" by default
	 * @return this TestComparator object
	 */
	public TestComparator sortBy(String order) {
		this.order = order;
		return this;
	}

	@Override
	public int compare(Test t1, Test t2) {
		return compare(t1, t2, order);
	}
	
	private int compare(Test t1, Test t2, String order) {
		int returnVal = 0;
		switch (order) {
			case "NEWEST_UPLOAD_DATE":
				if (t1.getUploadDate().isBefore(t2.getUploadDate())) returnVal = -1;
				if (t1.getUploadDate().isAfter(t2.getUploadDate())) returnVal = 1;
				break;
			case "OLDEST_UPLOAD_DATE":
				if (t1.getUploadDate().isAfter(t2.getUploadDate())) returnVal = -1;
				if (t1.getUploadDate().isBefore(t2.getUploadDate())) returnVal = 1;
				break;
			case "MOST_TAKEN_COUNT":
				returnVal = t2.getTakenCount() - t1.getTakenCount();
				break;
			case "LEAST_TAKEN_COUNT":
				returnVal = t1.getTakenCount() - t2.getTakenCount();
				break;
			default:
				returnVal = 0;
				break;
		}
		return returnVal;
	}
	
}