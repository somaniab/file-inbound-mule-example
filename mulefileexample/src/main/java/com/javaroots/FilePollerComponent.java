package com.javaroots;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;


public class FilePollerComponent implements Callable
{

	private String pollDir ;
	
	private int numberOfFiles;
	
	public String getPollDir()
	{
		return pollDir;
	}

	public void setPollDir(String pollDir)
	{
		this.pollDir = pollDir;
	}
	
	

	public int getNumberOfFiles()
	{
		return numberOfFiles;
	}

	public void setNumberOfFiles(int numberOfFiles)
	{
		if(numberOfFiles < 1 )
			throw new RuntimeException("Number of files can not be less than 1");
		this.numberOfFiles = numberOfFiles;
	}

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception
	{
		File f = new File(pollDir);
		List<File> filesToReturn = new ArrayList<File>(numberOfFiles);
		if(f.isDirectory())
		{
			File[] files = f.listFiles();
			int i = 0;
			for(File file : files)
			{
				if(i==numberOfFiles)
					break ;
				if(file.isFile())
				{
					filesToReturn.add(file);
					i++;
				}
			}
		}
		else
		{
			throw new Exception("Invalid Directory");
		}
		return filesToReturn;
	}

}
