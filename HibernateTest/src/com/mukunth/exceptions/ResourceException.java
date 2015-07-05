package com.mukunth.exceptions;

import java.io.Serializable;

public class ResourceException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;
	public ResourceException() {
        super();
    }
	public ResourceException(String msg)   {
        super(msg);
    }
    public ResourceException(String msg, Exception e)  {
        super(msg, e);
    }
}
