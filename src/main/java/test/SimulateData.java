package test;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

import dao.ExceptionDataDao;
import dao.ExceptionDataDaoImp;
import modal.ExceptionData;

public class SimulateData {

	public static void simulatExceptionData() {
		
		int count=20;
		for(int i=0;i<count;i++) {
			//产生模拟数据
			
			//location
			String locations[]= {"基教一层112","基教一层106","基教一层111","基教一层105"};
			//int n=(int)(new Random().nextInt(3));
			String location=locations[(int)(new Random().nextInt(3))];
			
			//persons
			int max=20;
			String persons=String.valueOf(new Random().nextInt(max));
			
			//date
			int year=2019;
			//int months[]= {1,2,3,4,5,6,7,8,9,10,11,12};
			int month=new Random().nextInt(12)+1;
			int day;
			if(month==2) {
				day=new Random().nextInt(28)+1;
			}else {
				day=new Random().nextInt(30)+1;
			}
			String datatime=year+"-"+month+"-"+day;
			
			//state
			String state="0";
			if(Integer.parseInt(persons)>10) {
				state="1";
			}
			
			//添加
			ExceptionData ed=new ExceptionData();
			ed.setLocation(location);
			ed.setPersons(persons);
			ed.setDatatime(datatime);
			ed.setState(state);
			
			ExceptionDataDao dao=new ExceptionDataDaoImp();
			dao.insertExceptionData(ed);
			
		}
		System.out.println("添加"+count+"条异常数据");
		
	}
	
}
