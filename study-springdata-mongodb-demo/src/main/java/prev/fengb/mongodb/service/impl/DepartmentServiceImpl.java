package prev.fengb.mongodb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import prev.fengb.mongodb.model.Department;
import prev.fengb.mongodb.service.IDepartmentService;

/**
 * 部门基础增删改查Service实现类
 *
 * @author fengb
 * @date 2020年12月2日 下午11:57:22
 */
@Service
@Slf4j
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Department> list() {
//		mongoTemplate.findAll(Department.class);
		Query query = new Query(new Criteria()).with(Sort.by(Order.desc("bossId"))); // 排序为什么不生效
		List<Department> list = mongoTemplate.find(query, Department.class);
		return list;
	}

	@Override
	public Department getById(String id) {
		Department department = mongoTemplate.findById(id, Department.class);
		return department;
	}

	@Override
	public int add(Department department) {
		String uuid = IdUtil.fastUUID();
		log.info("【增加部门】生成唯一标识的部门ID：" + uuid);
		department.setId(uuid);
		mongoTemplate.insert(department);
		return 1;
	}

	@Override
	public int addMulti(List<Department> departments) {
		BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkMode.UNORDERED, Department.class);
		if (departments != null && !departments.isEmpty()) {
			departments.forEach(department -> {
				String uuid = IdUtil.fastUUID();
				log.info("【增加多个部门】生成唯一标识的部门ID：" + uuid);
				department.setId(uuid);
				bulkOperations.insert(department);
			});
		}
		BulkWriteResult bulkWriteResult = bulkOperations.execute();
		int modifiedCount = bulkWriteResult.getModifiedCount();
		return modifiedCount;

//		Collection<Department> result = mongoTemplate.insertAll(departments);
//		return result.size();
	}

	@Override
	public int update(Department department) {
		Query query = new Query(Criteria.where("_id").is(department.getId()));
		Update update = new Update().set("name", department.getName()).set("bossId", department.getBossId());
		UpdateResult updateResult = mongoTemplate.updateMulti(query, update, Department.class);
		log.info(updateResult.toString());
		long modifiedCount = updateResult.getModifiedCount();
		long matchedCount = updateResult.getMatchedCount();
		log.info("modifiedCount:" + modifiedCount);
		log.info("matchedCount:" + matchedCount);
//		BsonValue upsertedId = updateResult.getUpsertedId();
//		log.info(upsertedId.toString());
//		BsonArray array = upsertedId.asArray();
//		array.forEach(bson -> log.info(bson.toString()));
		return (int) modifiedCount;
	}

	@Override
	public int deleteById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		DeleteResult deleteResult = mongoTemplate.remove(query, Department.class);
		long deletedCount = deleteResult.getDeletedCount();
		return (int) deletedCount;
	}

}
