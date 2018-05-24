package grailsdatabindingandvalidation

import grailsdatabindingandvalidation.messages.binding.DataBindingJson
import grailsdatabindingandvalidation.messages.binding.DataBindingParams
import grailsdatabindingandvalidation.messages.dataValidation.ValidationErrorMessage
import grailsdatabindingandvalidation.messages.validation.DataValidationJson
import grailsdatabindingandvalidation.messages.validation.DataValidationParams

class DataController extends BaseController {

    def bind() {
        DataBindingParams dataBindingParams = params as DataBindingParams
        DataBindingJson dataBindingJson = request.JSON as DataBindingJson

        return [dataBindingParams: dataBindingParams, dataBindingJson: dataBindingJson]
    }

    def validate() {
        DataValidationParams dataValidationParams = params as DataValidationParams
        DataValidationJson dataValidationJson = request.JSON as DataValidationJson

        ValidationErrorMessage validationErrorMessage = validateData([dataValidationParams, dataValidationJson])
        if (validationErrorMessage) return

        return [dataValidationParams: dataValidationParams, dataValidationJson: dataValidationJson]
    }

}
