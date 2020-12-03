package prev.fengb.mongodb.service;

import java.util.List;

import prev.fengb.mongodb.model.Emp;

/**
 * 职工基础增删改查Service接口
 *
 * @author fengb
 * @date 2020年12月3日 上午10:40:15
 */
public interface IEmpService {

	List<Emp> list();

	Emp getById(String id);

	int add(Emp emp);

	int deleteAll();
}
