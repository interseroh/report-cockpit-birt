/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * (c) 2015 - Interseroh and Crowdcode
 */
package de.interseroh.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Lofi Dewanto (Interseroh)
 */
@PropertySource({ "classpath:config.properties",
		"classpath:version.properties" })
@Component
public class ConfigSetter {

	@Autowired
	private Environment env;

	public void setBranding(ModelAndView modelAndView) {
		String brandingText = env.getProperty("text.branding",
				"Report Cockpit");
		modelAndView.addObject("brandingText", brandingText);
		String brandingLogo = env.getProperty("logo.branding", "birt-logo.png");
		modelAndView.addObject("brandingLogo", brandingLogo);
	}

	public void setVersion(ModelAndView modelAndView) {
		String version = env.getProperty("version");
		modelAndView.addObject("version", version);
	}

}
