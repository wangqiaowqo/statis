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

import com.discoveryLog.Log;
import com.discoveryLog.LogServiceImpl;
 

public class LogServer { 
   /** 
    * 启动 Thrift 服务器
    * @param args 
    */ 
   public static void main(String[] args) { 
       try { 
    	   // 设置服务端口为 10005
    	   TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(10005); 
    	   Log.Processor processor = new Log.Processor(new LogServiceImpl());
    	   Args serverTransportArgs = new Args(serverTransport);
    	   
    	   // 设置协议工厂为 TCompactProtocol.Factory 
    	   serverTransportArgs.protocolFactory(new TCompactProtocol.Factory());  
    	   // 设置传输方式为 TFramedTransport.Factory
    	   serverTransportArgs.transportFactory(new TFramedTransport.Factory()); 
    	   // 关联处理器与 Log 服务的实现
    	   serverTransportArgs.processorFactory(new TProcessorFactory(processor));
    	   
    	   TServer server = new TNonblockingServer(serverTransportArgs); 
    	   System.out.println("Start server on port 10005 ..."); 
    	   server.serve(); 
       } catch (TTransportException e) { 
           e.printStackTrace(); 
       } 
   } 
} 
