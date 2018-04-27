package stega.core.res;

public class WrongPasswordException extends Exception{
	public WrongPasswordException() {
		
	}
	
	public WrongPasswordException(String message)
    {
       super(message);
    }
}
