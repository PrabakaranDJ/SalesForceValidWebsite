package com.salesforce.util;

import java.util.Hashtable;

public class TestUtil {
	
	
	public static boolean isExecutable(String testName, Xls_Reader xls){
		
		for(int rNum=2;rNum<=xls.getRowCount("Test Cases");rNum++){
			
			if(testName.equals(xls.getCellData("Test Cases", "TCID", rNum))){
				if(xls.getCellData("Test Cases", "Runmode", rNum).equals("Y"))
					return true;
				else
					return false;
			}
			
		}
		
		return false;	
	}
	
	public static Object[][] getData(String testName,Xls_Reader xls){
		int testCaseStartIndex=0;
		for(int rNum=1;rNum<=xls.getRowCount("Test Data");rNum++){
			if(testName.equals(xls.getCellData("Test Data", 0, rNum))){
				testCaseStartIndex=rNum;
				break;
			}	
		}
		System.out.println("Test Starts from - "+ testCaseStartIndex);
		
		int colStartIndex=testCaseStartIndex+1;
		int cols=0;
		while(!xls.getCellData("Test Data", cols, colStartIndex).equals("")){
			cols++;
		}
		System.out.println("Total cols are  - "+cols);
		
		int dataStartIndex=testCaseStartIndex+2;
		int rows=0;
		while(!xls.getCellData("Test Data", 0, (dataStartIndex+rows)).equals("")){
			rows++;
		}
		System.out.println("Total rows are - "+rows);
		Object[][] data = new Object[rows][1];
		Hashtable<String,String> table = null;
		for(int rNum=dataStartIndex;rNum<(dataStartIndex+rows);rNum++){
			table = new Hashtable<String,String>();
			for(int cNum=0;cNum<cols;cNum++){
				table.put(xls.getCellData("Test Data", cNum, colStartIndex), xls.getCellData("Test Data", cNum, rNum));
			}
			data[rNum-dataStartIndex][0]=table;
		}
	return data;

	}
	
	
	
	
	

}
