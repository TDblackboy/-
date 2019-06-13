package dao;

import java.util.List;

import modal.ExceptionData;

public interface ExceptionDataDao {
	List<ExceptionData> load();
	List<ExceptionData> load(int n);
	List<ExceptionData> load(String location);
	List<ExceptionData> loadByData(String yyMM);
	void insertExceptionData(ExceptionData ed);
}
