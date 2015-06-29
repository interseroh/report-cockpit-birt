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
 * (c) 2015 - Interseroh
 */
package de.interseroh.report.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import de.interseroh.report.exception.UnknownParameterTypeException;
import org.eclipse.birt.report.engine.api.IParameterDefnBase;
import org.junit.Test;

/**
 * @author Ingo Düppe (Crowdcode)
 */
public class BirtParameterTypeTest {

	@Test
	public void testGetType() throws Exception {
		assertThat(
				BirtParameterType.valueOf(IParameterDefnBase.SCALAR_PARAMETER)
						.getType(), is(IParameterDefnBase.SCALAR_PARAMETER));
	}

	@Test(expected = UnknownParameterTypeException.class)
	public void testUnknownParameterTypeException() throws Exception {
		BirtParameterType.valueOf(-1);
	}
}