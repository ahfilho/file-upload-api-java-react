package br.com.api.storage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.api.response.ResponseMessage;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<ResponseMessage> handlerMaxSixeException(MaxUploadSizeExceededException exc) {

		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Arquivo muito grande."));
	}

}
