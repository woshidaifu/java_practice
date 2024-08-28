package algorithm.poj;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Q1008 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lines = sc.nextInt();
        sc.nextLine();
        String[] haab = new String[lines];
        for (int i = 0;i<lines;i++){
            haab[i] = sc.nextLine();
        }
        transfer(haab);
        sc.close();
    }

    /**
     * 转换程序
     * @param haab
     */
    static void transfer(String[] haab){
        String[] tmp = null;
        int days = 0;//日期获取到的总天数
        int TzolkinDay = 0;
        int TzolkinName = 0;
        int TzolkinYear = 0;
        System.out.println(haab.length);
        for (int i = 0;i<haab.length;i++){
            tmp = haab[i].split("\\s|\\.\\s");//tmp 0 1 2 分别是日 月 年
            days = (Integer.parseInt(tmp[0])+1) + mapping(tmp[1]) + 365 * (Integer.parseInt(tmp[2]));
            //额外考虑整除的情况
            TzolkinDay = days%13==0?13:days%13;
            TzolkinName = days%20;
            TzolkinYear = days%260==0?days/260-1:days/260;
            System.out.println(TzolkinDay+" "+mapName(TzolkinName)+" "+TzolkinYear);
        }
    }

    /**
     * 月份映射天数
      * @param monthName
     * @return 天数
     */
    static int mapping(String monthName){
        Map<String,Integer> mapper = new HashMap<String,Integer>();
        mapper.put("pop",20*0);
        mapper.put("no",20*1);
        mapper.put("zip",20*2);
        mapper.put("zotz",20*3);
        mapper.put("tzec",20*4);
        mapper.put("xul",20*5);
        mapper.put("yoxkin",20*6);
        mapper.put("mol",20*7);
        mapper.put("chen",20*8);
        mapper.put("yax",20*9);
        mapper.put("zac",20*10);
        mapper.put("ceh",20*11);
        mapper.put("mac",20*12);
        mapper.put("kankin",20*13);
        mapper.put("muan",20*14);
        mapper.put("pax",20*15);
        mapper.put("koyab",20*16);
        mapper.put("cumhu",20*17);
        mapper.put("uayet",20*18);
        return mapper.get(monthName);
    }

    /**
     * Tzolkin 日期映射名字
     * @param day
     * @return
     */
    static String mapName(int day){
        Map<Integer,String> mapper = new HashMap<Integer, String>();
        mapper.put(1,"imix");
        mapper.put(2,"ik");
        mapper.put(3,"akbal");
        mapper.put(4,"kan");
        mapper.put(5,"chicchan");
        mapper.put(6,"cimi");
        mapper.put(7,"manik");
        mapper.put(8,"lamat");
        mapper.put(9,"muluk");
        mapper.put(10,"ok");
        mapper.put(11,"chuen");
        mapper.put(12,"eb");
        mapper.put(13,"ben");
        mapper.put(14,"ix");
        mapper.put(15,"mem");
        mapper.put(16,"cib");
        mapper.put(17,"caban");
        mapper.put(18,"eznab");
        mapper.put(19,"canac");
        mapper.put(20,"ahau");
        mapper.put(0,"ahau");
        return mapper.get(day);
    }
}
