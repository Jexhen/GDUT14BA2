package xin.liaozhixing.class14ba2.domain;

public class Score {
	private String courseName;//�γ�����
	private String score;//�γ̳ɼ�
	private double credit;//�γ�ѧ��
	private double gradePoint;//�γ̼���
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public double getGradePoint() {
		return gradePoint;
	}
	public void setGradePoint(double gradePoint) {
		this.gradePoint = gradePoint;
	}
	@Override
	public String toString() {
		return "Score [courseName=" + courseName + ", score=" + score + ", credit=" + credit + ", gradePoint="
				+ gradePoint + "]";
	}
	
}
