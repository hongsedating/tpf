package com.tpf.framework.core.communication;

import com.tpf.framework.ms.MSRequestDispatcher;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;

public class SocketRequestProxy implements CommunicationProtocol.Iface {
    private static Logger logger = Logger.getLogger(SocketRequestProxy.class);
    @Override
    public String process(ProtocolVersion version, String path, String data) throws TException {
        try {
            String result = MSRequestDispatcher.dispatch(path, data);
            System.out.println(result);
            return result;
        }catch (Exception e){
            logger.error("=====>", e);
            return e.getMessage();
        }
    }
}
