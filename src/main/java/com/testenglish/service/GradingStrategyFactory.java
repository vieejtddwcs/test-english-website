package com.testenglish.service;

import com.testenglish.model.test.Test;
import com.testenglish.model.test.Test.Type;
import com.testenglish.service.impl.TOEICGradingStrategy;

public class GradingStrategyFactory {

	private GradingStrategyFactory() {
		throw new AssertionError();
	}
	
	public static GradingStrategy getStrategy(Type type) {
		if (type == Test.Type.TOEIC) return TOEICGradingStrategy.getInstance();
		return null;
	}
	
}