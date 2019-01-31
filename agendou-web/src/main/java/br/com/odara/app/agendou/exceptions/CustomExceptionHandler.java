package br.com.odara.app.agendou.exceptions;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.jboss.logging.Logger;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {

	private static final Logger log = Logger.getLogger(CustomExceptionHandler.class.getCanonicalName());
	private ExceptionHandler exceptionHandler;

	private FacesContext fc;
	
	private Map<String, Object> requestMap;
	private NavigationHandler nav; 
	
	private Iterator<ExceptionQueuedEvent> i;

	@SuppressWarnings("deprecation")
	public CustomExceptionHandler(ExceptionHandler exception) {
		this.exceptionHandler = exception;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return exceptionHandler;
	}
	
	@Override
    public void handle() throws FacesException {
 
        i = getUnhandledExceptionQueuedEvents().iterator();
        while (i.hasNext()) {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

            Throwable t = context.getException();
            fc = FacesContext.getCurrentInstance();
            requestMap = fc.getExternalContext().getRequestMap();
            nav = fc.getApplication().getNavigationHandler();
            
            try {
 
                log.log(Logger.Level.FATAL, "Critical Exception!", t);
 
                requestMap.put("error-message", t.getMessage());
                requestMap.put("error-stack", t.getStackTrace());
                nav.handleNavigation(fc, null, "/pages/error/");
                fc.renderResponse();
 
            } finally {
                i.remove();
            }
        }
    }

}
