/*
 * Copyright (c) 2014 Keetip
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * This product may include a number of subcomponents with separate copyright notices and
 * license terms. Your use of the source code for the these subcomponents is subject to
 * the terms and conditions of the subcomponent's license, as noted in the LICENSE file.
 */

package com.keetip.versio.domain;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.keetip.ddd.domain.Entity;

/**
 * @author keetip
 * 
 */
public class Project implements Entity<Project>{

	private final UUID mUuid;
	private final String mName;
	private String mDescription;
	private Date mCreationDate;
	
	/**
	 * Construct a new persisted Project entity
	 * 
	 * @param uuid
	 */
	public Project(UUID uuid, String name){
		Validate.notNull(uuid, "Project id is required");
		Validate.notNull(name, "Project name is required");
		mUuid = uuid;
		mName = name;
	}
	
	/**
	 * Construct a new transient Project entity
	 * 
	 * @return
	 */
	public static Project newProject(String name){
		return new Project(UUID.randomUUID(), name);
	}

	public UUID getUuid() {
		return mUuid;
	}

	public String getName() {
		return mName;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String description) {
		mDescription = description;
	}

	public Date getCreationDate() {
		return mCreationDate;
	}

	public void setCreationDate(Date creationDate) {
		mCreationDate = creationDate;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(mUuid)
			.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Project)){
			return false;
		}
		Project other = (Project)obj;
		return sameIdentityAs(other);
	}

	public boolean sameIdentityAs(Project other) {
		return new EqualsBuilder()
			.append(mUuid, other.getUuid())
			.isEquals();
	}
	
}
