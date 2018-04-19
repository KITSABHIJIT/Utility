package com.test.exceptions;

public class ServiceException extends Exception
{

	private static final long serialVersionUID = 1997753363232807009L;

	public ServiceException()
	{
	}

	public ServiceException(String message)
	{
		super(message);
	}

	public ServiceException(Throwable cause)
	{
		super(cause);
	}

	public ServiceException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
