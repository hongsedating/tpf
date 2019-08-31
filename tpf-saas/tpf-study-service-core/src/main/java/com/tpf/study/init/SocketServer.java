package com.tpf.study.init;

import com.tpf.framework.core.communication.CommunicationProtocol;
import com.tpf.framework.core.communication.SocketRequestProxy;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.ServerContext;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServerEventHandler;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SocketServer {
    @Value("${socket.server.port}")
    private int serverPort;

    @Value("${socket.server.thread.count}")
    private int threadCount;

    public void start(){
        try {
            System.out.println(serverPort);
            TProcessor processor =  new CommunicationProtocol.Processor<CommunicationProtocol.Iface>(new SocketRequestProxy());
            TNonblockingServerSocket socket = new TNonblockingServerSocket(serverPort);

            TThreadedSelectorServer.Args arg = new TThreadedSelectorServer.Args(socket);
            arg.transportFactory(new TFramedTransport.Factory());
            arg.protocolFactory(new TBinaryProtocol.Factory());
            arg.selectorThreads(threadCount/2);
            arg.workerThreads(threadCount);
            arg.acceptPolicy(TThreadedSelectorServer.Args.AcceptPolicy.FAST_ACCEPT).acceptQueueSizePerThread(threadCount);
            arg.processorFactory(new TProcessorFactory(processor));
            arg.maxReadBufferBytes = 1024*1024*10;

            TServer server = new TThreadedSelectorServer(arg);
            server.setServerEventHandler(new StartServerEventHandler());
            server.serve();
        }catch (Exception e){
        }

    }
    /**
     * 一个事件处理器。
     * @author liusp
     *
     */
    private class StartServerEventHandler implements TServerEventHandler {
        @Override
        public void preServe() {
        }

        /* (non-Javadoc)
         * @see org.apache.thrift.server.TServerEventHandler#createContext(org.apache.thrift.protocol.TProtocol, org.apache.thrift.protocol.TProtocol)
         */
        @Override
        public ServerContext createContext(TProtocol input, TProtocol output) {
            return null;
        }

        @Override
        public void deleteContext(ServerContext serverContext, TProtocol input, TProtocol output) {
        }

        @Override
        public void processContext(ServerContext serverContext, TTransport inputTransport, TTransport outputTransport) {
        }
    }
}
