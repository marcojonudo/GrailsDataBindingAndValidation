package grailsdatabindingandvalidation

import grailsdatabindingandvalidation.builders.Message
import grailsdatabindingandvalidation.builders.MessageBuilder
import grailsdatabindingandvalidation.builders.StatusDecider
import grailsdatabindingandvalidation.messages.dataValidation.ValidationErrorMessage
import org.springframework.validation.FieldError

class BaseController {

    /**
     * Builds generic response model
     * @param object Object to be returned
     * @return Map with Model content
     */
    HashMap buildResponseModel(object) {
        return [responseObject: object]
    }

    /**
     * Validates if a message fields are correct and responds with validation error message if necessary
     * @param message Object or list of objects
     * @return ValidationErrorMessage
     */
    ValidationErrorMessage validateData(message) {
        ValidationErrorMessage validationErrorMessage = null
        ArrayList<FieldError> errors = Validation.validateMessage(message)
        if (errors) {
            validationErrorMessage = MessageBuilder.buildMessage(Message.VALIDATION_ERROR, errors) as ValidationErrorMessage
            response.status = StatusDecider.chooseStatus(Message.VALIDATION_ERROR, validationErrorMessage).value()
            render(view: '/errors/validationErrors', model: buildResponseModel(validationErrorMessage))
        }

        return validationErrorMessage
    }

}
