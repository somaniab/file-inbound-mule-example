package com.javaroots;
import java.io.File;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;


public class SleepComponent implements Callable
{

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception
	{
		File f = (File)eventContext.getMessage().getPayload();
		System.out.println("Processing file " + f.getName());
		System.out.println("Sleeping for 20 seconds");
		Thread.sleep(20000);
		eventContext.getMessage().setPayload("Successfully processed " + f.getName());
		f.delete();
		
		return eventContext.getMessage();
	}
	

}
