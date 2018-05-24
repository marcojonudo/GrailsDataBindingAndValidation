# Grails data binding and validation
Receiving parameters and a JSON object is the everyday reality of controllers and APIs.
Unfortunately, these data is not always correct, causing different type of errors in code, from
type incompatibilities to business logic problems.

Even when it could be quite difficult to find in official doc, Grails provides a simple way to validate
all kind of parameters. Helped by a recursive algorithm to loop through all validateable fields in a list
of objects, params and JSON can be validated to ensure services are only accessed when data fits our needs.

## Binding
Given following JSON:

    {
        "fieldToUpperCase": "i am upper case!",
        "fieldToLowerCase": "I AM LOWER CASE!",
        "quantity1": null,
        "quantity2": 1234,
        "date": "2018-03-03 12:00:00"
    }

Binding data makes possible not only to hold it in local POGOs, but also to transform it. Response object shows
some possibilities, where both String fields have been converted:

    {
        "dataBindingParams": {
            "identifier1": "id1",
            "identifier2": 0
        },
        "dataBindingJson": {
            "date": "2018-03-03T11:00:00Z",
            "fieldToLowerCase": "i am lower case!",
            "fieldToUpperCase": "I AM UPPER CASE!",
            "quantity2": 1234
        }
    }
    
## Validation
After binding data, validation (null values, string lengths, custom validation...) should be the next step.

Validateable trait makes it possible to return custom messages depending on the specified methods. For example,
given following JSON:

    {
        "fieldToUpperCase": "i am upper case!",
        "fieldToLowerCase": "You shall not pass!"
    }
    
Return message is:

    {
        "errorList": [
            {
                "className": "DataValidationJson",
                "field": "fieldToLowerCase",
                "message": "Gandalf dice \"you shall not pass!\"",
                "rejectedValue": "you shall not pass!",
                "status": 400
            },
            {
                "className": "DataValidationJson",
                "field": "date",
                "message": "La propiedad [date] de la clase [class grailsdatabindingandvalidation.messages.validation.DataValidationJson] no puede ser nulo",
                "rejectedValue": null,
                "status": 400
            }
        ],
        "message": "Se han encontrado los siguientes errores de validación",
        "total": 2
    }

    