package grailsdatabindingandvalidation.converters

import grails.databinding.converters.FormattedValueConverter
import grailsdatabindingandvalidation.Constants

@SuppressWarnings("GroovyAssignabilityCheck")
class Converter implements FormattedValueConverter {

    private final Map<String, Closure> converterMap = [
            (Constants.UPPERCASE): { String value -> value.toUpperCase() },
            (Constants.LOWERCASE): { String value -> value.toLowerCase() }
    ]

    def convert(value, String format) {
        Closure converter = converterMap.get(format)
        value = converter(value)

        return value
    }

    Class getTargetType() {
        String
    }

}
