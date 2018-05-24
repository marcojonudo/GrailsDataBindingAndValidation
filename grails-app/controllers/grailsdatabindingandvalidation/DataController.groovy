package grailsdatabindingandvalidation

import grailsdatabindingandvalidation.binding.DataBindingJson
import grailsdatabindingandvalidation.binding.DataBindingParams

class DataController {

    def bind() {
        DataBindingParams dataBindingParams = params as DataBindingParams
        DataBindingJson dataBindingJson = request.JSON as DataBindingJson

        return [dataBindingParams: dataBindingParams, dataBindingJson: dataBindingJson]
    }

}
