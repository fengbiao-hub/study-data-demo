package prev.fengb.mongodb.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 职工类
 *
 * @author fengb
 * @date 2020年12月2日 下午11:42:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "emps")
public class Emp {

	@Id
	private String id;
	/** 名字 */
	private String name;
	/** 入职时间 */
	private Date hiredate;
	/** 工资 */
	private double salary;
	/** 家乡 */
	private Address hometown;
	/** 爱好 */
	private String[] hobbies;
	/** 上级领导ID */
	private String managerId;
	/** 部门ID */
	private String departmentId;
}
