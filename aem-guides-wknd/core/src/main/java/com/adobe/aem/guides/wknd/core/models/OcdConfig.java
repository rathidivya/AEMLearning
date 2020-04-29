package com.adobe.aem.guides.wknd.core.models;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "DropdownConfig", description = "For the dropdown values")

public @interface OcdConfig {
	
	@AttributeDefinition(name = "Dropdown values for the key", description = "Holds the value for the respective key in the dropdown", type = AttributeType.STRING)
	String getkeyValueForIndia() default "India";


}
