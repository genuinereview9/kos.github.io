package dk.orsted.katalonapiutil

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.RestRequestObjectBuilder
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.testobject.impl.HttpFileBodyContent
import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import dk.orsted.katalonapiutil.ApiUtil as ApiUtil
import dk.orsted.katalonapiutil.VstsIntegration as VstsIntegration

public class VstsTestCaseUpdate {

	static String authToken  = 'cj3i2gb5bveglg5hflubzxweqayb35mlepum4mpvebkxfowtenna'
	static String username = 'xkaos'

	//public VstsTestCaseUpdate(){
	//}

	public static boolean fn_updateTestExecutionResult(String TV_PlanID, String TV_SuiteID, String TV_TCIDs, String testExecutionOutcome){
		String[] testCaseIds = TV_TCIDs.split(',')
		for( String TV_TCID : testCaseIds){
			def endPointURL = 'https://dongenergy-p.visualstudio.com/DefaultCollection/DnD%20ART/_apis/test/plans/' + TV_PlanID + '/suites/' + TV_SuiteID +'/points?testcaseid=' + TV_TCID
			def testPointId = VstsIntegration.fn_vstsGetTestPointID(username, authToken, endPointURL)
			def endpointURLPatchTestCase = 'https://dongenergy-p.visualstudio.com/DnD%20ART/_apis/test/Plans/' + TV_PlanID + '/suites/' + TV_SuiteID +'/points/' + testPointId + '?api-version=5.0&Content-Type=application/json'
			VstsIntegration.fn_vstsUpdateTestOutcome(username, authToken, endpointURLPatchTestCase, testExecutionOutcome)
		}
	}

	public static boolean fn_resetTestCaseStatusToActive(String TV_PlanID, String TV_SuiteID, String TV_TCIDs){
		String[] testCaseIds = TV_TCIDs.split(',')
		for( String TV_TCID : testCaseIds){
			def endPointURL = 'https://dongenergy-p.visualstudio.com/DefaultCollection/DnD%20ART/_apis/test/plans/' + TV_PlanID + '/suites/' + TV_SuiteID +'/points?testcaseid=' + TV_TCID
			println "Authtoken and User name inside::" + VstsTestCaseUpdate.username + VstsTestCaseUpdate.authToken
			def testPointId = VstsIntegration.fn_vstsGetTestPointID(username, authToken, endPointURL)
			def endpointURLPatchTestCase = 'https://dongenergy-p.visualstudio.com/DnD%20ART/_apis/test/Plans/' + TV_PlanID + '/suites/' + TV_SuiteID +'/points/' + testPointId + '?api-version=5.0&Content-Type=application/json'
			VstsIntegration.fn_vstsResetTestCaseToActive(username, authToken, endpointURLPatchTestCase)
		}
	}
}
