package grailsdatabindingandvalidation.messages.dataValidation

class ValidationErrorFields {

    static final String VALIDATION_INFO_SEPARATOR = ' \\| '
    static final ArrayList<String> VALIDATION_INFO_KEYS = ['message', 'code']

    String message
    String code

}
