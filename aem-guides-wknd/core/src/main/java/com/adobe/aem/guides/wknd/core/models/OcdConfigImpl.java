package com.adobe.aem.guides.wknd.core.models;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = OcdConfigInterface.class, immediate = true)
@Designate(ocd = OcdConfig.class)
public class OcdConfigImpl implements OcdConfigInterface {
	
	private OcdConfig ocdConfig;
	
	 @Activate
	 protected void activate(OcdConfig config) {
	  this.ocdConfig = config;
	 }

	@Override
	public String getkeyValueForIndia() {
		return ocdConfig.getkeyValueForIndia();
	}


}
