package grailsdatabindingandvalidation

import grails.web.databinding.DataBinder

class BindingObject implements DataBinder {

    BindingObject(Map source) {
        bindData(this, source)
    }

}
