package com.discoveryLog;

import org.apache.thrift.TException; 
public class LogServiceImpl implements Log.Iface { 
@Override
public int log(String category, String message) throws TException {
	System.out.println(category +" message is:" +message); 
	return 100;
} 
} 
