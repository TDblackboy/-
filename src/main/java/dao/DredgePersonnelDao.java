package dao;

import java.util.List;

import modal.DredgePersonnel;

/**
 *  对疏导人员的操作
 */

public interface DredgePersonnelDao {
	List<DredgePersonnel> load(String location);//按监控位置查询疏导人员；并且时模糊查询
	List<DredgePersonnel> load(int i,String name);//按疏导人员的名字查询疏导人员；并且时模糊查询 //参数i 没有用！
}
