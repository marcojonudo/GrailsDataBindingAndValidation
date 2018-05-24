package grailsdatabindingandvalidation.builders

import grailsdatabindingandvalidation.messages.dataValidation.ValidationErrorMessage
import org.springframework.http.HttpStatus

@SuppressWarnings("GroovyAssignabilityCheck")
class StatusDecider {

    /* Mapa con la asociación necesaria para escoger un determinado status */
    private static final HashMap<Message, Closure> messageBuilder = [
            (Message.VALIDATION_ERROR): { ValidationErrorMessage validationErrorMessage -> chooseValidationErrorStatus(validationErrorMessage) }
    ]

    /**
     * Chooses HTTP status based on the message type
     * @param messageType Message enum containing the message type
     * @param message Message to build the response one from
     * @return HttpStatus
     */
    static HttpStatus chooseStatus(Message messageType, message) {
        Closure buildMessageType = messageBuilder.get(messageType)
        def builtMessage = buildMessageType(message)

        return builtMessage
    }

    /**
     * Chooses validation error HTTP status
     * @param validationErrorMessage ValidationErrorMessage
     * @return HttpStatus
     */
    private static HttpStatus chooseValidationErrorStatus(ValidationErrorMessage validationErrorMessage) {
        boolean unprocessable = validationErrorMessage.errorList.status.find {
            it == HttpStatus.UNPROCESSABLE_ENTITY.value()
        }
        HttpStatus status = unprocessable ? HttpStatus.UNPROCESSABLE_ENTITY : HttpStatus.BAD_REQUEST

        return status
    }

}
