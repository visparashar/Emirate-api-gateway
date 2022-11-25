package ae.emirates.emiratesapigateway.model;

import java.util.Calendar;
import java.util.Date;


public class Response<T> {

	  private static final String R_MSG_EMPTY = "";
	  private final boolean responseCode;
	  private final Date execDt;
	  private final String message;

	  private T data;

	  /**
	   * A Creates a new instance of Response
	   *
	   * @param code
	   * @param message
	   * @param execDt
	   */
	  
	  /**
	 * 
	 */
	
	  public Response(final boolean code, final String message, final Date execDt) {

	    this.execDt = execDt == null ? Calendar.getInstance().getTime() : execDt;
	    this.message = message == null ? Response.R_MSG_EMPTY : message;
	    this.responseCode = code;
	    this.data = null;
	  }
	  
	  public Response(final boolean code, final String message, final Date execDt , final T response) {

		    this.execDt = execDt == null ? Calendar.getInstance().getTime() : execDt;
		    this.message = message == null ? Response.R_MSG_EMPTY : message;
		    this.responseCode = code;
		    this.data = response;
		  }
	  
	  

	  /**
	   * @return the execDt
	   */
	  public Date getExecDt() {

	    return this.execDt;
	  }

	  /**
	   * @return the message
	   */
	  public String getMessage() {

	    return this.message;
	  }

	  /**
	   * @return the response
	   */
	  public T getData() {

	    return this.data;
	  }

	  /**
	   * @return the responseCode
	   */
	  public boolean getResponseCode() {

	    return this.responseCode;
	  }

	  /**
	   * sets the response object
	   *
	   * @param obj
	   * @return
	   */
	  public Response<T> setData(final T obj) {

	    this.data = obj;
	    return this;
	  }
	}
