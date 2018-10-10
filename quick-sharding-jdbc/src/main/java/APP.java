/**
 * @auther: Zealon
 * @Date: 2018-10-10 13:58
 */
public class APP {
    public static void main(String[] args){
        String dataSourceName = "db";
        String str = String.format("jdbc:mysql://localhost:3306/%s", dataSourceName);
        System.out.println(str);
    }
}
