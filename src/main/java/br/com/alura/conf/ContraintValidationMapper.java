package br.com.alura.conf;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.alura.dto.MensagemErroDto;

/*
 * Class necessária para capturar as exceções do bean validation
 */

@Provider
public class ContraintValidationMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException ex) {
		return Response.status(Response.Status.BAD_REQUEST)
				.entity(MensagemErroDto.build(ex.getConstraintViolations().stream()
						.map(constraintViolation -> constraintViolation.getMessage()).collect(Collectors.toList())))
				.build();
	}

}
