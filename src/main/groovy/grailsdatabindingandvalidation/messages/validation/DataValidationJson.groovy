package grailsdatabindingandvalidation.messages.validation

import grails.databinding.BindingFormat
import grails.validation.Validateable
import grailsdatabindingandvalidation.BindingObject
import grailsdatabindingandvalidation.Constants
import groovy.transform.InheritConstructors

@InheritConstructors
class DataValidationJson extends BindingObject implements Validateable {

    @BindingFormat('UPPERCASE')
    String fieldToUpperCase
    @BindingFormat('LOWERCASE')
    String fieldToLowerCase
    Integer quantity1 // May be null
    int quantity2 // Cannot be null (primitive type)
    Date date

    static constraints = {
        fieldToLowerCase validator: { String value ->
            return value == Constants.YOU_SHALL_NOT_PASS ? 'gandalf' : null
        }
        quantity1 nullable: true
    }

}
