package xin.liaozhixing.class14ba2.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.NoRouteToHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import xin.liaozhixing.class14ba2.domain.Score;


public class JWGL {
	private final static String HOST = "http://222.200.98.146";//ѧУ����ϵͳ��ַ
	private final static String VERIFY_CODE_URI = "/yzm";//��ȡ��֤���URI
	private final static String LOGIN_URI = "/login!doLogin.action";//�����¼�����URI
	private final static String LOGOUT_URI = "/login!logout.action";
	private final static String HOME_URI = "/login!welcome.action";//��ҳ��URI
	private final static String SCORE_REFERER_URI = "/xskccjxx!xskccjList.action";//��Ҫ���͵���ѯ�ɼ���Referer
	private final static String SCORE_URI = "/xskccjxx!getDataList.action";//�����ѯ�ɼ������URI
	private final static String COURSE_REFERER_URI = "/xsgrkbcx!xskbList.action";//��Ҫ���͵���ѯ�α��Referer
	private final static String COURSE_URI = "/xsgrkbcx!getKbRq.action";//�����ѯ�α������URI
	private String usrname;//ѧ��
	private String password;//����
	private String verifyCode;//��֤��
	private static CloseableHttpClient client = HttpClients.createDefault();//HttpClient
	private String stuName;//��¼�ɹ���ѧ������
	
	
	public JWGL(String usrname, String password, String verifyCode) {
		this.usrname = usrname;
		this.password = password;
		this.verifyCode = verifyCode;
	}
	
