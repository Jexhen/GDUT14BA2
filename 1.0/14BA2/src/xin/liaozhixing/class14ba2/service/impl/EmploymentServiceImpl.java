package xin.liaozhixing.class14ba2.service.impl;

import java.util.ArrayList;
import java.util.List;

import xin.liaozhixing.class14ba2.dao.EmploymentDao;
import xin.liaozhixing.class14ba2.domain.EmployInfo;
import xin.liaozhixing.class14ba2.domain.Notice;
import xin.liaozhixing.class14ba2.service.EmploymentService;

public class EmploymentServiceImpl implements EmploymentService {
	private EmploymentDao employmentDao;
	
	public List<EmployInfo> getEmployInfos(Integer currentPage, Integer currentCount) {
		int index = (currentPage-1) * currentCount;
		List<EmployInfo> employInfos = employmentDao.getEmployInfos(index, currentCount);
		return employInfos;
	}

	@Override
	public Integer getEmploymentTotalCount() {
		return employmentDao.getEmploymentTotalCount();
	}

	@Override
	public List<EmployInfo> getHotEmployInfos() {
		return employmentDao.getHotEmployInfos();
	}

	public void setEmploymentDao(EmploymentDao employmentDao) {
		this.employmentDao = employmentDao;
	}

	@Override
	public EmployInfo getEmployInfoByEmp_info_id(Integer emp_info_id) {
		return employmentDao.getEmployInfoByEmp_info_id(emp_info_id);
	}

	@Override
	public List<EmployInfo> getRecentEmployInfo() {
		return employmentDao.getRecentEmployInfo();
	}

	@Override
	public void updateEmployInfo(EmployInfo employInfo) {
		if (employInfo!=null)
			employmentDao.updateEmployInfo(employInfo);
	}

	@Override
	public boolean delEmployInfo(String emp_info_id) {
		if (emp_info_id!=null&&!emp_info_id.isEmpty()) {
			employmentDao.delEmployInfo(Integer.valueOf(emp_info_id));
			return true;
		}
		return false;
	}

	@Override
	public void saveEmployInfo(EmployInfo employInfo) {
		if (employInfo!=null)
			employmentDao.saveEmployInfo(employInfo);
	}

	@Override
	public List<EmployInfo> getEmployInfosBySearchCondition(String emp_info_title, String stu_name,
			String emp_info_date) {
		List<EmployInfo> employInfosByCondition = employmentDao.getEmployInfosBySearchCondition(emp_info_title, emp_info_date);
		//stu_name为空，只需要检索通知题目和通知发布日，调用getNoticesBySearchCondition方法即可
		if (stu_name==null||stu_name.isEmpty()) {
			return employInfosByCondition;
		} else if (( emp_info_title==null||emp_info_title.isEmpty())&&( emp_info_date==null|| emp_info_date.isEmpty())){
		//stu_name不为空且其他两者皆为空，要检索全部该学生发布的通知，所以获取全部通知，然后从通知中筛选出是发布者包含该学生的通知
			List<EmployInfo> allEmployInfos = employmentDao.getAllEmployInfos();
			if (allEmployInfos!=null&&!allEmployInfos.isEmpty()) {
				List<EmployInfo> employInfos = new ArrayList<EmployInfo>();
				for (EmployInfo ei : allEmployInfos) {
					if (ei.getStudent().getStu_name().contains(stu_name)) {
						employInfos.add(ei);
					}
				}
				return employInfos;
			}
		} else {
		//stu_name不为空且其他两者有一者不空,其他两者不为空的结果已经用getNoticesBySearchCondition方法获得了，再从中求出与该学生的交集即可
			if (employInfosByCondition!=null&&!employInfosByCondition.isEmpty()) {
				List<EmployInfo> employInfos = new ArrayList<EmployInfo>();
				for (EmployInfo ei : employInfosByCondition) {
					if (ei.getStudent().getStu_name().contains(stu_name)) {
						employInfos.add(ei);
					}
				}
				return employInfos;
			}
		}
		return null;
	}

}
