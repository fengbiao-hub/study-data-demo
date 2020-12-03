package prev.fengb.mongodb.service;

import java.util.List;

import prev.fengb.mongodb.model.Department;

/**
 * 部门基础增删改查Service接口
 *
 * @author fengb
 * @date 2020年12月2日 下午11:56:33
 */
public interface IDepartmentService {

	List<Department> list();

	Department getById(String id);

	int add(Department department);
	
	int addMulti(List<Department> departments);

	int update(Department department);

	int deleteById(String id);
}
