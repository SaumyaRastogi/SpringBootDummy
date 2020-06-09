package com.restServices.UdemyRestServices.UIModelResponse;


public enum ErrorMessages {
	       MISSING_REQUIRED_FIELD("missing field required please check"),
			INTERNAL_SERVER_ERROR("some internal server eroor"),
	        NO_RECORD_FOUND("This record do not exist");
		
		private String errorMessage;
		
		
		
		ErrorMessages(String errorMessage)
		{
			
			this.errorMessage = errorMessage;
		}

		

		public String getMessage() {
			return errorMessage;
		}

		public void setMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
	}


