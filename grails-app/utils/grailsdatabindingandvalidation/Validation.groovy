package grailsdatabindingandvalidation

import grails.validation.Validateable
import grailsdatabindingandvalidation.messages.dataValidation.ValidationErrorFields
import org.springframework.validation.FieldError

class Validation {

    /**
     * Checks message validation errors
     * @param message Message to validate errors from
     * @return List of FieldError
     */
    static ArrayList<FieldError> validateMessage(message) {
        /* Si el mensaje es validable y no valida, se obtienen los errores */
        List<FieldError> errorList = [message].flatten().collect {
            it instanceof Validateable && !it.validate() ? it.errors.fieldErrors : []
        }.flatten() as ArrayList<FieldError>
        /* Se obtienen aquellos objetos hijos que pueden sar validados */
        ArrayList<Object> validateableObjectList = message.properties.values().findAll { isValidateable(it) }.flatten()

        /* Si existen hijos validables, se obtienen sus errores y se añaden a 'errorList' */
        if (validateableObjectList) {
            errorList += validateableObjectList.collect { validateMessage(it) }
        }

        return errorList.flatten() as ArrayList<FieldError>
    }

    /**
     * Checks if an object validateable (Validateable object or list)
     * @param message Message to check if validateable
     * @return Boolean
     */
    private static boolean isValidateable(message) {
        /* Se sigue iterando si el objeto es validable o si es una lista (con posibles objetos validables) */
        return message instanceof Validateable || message instanceof List
    }

    /**
     * Converts message got from i18n to a map with message and code keys
     * @param message Message read from i18n
     * @return ValidationErrorFields
     */
    static ValidationErrorFields parseValidationMessage(String message) {
        ArrayList<String> infoList = message.split(ValidationErrorFields.VALIDATION_INFO_SEPARATOR)
        ValidationErrorFields validationErrorFields = infoList.size() == ValidationErrorFields.VALIDATION_INFO_KEYS.size() ? new ValidationErrorFields() : null
        if (validationErrorFields) {
            infoList.eachWithIndex { String value, int index ->
                validationErrorFields."${ValidationErrorFields.VALIDATION_INFO_KEYS[index]}" = value
            }
        }
        return validationErrorFields
    }

}
