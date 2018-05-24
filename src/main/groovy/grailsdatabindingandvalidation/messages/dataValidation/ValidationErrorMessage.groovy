package grailsdatabindingandvalidation.messages.dataValidation

class ValidationErrorMessage {

    String message
    int total
    ArrayList<ValidationError> errorList

}

class ValidationError {

    int status
    String message
    String className
    String field
    Object rejectedValue

}