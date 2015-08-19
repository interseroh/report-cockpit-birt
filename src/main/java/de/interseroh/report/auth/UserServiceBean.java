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
package de.interseroh.report.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserService Implementation.
 *
 * @author Lofi Dewanto (Interseroh)
 */
@Service
public class UserServiceBean implements UserService {

	@Autowired
	private MembershipRepository membershipRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Transactional(readOnly = true)
	@Override
	public Collection<Membership> findMembershipsByUserEmail(String email) {
		Collection<MembershipEntity> memberships = membershipRepository
				.findByUserEmail(email);

		Collection<Membership> returnMemberships = new ArrayList<>();
		returnMemberships.addAll(memberships);

		return returnMemberships;
	}

	@Transactional
	@Override
	public void createMembership(User user, Group group) {
		Membership membership = new MembershipEntity();
		membership.setGroup(group);
		membership.setUser(user);

		userRepository.save((UserEntity) user);
		groupRepository.save((GroupEntity) group);
		membershipRepository.save((MembershipEntity) membership);
	}
}
