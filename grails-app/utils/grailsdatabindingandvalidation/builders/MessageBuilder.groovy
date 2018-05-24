package grailsdatabindingandvalidation.builders

import grailsdatabindingandvalidation.CommonFunctionality
import grailsdatabindingandvalidation.Constants
import grailsdatabindingandvalidation.Validation
import grailsdatabindingandvalidation.messages.dataValidation.ValidationError
import grailsdatabindingandvalidation.messages.dataValidation.ValidationErrorFields
import grailsdatabindingandvalidation.messages.dataValidation.ValidationErrorMessage
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError

@SuppressWarnings("GroovyAssignabilityCheck")
class MessageBuilder {

    private static final HashMap<Message, Closure> messageBuilder = [
            (Message.VALIDATION_ERROR): { ArrayList<FieldError> errors -> buildValidationErrorMessage(errors) }
    ]

    /**
     * Chooses message builder and returns built message
     * @param messageType Enum containing message type
     * @param message Object to build the return message from
     * @return Built message
     */
    static buildMessage(Message messageType, message) {
        Closure buildMessageType = messageBuilder.get(messageType)
        def builtMessage = buildMessageType(message)
        return builtMessage
    }

    /**
     * Builds validation errorList message
     * @param errorList List of validation errorList
     * @return ValidationErrorMessage
     */
    static ValidationErrorMessage buildValidationErrorMessage(ArrayList<FieldError> errorList) {
        ValidationErrorMessage validationErrorMessage = new ValidationErrorMessage(
                message: CommonFunctionality.getMessage(Constants.VALIDATION_ERROR),
                total: errorList.size()
        )

        validationErrorMessage.errorList = errorList.collect {
            String message = CommonFunctionality.getMessage(it)
            ValidationErrorFields messageInfo = Validation.parseValidationMessage(message)
            int status = messageInfo?.code ?
                    grailsdatabindingandvalidation.errors.validation.ValidationError.valueOf(messageInfo.code).status.value() :
                    HttpStatus.BAD_REQUEST.value()
            new ValidationError(
                    status: status,
                    message: messageInfo?.message ?: message,
                    className: it.objectName.split('\\.').last(),
                    field: it.field,
                    rejectedValue: it.rejectedValue
            )
        }

        return validationErrorMessage
    }

}
