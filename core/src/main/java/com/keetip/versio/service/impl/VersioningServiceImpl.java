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

package com.keetip.versio.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.Validate;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import com.google.common.collect.Ordering;
import com.keetip.ddd.domain.EntityNotFoundException;
import com.keetip.versio.domain.Resource;
import com.keetip.versio.domain.Revision;
import com.keetip.versio.service.ResourceProviderService;
import com.keetip.versio.service.VersioningService;

/**
 * @author keetip
 * 
 */
public class VersioningServiceImpl implements VersioningService{

	private final Multimap<ResourceKey, Resource> mResources;
	
	private ResourceProviderService mResourceProvider;
	
	public VersioningServiceImpl(){
		mResources = ArrayListMultimap.create();
	}
	
	public <T extends Resource> Collection<T> getResources(String projectName) {
		//mResourceProvider.getResources(projectId);
		return new ArrayList<T>();
	}

	public <T extends Resource> Optional<T> getResource(String projectName, String resourceName, Class<T> resourceClass) {
		return Optional.absent();
	}

	public <T extends Resource> Optional<T> getRevision(String projectName, String resourceName, Class<T> resourceClass, Integer revision) {
		Validate.notEmpty(projectName, "Project name is required");
		Validate.notEmpty(resourceName, "Resource name is required");
		
		//TODO1: resolve projectId from projectName
		UUID projectId = UUID.randomUUID();
		Optional<ResourceKey> key = resolveKey(projectId, resourceName);		
		Optional<Resource> resourceHolder = null;
		if(!key.isPresent()){
			Collection<Resource> allRevisions = mResourceProvider.getRevisions(projectId, resourceName);
		}else{
		}
		
		if(!key.isPresent()){
			resourceHolder = tryLoadResource(key.get(), projectId, resourceName);
		}else{
			resourceHolder = getLatestRevision(mResources.get(key.get()));
		}
		if(resourceHolder.isPresent()){
			Object resource = resourceHolder.get();				
			return Optional.of(resourceClass.cast(resource));				
		}
		return Optional.absent();
	}

	public <T extends Resource> Optional<T> getRevision(String projectName, String resourceName, Date date) {
		return null;
	}

	public Revision resolveRevision(String projectName, String resourceName, Date date) {
		return null;
	}
	
	private <T extends Resource> Optional<T> tryLoadResource(ResourceKey key, UUID projectId, String resourceName){
		if(key == null){
			key = new ResourceKey(projectId, resourceName);
		}
		try{
			Collection<T> resources = mResourceProvider.getRevisions(projectId, resourceName);
			mResources.putAll(key, resources);
			return getLatestRevision(resources);
		}catch(EntityNotFoundException exception){
			return Optional.absent();
		}
	}
	
	private Optional<Resource> getRevision(ResourceKey resourceKey, final Integer revision){
		Collection<Resource> allRevisions = mResources.get(resourceKey);		
		return Iterables.tryFind(allRevisions, new Predicate<Resource>(){
			public boolean apply(Resource resource){
				return resource.getRevision().getRevisionNumber().equals(revision);
			}
		});
	}
	
	private <T extends Resource> Optional<T> getLatestRevision(Collection<T> resources){
		if(resources.isEmpty()){
			return Optional.absent();
		}
		return Optional.of(getByLastRevisionOrdering().max(resources));
	}
	
	private Optional<ResourceKey> resolveKey(final UUID projectId, final String resourceName){
		return Iterables.tryFind(mResources.keySet(), new Predicate<ResourceKey>(){
			public boolean apply(ResourceKey key){
				return key.matches(projectId, resourceName);
			}
		});
	}
	
	private <T extends Resource> Ordering<T> getByLastRevisionOrdering(){
		return Ordering.natural().onResultOf(new Function<T, Integer>(){
			public Integer apply(T resource){
				return resource.getRevision().getRevisionNumber();
			}
		}).reverse();
	}

	public void setResourceProviderService(ResourceProviderService resourceProvider){
		Validate.notNull(resourceProvider, "ResourceProvideService is required");
		mResourceProvider = resourceProvider;
	}

	public <T extends Resource> Optional<T> getResource(String projectName,
			String resourceName) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T extends Resource> Optional<T> getRevision(String projectName,
			String resourceName, Integer revision) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
