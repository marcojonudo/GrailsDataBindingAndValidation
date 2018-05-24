package grailsdatabindingandvalidation.errors.validation

import org.springframework.http.HttpStatus

enum ValidationError {

    BAD_REQUEST(HttpStatus.BAD_REQUEST), VALIDATION_ERROR(HttpStatus.UNPROCESSABLE_ENTITY)

    final HttpStatus status

    ValidationError(HttpStatus status) {
        this.status = status
    }

}
