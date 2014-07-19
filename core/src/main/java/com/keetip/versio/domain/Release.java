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

import java.util.UUID;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.keetip.ddd.domain.Entity;

/**
 * @author keetip
 * 
 */
public class Release implements Entity<Release>{
	
	private final UUID mUuid;
	private final Project mProject;
	private final String mName;
	private final Revision mRevision;
	private String mComment;
	
	/**
	 * Construct a new persisted Release entity
	 * 
	 * @param project
	 * @param uuid
	 * @param name
	 * @param revision
	 */
	public Release(Project project, UUID uuid, String name, Revision revision){
		Validate.notNull(project, "Project is required");
		Validate.notNull(uuid, "Release id is required");
		Validate.notNull(name, "Release name is required");
		Validate.notNull(revision, "Revision is required");
		
		mProject = project;
		mUuid = uuid;
		mName = name;
		mRevision = revision;
	}
	
	/**
	 * Construct a new transient Release entity
	 * 
	 * @param project
	 * @param name
	 * @param revision
	 * @return
	 */
	public static Release newRelase(Project project, String name, Revision revision){
		return new Release(project, UUID.randomUUID(), name, revision);
	}
	
	public Project getProject() {
		return mProject;
	}

	public UUID getUuid() {
		return mUuid;
	}

	public String getComment() {
		return mComment;
	}

	public String getDescription() {
		return mComment;
	}

	public void setComment(String comment) {
		mComment = comment;
	}

	public String getName() {
		return mName;
	}

	public Revision getRevision() {
		return mRevision;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(mUuid)
			.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Release)){
			return false;
		}
		Release other = (Release)obj;
		return sameIdentityAs(other);
	}

	public boolean sameIdentityAs(Release other) {
		return new EqualsBuilder()
			.append(mUuid, other.getName())
			.isEquals();
	}

}
