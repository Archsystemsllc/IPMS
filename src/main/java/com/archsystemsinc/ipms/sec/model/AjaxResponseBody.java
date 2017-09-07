package com.archsystemsinc.ipms.sec.model;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonView;

/**
 * @author Ramz
 *
 */
public class AjaxResponseBody {

			private String status = null;
	
	        private Object result = null;
	
	        public String getStatus() {
	
	                return status;
	
	        }
	
	        public void setStatus(String status) {
	
	                this.status = status;
	
	        }
	
	        public Object getResult() {
	
	                return result;
	
	        }
	
	        public void setResult(Object result) {
	
	                this.result = result;
	
	        }

}
