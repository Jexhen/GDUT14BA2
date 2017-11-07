package xin.liaozhixing.class14ba2.utils;

public class StringUtils {
	private static final String  ENGLISH_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    /**
     * ��ȡ����ĸ���������������л���ͷ��
     * @param nick
     * @return
     */
    public static String getCharString(String nick){

        if(nick==null){
          return  String.valueOf(ENGLISH_CHARS.charAt((int)(Math.random() * 26)));
        }

        char[] chars = nick.toCharArray();
        if(chars.length>0){
            char c=chars[0];
            if(Character.isLetter(c)){
                //����ĸ
                if(Character.isLowerCase(c)){
                    //�Ƿ���Сд��ĸ
                    c=Character.toUpperCase(c);
                    return String.valueOf(c);
                }else {
                    return String.valueOf(c);
                }
            }else{
                //������ĸ����
                return String.valueOf(c);
            }
        }
        return String.valueOf(ENGLISH_CHARS.charAt((int)(Math.random() * 26)));
    }
}
