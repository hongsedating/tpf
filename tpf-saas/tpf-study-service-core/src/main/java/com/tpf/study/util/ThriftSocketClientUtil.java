package com.tpf.study.util;

import com.tpf.framework.core.communication.CommunicationProtocol;
import com.tpf.framework.core.communication.ProtocolVersion;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class ThriftSocketClientUtil {
    private static int DEF_TIMEOUT = 10000;
    private static Logger logger = Logger.getLogger(ThriftSocketClientUtil.class);

    public static String request(String host, int port, ProtocolVersion pv, String msPath, String data){
        return request(host, port, DEF_TIMEOUT, pv, msPath, data);
    }

    public static String request(String host, int port, int timeout, ProtocolVersion pv, String msPath, String data) {
        TTransport transport = null;
        String result = null;
        try {
            transport = new TFramedTransport(new TSocket(host, port, timeout));
            TProtocol protocol = new TBinaryProtocol(transport);
            CommunicationProtocol.Client client = new CommunicationProtocol.Client(protocol);
            transport.open();
            long start = System.currentTimeMillis();
            result = client.process(pv, msPath, data);
            logger.info(msPath+"请求用时"+(System.currentTimeMillis()-start)+"毫秒");
            transport.close();
            transport = null;
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
        return result;
    }
}
