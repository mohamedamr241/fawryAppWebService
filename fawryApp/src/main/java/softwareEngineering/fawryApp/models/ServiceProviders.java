package softwareEngineering.fawryApp.models;

import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = We.class, name = "We"),
    @JsonSubTypes.Type(value = Orange.class, name = "Orange"),
    @JsonSubTypes.Type(value = Vodafone.class, name = "Vodafone"),
    @JsonSubTypes.Type(value = Etisalat.class, name = "Etisalat")
    
})
public class ServiceProviders {

}
