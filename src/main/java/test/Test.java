package test;

import java.text.NumberFormat;

public class Test {

	public static void main(String[] args) {
		
		//创建异常数据的模拟数据
		//SimulateData.simulatExceptionData();
		//创建百分数
		NumberFormat format=NumberFormat.getPercentInstance();
		float s=(float) (1.0/3);
		
		float ss=1+5+6;
		String perc1=format.format(ss/5);
		System.out.println(ss+"="+perc1);
	}

}