	/**
	 * ��ȡ��֤��
	 */
	public static String getVerifyCode() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		HttpGet verifyCodeGet = null;
		FileOutputStream out = null;
		try {
			verifyCodeGet = new HttpGet(HOST + VERIFY_CODE_URI);
			CloseableHttpResponse verifyCodeResponse = client.execute(verifyCodeGet);
			HttpEntity entity = verifyCodeResponse.getEntity();
			String filename = "verifycode_"+uuid+".jpeg";
			String realPath = ServletActionContext.getServletContext().getRealPath("/img/verifyCode");
			File file = new File(realPath+File.separator+filename);
			if(!file.exists()){
			    //�ȵõ��ļ����ϼ�Ŀ¼���������ϼ�Ŀ¼���ڴ����ļ�
			    file.getParentFile().mkdir();
			    try {
			        //�����ļ�
			        file.createNewFile();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			}
			out = new FileOutputStream(file);
			entity.writeTo(out);
		} catch (NoRouteToHostException e) {
			throw new RuntimeException("�޷����ӵ�����ϵͳ");
		} catch (IOException e) {
			throw new RuntimeException("�޷���ȡ��֤��");
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					throw new RuntimeException("�޷���ȡ��֤��");
				}
			}
			if (verifyCodeGet != null) {
				verifyCodeGet.abort();
			}
		}
		return uuid;
	}
	
	/**
	 * ��¼����ϵͳ
	 * @return ����״̬��1����¼�ɹ���0���˺Ż����������-1������δ����
	 */
	public int login() {
		int statusCode = 0;
		HttpPost loginPost = null;
		HttpGet welGet = null;
		try {
			loginPost = new HttpPost(HOST + LOGIN_URI);
			//�ύ��post���Ĳ���
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			NameValuePair account = new BasicNameValuePair("account", usrname);
			NameValuePair pwd = new BasicNameValuePair("pwd", password);
			NameValuePair verifycode = new BasicNameValuePair("verifycode", verifyCode);
			nameValuePairs.add(account);
			nameValuePairs.add(pwd);
			nameValuePairs.add(verifycode);
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
			loginPost.setEntity(entity);
			client.execute(loginPost);
			//����Get
			welGet = new HttpGet(HOST + HOME_URI);
			//Ϊget���ñ�Ҫ��Header
			welGet.setHeader("Referer", HOST);
			//������Ӧresponse
			HttpResponse welResponse = client.execute(welGet);
			HttpEntity httpEntity = welResponse.getEntity();
			String content = EntityUtils.toString(httpEntity);//��ȡhtml����
			//������֪�����¼�ɹ��õ���html���ݰ�����¼ʱ�䣬�����ж����޵�¼�ɹ������ж������Ƿ��������¼ʱ�䡱���Ӵ�
			if (content.contains("��¼ʱ��")) {
				statusCode = 1;
				Matcher m =  Pattern.compile("\"top\">[\\s]*[^\\x00-\\xff[a-zA-Z]]*").matcher(content);
				if (m.find()) {
					stuName = m.group().replaceAll("\"top\">\\s+", "");
				}
			} else {
				statusCode = 0;
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("�������ڲ��쳣(UnsupportedEncodingException)������ϵ��վ����Ա");
		} catch (ClientProtocolException e) {
			throw new RuntimeException("�������ڲ��쳣(ClientProtocolException)������ϵ��վ����Ա");
		} catch (IOException e) {
			throw new RuntimeException("�������ڲ��쳣(IOException)������ϵ��վ����Ա");
		} finally {
			if (loginPost != null) {
				loginPost.abort();
			}
			if (welGet != null) {
				welGet.abort();
			}
		}
		return statusCode;
	}
	
	/**
	 * ����ѧ��ѧ�ڴ����ý����������ץȡ������Ϣ����������Ϣ��װ��ÿһ��Score������
	 * @param yearSemesterCode ѧ��ѧ�ڴ���
	 * @return ����һ����Score���󹹳ɵ�List
	 */
	private List<Score> queryResult(String yearSemesterCode) {
		List<Score> scores = new ArrayList<Score>();//�洢ץȡ���Ŀγ̳ɼ�
		HttpPost queryResultPost = null;
		try {
			//����post����
			queryResultPost = new HttpPost(HOST + SCORE_URI);
			//���һЩ��Ҫ��Header��Ϣ
			queryResultPost.addHeader("Referer", HOST + SCORE_REFERER_URI + "?firstquery=" + 1);
			//��Ҫ�ύ�ı�����
			NameValuePair xnxq = new BasicNameValuePair("xnxqdm", yearSemesterCode);//ѧ��ѧ�ڴ���
			NameValuePair jhlx = new BasicNameValuePair("jhlxdm", "");//
			NameValuePair page = new BasicNameValuePair("page", "1");//�ڶ���ҳ
			NameValuePair rows = new BasicNameValuePair("rows", "50");//ÿҳ��ʾ������
			NameValuePair sort = new BasicNameValuePair("sort", "xnxqdm");//��������
			NameValuePair order = new BasicNameValuePair("order", "asc");//�������
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(xnxq);
			nameValuePairs.add(jhlx);
			nameValuePairs.add(page);
			nameValuePairs.add(rows);
			nameValuePairs.add(sort);
			nameValuePairs.add(order);
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
			queryResultPost.setEntity(entity);//����������װ��Post����
			//���Ի��Response
			HttpResponse queryResultResponse = client.execute(queryResultPost);
			HttpEntity httpEntity = queryResultResponse.getEntity();
			String content = EntityUtils.toString(httpEntity);//��ȡ��õ�html����
			//��Gson��ȡjson�����е���Чֵ
			JsonParser parser = new JsonParser();
			JsonElement jsonEl = parser.parse(content);
			JsonObject jsonObj = jsonEl.getAsJsonObject();
			JsonArray scoreArr = jsonObj.get("rows").getAsJsonArray();
			for (int i = 0; i < scoreArr.size(); i++) {
				JsonElement jsonElement = scoreArr.get(i);
				JsonObject jsonObject = jsonElement.getAsJsonObject();
				Score scoreBean = new Score();
				//��ȡ�γ�����
				String courseName = jsonObject.get("kcmc").toString();
				courseName = deleteQuote(courseName);
				scoreBean.setCourseName(courseName);
				//��ȡ�γ̳ɼ�
				String score = jsonObject.get("zcj").toString();
				score = deleteQuote(score);
				scoreBean.setScore(score);
				//��ȡ�γ�ѧ��
				String credit = jsonObject.get("xf").toString();
				scoreBean.setCredit(Double.parseDouble(deleteQuote(credit)));
				//��ȡ�γ̼���
				String gradePoint = jsonObject.get("cjjd").toString();
				scoreBean.setGradePoint(Double.parseDouble(deleteQuote(gradePoint)));
				scores.add(scoreBean);//����ȡ�Ŀγ̳ɼ���ӵ����ϵ���
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (queryResultPost != null) {
				queryResultPost.abort();
			}
		}
		return scores;
	}
	
	/**
	 * ����ƽ������
	 * @param scores ��Ҫ���㼨������гɼ���Score����List
	 * @return double�͵�ƽ������ֵ
	 */
	public double countGPA(List<Score> scores) {
		double allCreditScoreSum = 0.0;
		double allCreditSum = 0.0;
		
		for (int i = 0; i < scores.size(); i++) {
			Score course = scores.get(i);
			allCreditScoreSum +=  course.getCredit() * course.getGradePoint();
			allCreditSum += course.getCredit();
		}
		return allCreditScoreSum / allCreditSum;
	}
	
	
	/**
	 * �������õĻ�ȡ�ɼ��ķ���,���������ڻ�ȡһ��ѧ��ĳɼ�
	 * @param schoolYear ��Ҫ��ѯ�ɼ�������ѧ��
	 * @return ���ز�ѯ�����
	 */
	public List<Score> getScores(int schoolYear) {
		List<Score> scores = new ArrayList<Score>();//�洢���в�ѯ�����List
		List<Score> temp = queryResult("" + schoolYear + "01");//��ѯ��ѧ���һѧ�ڵĳɼ�
		scores.addAll(temp);//��һѧ�ڵ����н����������ؽ����List��
		temp.clear();//�ظ�������ʱ����ѧ�ڽ����List,�ڶ��ε���ʱӦ�������һ�εĽ��
		temp = queryResult("" + schoolYear + "02");//��ѯ��ѧ��ڶ�ѧ�ڵĽ��
		scores.addAll(temp);//���ڶ�ѧ�ڵĲ�ѯ���ȫ���Ž����ս����
		return scores;
	}
	
	/**
	 * ���ع������õĻ�ȡ�ɼ��ķ���,���������ڻ�ȡһ��ѧ�ڵĳɼ�
	 * @param yearSemesterCode ��Ҫ��ѯ��ѧ��ѧ�ڴ���
	 * @return
	 */
	public List<Score> getScores(String yearSemesterCode) {
		List<Score> scores = queryResult(yearSemesterCode);
		return scores;
	}
	
	/**
	 * ���ݵ�ǰ��ݴӺ���ǰ��ѯ���гɼ�
	 * @param endYear
	 * @return
	 */
	@Deprecated
	public List<Score> getAllScores(int endYear) {
		List<Score> scores = new ArrayList<Score>();
		List<Score> temp = null;
		int flag = 0;//���ڵ�һ��û�ҵ��ļ�����ǰ��̽��־���������̽3��
		//����ҵ����ǿյļ�����ǰ��̽�������ֻ����̽3��
		for (int i = endYear; !((temp=getScores(i)).isEmpty()) || flag < 3; i--) {
			if (!temp.isEmpty())
				scores.addAll(temp);
			flag++;
		}
		return scores;
	}
	
	public List<Score> getAllScores() {
		List<Score> scores = new ArrayList<Score>();//�洢ץȡ���Ŀγ̳ɼ�
		HttpPost queryResultPost = null;
		try {
			//����post����
			queryResultPost = new HttpPost(HOST + SCORE_URI);
			//���һЩ��Ҫ��Header��Ϣ
			queryResultPost.addHeader("Referer", HOST + SCORE_REFERER_URI + "?firstquery=" + 1);
			//��Ҫ�ύ�ı�����
			NameValuePair xnxq = new BasicNameValuePair("xnxqdm", "");//ѧ��ѧ�ڴ���
			NameValuePair jhlx = new BasicNameValuePair("jhlxdm", "");//
			NameValuePair page = new BasicNameValuePair("page", "1");//�ڶ���ҳ
			NameValuePair rows = new BasicNameValuePair("rows", "50");//ÿҳ��ʾ������
			NameValuePair sort = new BasicNameValuePair("sort", "xnxqdm");//��������
			NameValuePair order = new BasicNameValuePair("order", "asc");//�������
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(xnxq);
			nameValuePairs.add(jhlx);
			nameValuePairs.add(page);
			nameValuePairs.add(rows);
			nameValuePairs.add(sort);
			nameValuePairs.add(order);
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
			queryResultPost.setEntity(entity);//����������װ��Post����
			//���Ի��Response
			HttpResponse queryResultResponse = client.execute(queryResultPost);
			HttpEntity httpEntity = queryResultResponse.getEntity();
			String content = EntityUtils.toString(httpEntity);//��ȡ��õ�html����
			//��Gson��ȡjson�����е���Чֵ
			JsonParser parser = new JsonParser();
			JsonElement jsonEl = parser.parse(content);
			JsonObject jsonObj = jsonEl.getAsJsonObject();
			JsonArray scoreArr = jsonObj.get("rows").getAsJsonArray();
			for (int i = 0; i < scoreArr.size(); i++) {
				JsonElement jsonElement = scoreArr.get(i);
				JsonObject jsonObject = jsonElement.getAsJsonObject();
				Score scoreBean = new Score();
				//��ȡ�γ�����
				String courseName = jsonObject.get("kcmc").toString();
				courseName = deleteQuote(courseName);
				scoreBean.setCourseName(courseName);
				//��ȡ�γ̳ɼ�
				String score = jsonObject.get("zcj").toString();
				score = deleteQuote(score);
				scoreBean.setScore(score);
				//��ȡ�γ�ѧ��
				String credit = jsonObject.get("xf").toString();
				scoreBean.setCredit(Double.parseDouble(deleteQuote(credit)));
				//��ȡ�γ̼���
				String gradePoint = jsonObject.get("cjjd").toString();
				scoreBean.setGradePoint(Double.parseDouble(deleteQuote(gradePoint)));
				scores.add(scoreBean);//����ȡ�Ŀγ̳ɼ���ӵ����ϵ���
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (queryResultPost != null) {
				queryResultPost.abort();
			}
		}
		return scores;
	}
	
	/**
	 * ��ȡ��¼�ɹ�������
	 * @return String����
	 */
	public String getStuName() {
		return stuName;
	}
	
	public void logOut() {
		HttpGet logOutRequest = null;
		try {
			logOutRequest = new HttpGet(HOST + LOGOUT_URI);
			logOutRequest.addHeader("Referer", HOST + HOME_URI);
			client.execute(logOutRequest);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (logOutRequest != null) {
				logOutRequest.abort();
			}
		}
	}
	
	/**
	 * ɾ��ץȡ�������ж��������
	 * @param s ץȡ������
	 * @return ����ȥ�������ŵ��ַ���
	 */
	private String deleteQuote(String s) {
		return s.replaceAll("\"", "");
	}
}
