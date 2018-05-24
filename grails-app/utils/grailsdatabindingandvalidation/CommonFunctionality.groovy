package grailsdatabindingandvalidation

import grails.util.Holders
import org.springframework.context.MessageSourceResolvable

class CommonFunctionality {

    /**
     * Gets a message using messageSource
     * @param code Message code
     * @param argumentList List of arguments to fill the message
     * @return String containing the message
     */
    static String getMessage(String code, Object[] argumentList) {
        argumentList = argumentList.collect { it ? it : '' }
        String message = Holders.grailsApplication.mainContext.getBean('messageSource').getMessage(code, argumentList, Locale.default)
        return message
    }

    /**
     * Gets a message from a MessageSourceResolvable using messageSource
     * @param resolvable MessageSourceResolvable (usually a FieldError)
     * @return String containing the message
     */
    static String getMessage(MessageSourceResolvable resolvable) {
        String message = Holders.grailsApplication.mainContext.getBean('messageSource').getMessage(resolvable, Locale.default)
        return message
    }

}