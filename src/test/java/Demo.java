import cn.cgcc.model.Value;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Demo {
    public static void main(String[] args) {
        String arrayStr= "[{\"BusinessDataTableSpace\":2238,\"time\":1637252400},{\"BusinessDataTableSpace\":2238,\"time\":1637252100}]";
        JSONArray jsonArray= JSONArray.fromObject(arrayStr);

        Object o=jsonArray.get( 0 );
        JSONObject jsonObject2=JSONObject.fromObject(o);
        Value stu2=(Value) JSONObject.toBean(jsonObject2, Value.class );
        System.out.println( "stu2:" +stu2);
    }
}
