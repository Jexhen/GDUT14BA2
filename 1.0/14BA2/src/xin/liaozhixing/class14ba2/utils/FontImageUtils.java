package xin.liaozhixing.class14ba2.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

public class FontImageUtils {
	

	/**
	 * ==================================================
	 * <p/>
	 * ��Ȩ�����������.net12-1 ԭ��׿
	 * <p/>
	 * ��Ŀ���������û���Ȥ��ǩ�������Ƽ�ϵͳ
	 * <p/>
	 * ���ߣ���ԭ��׿
	 * <p/>
	 * �汾����1.0
	 * <p/>
	 * �������ڣ���16-4-14 ����11:36
	 * <p/>
	 * ���������� �����û��ǳ������û�ͷ��
	 * <p>
	 * <p/>
	 * ���ܸ�����ʷ��
	 * <p>
	 * ==================================================
	 */
	



	    /**
	     * �������ֺ�ͼƬ���ƴ���ͼƬ
	     * Ĭ�ϵ����塡����Ӵ�
	     * @param str������
	     * @param imgName���ļ���
	     * @throws Exception
	     */
	    public static void createImage(String str, String imgName) throws Exception{
	        createImage(str,new Font("����",Font.BOLD,40),new File(imgName));
	    }

	    /**
	     * ����str,���Ŀ¼����ͼƬ
	     * ������ʽĬ��Ϊ������Ӵ�
	     * @param str������
	     * @param outFile�����Ŀ¼
	     * @throws Exception
	     */
	    public static void createImage(String str, File outFile) throws Exception{
	        createImage(str,new Font("����",Font.BOLD,40),outFile);
	    }

	    /**
	     * ����str,font����ʽ�Լ�����ļ�Ŀ¼
	     * @param str ����
	     * @param font��������ʽ
	     * @param outFile��������ļ����������λ��
	     * @throws Exception
	     * createImage("�л����񹲺͹�",new Font("����",Font.BOLD,18),new File("e:/a.png"));
	     */
	    public static void createImage(String str, Font font, File outFile) throws Exception{

	        ArrayList<int[]> rgbs = getRGB();
	        int width=120;
	        int height=120;

	        //����ͼƬ
	        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
	        Graphics g=image.getGraphics();

	        g.setColor(new Color(rgbs.get(1)[0],rgbs.get(1)[1],rgbs.get(1)[2]));//��ɫ
	        g.fillRect(0, 0, width, height);//������ɫ�������ͼƬ,Ҳ���Ǳ���
	        g.setColor(new Color(rgbs.get(0)[0],rgbs.get(0)[1],rgbs.get(0)[2]));//�ڻ���ǳɫ
	        g.setFont(font);//���û�������

	        //�����ַ���
	        g.drawString(str,70,70);
	        g.dispose();
	        //���pngͼƬ
	        ImageIO.write(image, "png", outFile);
	    }


	    /**
	     *  һ���㷨�������ж�������ɫ����ǳ��ɫ���㷨
	     *
	     *  $grayLevel = $R * 0.299 + $G * 0.587 + $B * 0.114;
	     *    if ($grayLevel >= 192) {
	     *      // add shadow
	     *    }
	     *
	     * �ѷ���
	     */
	    private static ArrayList<int[]> getRGB(){
	       //1.��������ɡ�rgb
	       //2.���ж�����ɫ����ǳ��ɫ
	       //3.������������ɡ�rgb
	       //4.���ж�������ɫ����ǳ��ɫ�����͵�һ���෴����������rgbֵ
	       //5.  ��ɫ�����Ϊ��������ɫǳ����Ϊ���֣�
	       //6.����һ�����ǳ��ɫ���ڶ����������ɫ
	        ArrayList<int[]> colorList=new ArrayList<int[]>();
	        int[] rgb = getRanRGB();
	        while (true){
	            if(isQianRGB(rgb)){
	                colorList.add(rgb);
	                break;
	            }else {
	                rgb=getRanRGB();
	            }
	        }
	        int[] rgbQ=getRanRGB();
	        while (true){
	            if(isQianRGB(rgbQ)){
	                rgbQ=getRanRGB();
	            }else {
	                colorList.add(rgbQ);
	                break;
	            }
	        }
	        return colorList;
	    }

	    /**
	     * ��������ɫ
	     * @return
	     */
	    private static int[] getRanRGB(){
	        int [] colors=new int[3];
	        for(int i=0;i<colors.length;i++){
	            colors[i]=(int)(Math.random()*256);
	        }
	        return colors;
	    }

	    /**
	     * �ж��ǲ�������ɫ
	     * @param colors
	     * @return
	     */
	    private static boolean isQianRGB(int[] colors){
	        int grayLevel = (int) (colors[0] * 0.299 + colors[1] * 0.587 + colors[2] * 0.114);
	        if(grayLevel>=192){
	            return true;
	        }
	        return false;
	    }


}
