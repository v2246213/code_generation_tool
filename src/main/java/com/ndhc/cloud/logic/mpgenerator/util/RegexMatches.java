package com.ndhc.cloud.logic.mpgenerator.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author yangnian
 * @datc 2018/8/31 15:13
 */
public class RegexMatches {
    public static  boolean ipVoild(String parm){
        String  voild="";
       if (parm.equals("IP")){
            voild = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
       }else  if (parm.equals("port")){
           voild="([0-9]|[1-9]\\d{1,3}|[1-5]\\d{4}|6[0-4]\\d{4}|65[0-4]\\d{2}|655[0-2]\\d|6553[0-5])";
       }
        Pattern r = Pattern.compile(voild);
        Matcher m = r.matcher(parm);
        return m.matches();
    }
        public static void main(String args[]) {
            String str = "6379";
            String pattern = "([0-9]|[1-9]\\d{1,3}|[1-5]\\d{4}|6[0-4]\\d{4}|65[0-4]\\d{2}|655[0-2]\\d|6553[0-5])";

            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(str);
            System.out.println(m.matches());
        }

}
