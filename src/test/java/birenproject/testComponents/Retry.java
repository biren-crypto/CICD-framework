package birenproject.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	int count =0;
	int maxtry =1;
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxtry) {
			
			count++;
			return true;
		}
		
		return false;
	}

}
// in result all the meta data like method name pass fail all are present 