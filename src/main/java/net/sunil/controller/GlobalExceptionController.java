package net.sunil.controller;	//		String errorMessage = "";
//		String consalidatedErrorMessage = "";
//		String field = "";

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import net.sunil.dto.AppConstants;
import net.sunil.dto.GenericResponse;

@ControllerAdvice
@RestController
public class GlobalExceptionController {

	private static Logger logger = Logger.getLogger(GlobalExceptionController.class);
	
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public GenericResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
		// e.printStackTrace();
		final BindingResult result = e.getBindingResult();
		final List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
	
		final List<String> errorMsgs = new ArrayList<>();
		for (final org.springframework.validation.FieldError fieldError : fieldErrors) {

			errorMsgs.add(fieldError.getField() + " -> " + fieldError.getDefaultMessage());

		}
	
		logger.error("error in handleMethodArgumentNotValidException :: " + errorMsgs);
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE, errorMsgs);
	}

	

	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(value = IllegalArgumentException.class)
	public GenericResponse handleIllegalArgumentException(final IllegalArgumentException e) {
		final String errorMessage = e.getMessage();
		logger.error("error in handleIllegalArgumentException :: " + errorMessage);
		e.printStackTrace();
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE, errorMessage);
	}

	/**
	 * Gives @HttpStatus.NOT_FOUND status for the response, if
	 * requested @URI/ @URL is not found
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = NoHandlerFoundException.class)
	public GenericResponse handleNoHandlerFoundException(final NoHandlerFoundException e) {
		final String errorMessage = "The url you have requested (" + e.getRequestURL() + ") does not exist";
		logger.error("error in handleNoHandlerFoundException :: " + errorMessage);
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE, errorMessage);
	}

	/**
	 * Gives @HttpStatus.BAD_REQUEST status for the response, if DataType of
	 * keys(fields) in provided request doesn't match with the required fields.
	 * Generally this exception occurs if DataType of @PathVariable
	 * in @HttpServletRequest doesn't match with required DataType declared in
	 * the code(controller method)
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	public GenericResponse handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e) {
		// e.printStackTrace();
		final String errorMessage = e.getMessage();
		logger.error("error in handleMethodArgumentTypeMismatchException :: " + errorMessage);
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE, errorMessage);
	}

	/**
	 * Gives @HttpStatus.BAD_REQUEST status for the response, if required query
	 * string parameter( @PathVariable) is not present in
	 * the @HttpServletRequest
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	public GenericResponse handleMissingServletRequestParameterException(
			final MissingServletRequestParameterException e) {
		// e.printStackTrace();
		final String errorMessage = e.getMessage();
		logger.error("error in handleMissingServletRequestParameterException :: " + errorMessage);
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE, errorMessage);
	}

	/**
	 * Gives @HttpStatus.BAD_REQUEST status for the response, if required
	 * Content-Type (application/json) is not present in the request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
	public GenericResponse handleHttpMediaTypeNotSupportedException(final HttpMediaTypeNotSupportedException e) {
		e.printStackTrace();
		final String errorMessage = e.getMessage();
		logger.error("error in handleHttpMediaTypeNotSupportedException :: " + errorMessage);
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE, errorMessage);
	}

	/**
	 * Gives @HttpStatus.BAD_REQUEST status for the response, if
	 * requested @URI/ @URL doesn't contain required {@value Parameters} like
	 * 'query string parameter( @RequestParam)', 'dynamic value in
	 * path( @PathVariable)', etc..
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = ServletRequestBindingException.class)
	public GenericResponse handleServletRequestBindingException(final ServletRequestBindingException e) {
		// e.printStackTrace();
		final String errorMessage = e.getMessage();
		logger.error("error in handleServletRequestBindingException :: " + errorMessage);
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE, errorMessage);
	}

	/**
	 * Gives @HttpStatus.BAD_REQUEST status for the response, if
	 * requested @HttpMethod is different from required @HttpMethod
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public GenericResponse handleHttpRequestMethodNotSupportedException(
			final HttpRequestMethodNotSupportedException e) {
		// e.printStackTrace();
		final String errorMessage = e.getMessage();
		logger.error("error in handleHttpRequestMethodNotSupportedException :: " + errorMessage);
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE, errorMessage);
	}

	/**
	 * Gives @HttpStatus.BAD_REQUEST status for the response, if any date parse
	 * exception occurs in the code
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = ParseException.class)
	public GenericResponse handleParseException(final ParseException e) {
		// e.printStackTrace();
		final String errorMessage = e.getMessage();
		logger.error("error in handleParseException :: " + errorMessage);
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE, errorMessage);
	}

	/**
	 * Gives @HttpStatus.BAD_REQUEST status for the response, if the 'datatype'
	 * for the fields in request doesn't match with the required datatypes
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public GenericResponse handleHttpMessageNotReadableException(final HttpMessageNotReadableException e) {
		// e.printStackTrace();
		final String errorMessage = e.getCause().getMessage();

		if (e.getCause().getClass().equals(InvalidFormatException.class)) {
			/**
			 * To reproduce a @InvalidFormatException, provide string value in
			 * request for required @Long/other @Datatype
			 */
			return this.doSomethingForInvalidFormatException(errorMessage);
		} else {
			logger.error("e.getCause().getClass() -:-" + e.getCause().getClass());
			logger.error("error in handleHttpMessageNotReadableException :: " + errorMessage);
			return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE, errorMessage);
		}
	}

	private GenericResponse doSomethingForInvalidFormatException(final String errorMessage) {
		/**
		 * @Message Can not construct instance of long from String value
		 *          'dynamicString': not a valid Long value\n at [Source:
		 *          java.io.PushbackInputStream@4fc4d431; line: 3, column: 26]
		 *          (through reference chain:
		 *          net.thrymr.beans.ProjectBean[\"projectProposalId\"])
		 * @Note : Below logic/code will work if & only if the errorMessage is
		 *       in the above format
		 * @Note : All this logic is required to get the 'key'(name of the field
		 *       in the request, in which data/value is not correct) from the
		 *       default error message thrown by spring
		 */
		try {
			final String[] errorMessageArray = errorMessage.split("\"");
			final int lengthOfErrorMessageArray = errorMessageArray.length;
			String fieldName = "";
			if (errorMessageArray.length != 0) {
				int loopCount = 1;
				for (final String string : errorMessageArray) {

					if (loopCount == (lengthOfErrorMessageArray - 1)) {
						fieldName = string;
						break;
					}
					loopCount++;
				}
			}
			if (fieldName.equals("")) {
				/**
				 * value of 'fieldName' may or may not change from the above
				 * logic, If the value of 'fieldName' is ""/empty then throw the
				 * default error message given by spring
				 */
				logger.error("error in handleHttpMessageNotReadableException :: " + errorMessage);
				return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE, errorMessage);
			}
			final String consolidatedErrorMessage = "Please check the provided field '" + fieldName + "' \n "
					+ errorMessage;
			logger.error("error in handleHttpMessageNotReadableException :: " + consolidatedErrorMessage);
			return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE, consolidatedErrorMessage);

		} catch (final Exception ex) {
			/**
			 * If any exception occurs in the above logic, then throw the
			 * default error message given by spring
			 */
			logger.error("error in handleHttpMessageNotReadableException :: " + errorMessage);
			return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE, errorMessage);
		}
	}

	/**
	 * Gives @HttpStatus.INTERNAL_SERVER_ERROR status for the response, if there
	 * is any @NullPointerException in the code
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = NullPointerException.class)
	public GenericResponse handleNullPointerException(final NullPointerException e) {
		logger.error("error in handleNullPointerException");
		e.printStackTrace();
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE,
				"Oops! Something went wrong, please try again");
	}

	/**
	 * Gives @HttpStatus.INTERNAL_SERVER_ERROR status for the response as
	 * default, if and only if no specific @Exception handler is found
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public GenericResponse handleException(final Exception e) {
		logger.error("error in handleException");
		e.printStackTrace();
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE,
				"Oops! Something went wrong, please try again");
	}	
	
}
