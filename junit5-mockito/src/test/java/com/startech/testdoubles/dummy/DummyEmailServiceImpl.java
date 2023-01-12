package com.startech.testdoubles.dummy;

import com.startech.testdouble.dummy.EmailService;

public class DummyEmailServiceImpl implements EmailService{

	@Override
	public void sendEmail(String message) {
		throw new AssertionError("Method not implemented..");
	}

}
