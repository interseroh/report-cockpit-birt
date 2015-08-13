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

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import de.interseroh.report.domain.ParameterForm;
import de.interseroh.report.domain.ParameterUtils;
import de.interseroh.report.domain.ScalarParameter;
import de.interseroh.report.domain.visitors.AbstractParameterVisitor;

/**
 * @author Ingo Düppe (Crowdcode)
 */
@Component
public class ParameterFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(ParameterForm.class);
	}

	@Override
	public void validate(Object target, final Errors errors) {
		final ParameterForm parameterForm = (ParameterForm) target;

		parameterForm.accept(new AbstractParameterVisitor() {
			@Override
			public <T> void visit(ScalarParameter<T> parameter) {
				if (!parameter.isValid()) {
					// TODO idueppe - provide a valid
					String propertyPath = ParameterUtils
							.nameToPath(parameter.getName());
					errors.rejectValue(propertyPath,
							"javax.validation.constraints.NotEmpty.message",
							"Es muss ein Wert angegeben werden.");
				}
			}
		});
	}
}