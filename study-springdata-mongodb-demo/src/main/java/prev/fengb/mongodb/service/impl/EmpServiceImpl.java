package prev.fengb.mongodb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import prev.fengb.mongodb.model.Emp;
import prev.fengb.mongodb.service.IEmpService;

/**
 * 职工基础增删改查Service实现
 *
 * @author fengb
 * @date 2020年12月3日 上午10:52:19
 */
@Service
@Slf4j
public class EmpServiceImpl implements IEmpService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Emp> list() {
		Query query = new Query(new Criteria()).with(Sort.by(Direction.DESC, "name"));
		List<Emp> list = mongoTemplate.find(query, Emp.class);
		return list;
	}

	@Override
	public Emp getById(String id) {
		Emp emp = mongoTemplate.findById(id, Emp.class);
		return emp;
	}

	@Override
	public int add(Emp emp) {
		String uuid = IdUtil.fastUUID();
		emp.setId(uuid);
		log.info("【增加职工】生成唯一标识的职工ID：" + uuid);
		mongoTemplate.insert(emp);
		return 1;
	}

	@Override
	public int deleteAll() {
		mongoTemplate.dropCollection(Emp.class);
		return 1;
	}

}
