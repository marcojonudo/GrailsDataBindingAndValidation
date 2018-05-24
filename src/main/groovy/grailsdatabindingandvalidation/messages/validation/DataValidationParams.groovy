package grailsdatabindingandvalidation.messages.validation

import grailsdatabindingandvalidation.BindingObject
import groovy.transform.InheritConstructors

@InheritConstructors
class DataValidationParams extends BindingObject {

    String identifier1
    int identifier2

}
