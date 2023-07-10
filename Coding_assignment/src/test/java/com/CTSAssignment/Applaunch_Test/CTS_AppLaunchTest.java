package com.CTSAssignment.Applaunch_Test;

import org.testng.annotations.Test;
import com.assignment.Applaunch.Assignment_Applaunch;
import com.assignment.utility.BaseTest_Listener;

public class CTS_AppLaunchTest extends BaseTest_Listener {

	public Assignment_Applaunch CTSassignment;

	@Test(enabled = true, priority = 0)
	public void Coding() throws Throwable {
		CTSassignment = new Assignment_Applaunch(driver);
		CTSassignment.jsonFileCompareWithApplication();
	}

}
