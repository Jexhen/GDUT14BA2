package xin.liaozhixing.class14ba2.service;

import java.util.List;

import xin.liaozhixing.class14ba2.domain.EmployInfo;
import xin.liaozhixing.class14ba2.domain.Notice;

public interface EmploymentService {
	public List<EmployInfo> getEmployInfos(Integer currentPage, Integer currentCount);
	public Integer getEmploymentTotalCount();
	public List<EmployInfo> getHotEmployInfos();
	public EmployInfo getEmployInfoByEmp_info_id(Integer emp_info_id);
	public List<EmployInfo> getRecentEmployInfo();
	public void updateEmployInfo(EmployInfo employInfo);
	public boolean delEmployInfo(String emp_info_id);
	public void saveEmployInfo(EmployInfo employInfo);
	public List<EmployInfo> getEmployInfosBySearchCondition(String emp_info_title, String stu_name,
			String emp_info_date);
}
