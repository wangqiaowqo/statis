package com.discoveryLog.server;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TNonblockingServer.Args;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;

import com.discoveryLog.Hello;
import com.discoveryLog.HelloServiceImpl;
 

public class HelloServiceServer { 
   /** 
    * 启动 Thrift 服务器
    * @param args 
    */ 
   public static void main(String[] args) { 
       try { 
//           // 设置服务端口为 7911 
//           TServerSocket serverTransport = new TServerSocket(7911); 
//           // 设置协议工厂为 TBinaryProtocol.Factory 
//           //Factory proFactory = new TBinaryProtocol.Factory(); 
//           
//           TCompactProtocol.Factory proFactory = new TCompactProtocol.Factory();
////           TCompactProtocol protocol = new TCompactProtocol(transport); 
//           // 关联处理器与 Hello 服务的实现
//           TProcessor processor = new Hello.Processor(new HelloServiceImpl()); 
//           Args serverTransportArgs = new Args(serverTransport);
//           serverTransportArgs.processor(processor);
//           TServer server = new TThreadPoolServer(serverTransportArgs); 
//           System.out.println("Start server on port 7911..."); 
//           server.serve(); 
    	   
    	   
    	   TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(10005); 
    	   Hello.Processor processor = new Hello.Processor(new HelloServiceImpl());
    	   Args serverTransportArgs = new Args(serverTransport);
    	   
    	   serverTransportArgs.protocolFactory(new TCompactProtocol.Factory());  
    	   serverTransportArgs.transportFactory(new TFramedTransport.Factory());  
    	   serverTransportArgs.processorFactory(new TProcessorFactory(processor));  
    	   TServer server = new TNonblockingServer(serverTransportArgs); 
    	   System.out.println("Start server on port 10005 ..."); 
    	   server.serve(); 
       } catch (TTransportException e) { 
           e.printStackTrace(); 
       } 
   } 
} 
