package com.discoveryLog.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.discoveryLog.Hello;

public class HelloServiceClient { 
/** 
    * 调用 Hello 服务
    * @param args 
    */ 
   public static void main(String[] args) { 
       try { 
           // 设置调用的服务地址为本地，端口为 7911 
//           TTransport transport = new TSocket("localhost", 7911); 
           TTransport transport = new TFramedTransport(new TSocket("localhost", 10005)); 
           
           transport.open(); 
           // 设置传输协议为 TBinaryProtocol 
//           TProtocol protocol = new TBinaryProtocol(transport); 
           TCompactProtocol protocol = new TCompactProtocol(transport);
           Hello.Client client = new Hello.Client(protocol); 
           // 调用服务的 helloVoid 方法
           client.helloVoid(); 
           transport.close(); 
       } catch (TTransportException e) { 
           e.printStackTrace(); 
       } catch (TException e) { 
           e.printStackTrace(); 
       } 
   } 
} 
