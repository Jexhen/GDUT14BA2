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
		//stu_nameΪ�գ�ֻ��Ҫ����֪ͨ��Ŀ��֪ͨ�����գ�����getNoticesBySearchCondition��������
		if (stu_name==null||stu_name.isEmpty()) {
			return employInfosByCondition;
		} else if (( emp_info_title==null||emp_info_title.isEmpty())&&( emp_info_date==null|| emp_info_date.isEmpty())){
		//stu_name��Ϊ�����������߽�Ϊ�գ�Ҫ����ȫ����ѧ��������֪ͨ�����Ի�ȡȫ��֪ͨ��Ȼ���֪ͨ��ɸѡ���Ƿ����߰�����ѧ����֪ͨ
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
		//stu_name��Ϊ��������������һ�߲���,�������߲�Ϊ�յĽ���Ѿ���getNoticesBySearchCondition��������ˣ��ٴ���������ѧ���Ľ�������
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
