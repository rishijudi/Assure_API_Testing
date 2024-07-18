package com.as.qa.utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManger {
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static String dateName;
	public static String  Screenshotpath ;
	

	public static void setExtent(String type) {
		dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String path = System.getProperty("user.dir") + "\\Report\\Node_"+type+"Report" + dateName.toString();
        File file = new File(path);
        file.mkdir();
        Screenshotpath = path + "\\Screenshot";
        File screenfile = new File(Screenshotpath);
        screenfile.mkdir();
        htmlReporter = new ExtentHtmlReporter(path+"\\Node_"+type+"_Report.html");
        htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent.config.xml");
		//htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/ExtentReport/"+"MyReport_"+BaseClass.getCurrentTime()+".html");
		/*dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/ExtentReport/"+"MyReport_"+dateName+".html");
		*/
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("HostName", "INVCH-LTM-063");
		extent.setSystemInfo("ProjectName", "BIM CLASSIFY");
		extent.setSystemInfo("Tester", "VASU SEKAR");
		extent.setSystemInfo("OS", "Win11");
		extent.setSystemInfo("Browser", "Chrome");
	}

	public static void endReport() {
		extent.flush();
	}

}
