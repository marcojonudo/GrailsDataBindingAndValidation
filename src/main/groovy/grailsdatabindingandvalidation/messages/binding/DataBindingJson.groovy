package grailsdatabindingandvalidation.messages.binding

import grails.databinding.BindingFormat
import grailsdatabindingandvalidation.BindingObject
import groovy.transform.InheritConstructors

@InheritConstructors
class DataBindingJson extends BindingObject {

    @BindingFormat('UPPERCASE')
    String fieldToUpperCase
    @BindingFormat('LOWERCASE')
    String fieldToLowerCase
    Integer quantity1 // May be null
    int quantity2 // Cannot be null (primitive type)
    Date date

}
