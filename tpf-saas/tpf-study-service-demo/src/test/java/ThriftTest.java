import com.tpf.framework.core.communication.ProtocolVersion;
import com.tpf.study.util.ThriftSocketClientUtil;
import net.sf.json.JSONObject;
import org.junit.Test;

public class ThriftTest {
    @Test
    public void testRequest() {
        JSONObject data = new JSONObject();
        data.put("name", "zhangSan");
        String result = ThriftSocketClientUtil.request("127.0.0.1", 8091, ProtocolVersion.V1, "/demo/hello", data.toString());
        System.out.println(result);
    }
}
