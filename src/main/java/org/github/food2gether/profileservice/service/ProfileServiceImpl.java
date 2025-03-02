package org.github.food2gether.profileservice.service;

import com.github.food2gether.shared.model.Profile;
import io.netty.util.internal.StringUtil;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response.Status;
import org.github.food2gether.profileservice.reposetory.ProfileReposetory;

public class ProfileServiceImpl implements ProfileService {

  @Inject
  EntityManager entityManager;

  @Inject
  ProfileReposetory profileReposetory;

  @Override
  public Profile createOrUpdateProfile(Profile.DTO profile) {
    return profile.getId() == null ? this.create(profile) : this.update(profile);
  }

  @Transactional
  public Profile create(Profile.DTO profile) {
    if (profile.getId() != null) {
      throw new WebApplicationException(
          "Profile id must be null for creating a new Profile", Status.BAD_REQUEST
      );
    }

    if(StringUtil.isNullOrEmpty(profile.getName())) {
      throw new WebApplicationException(
          "Profile name must not be null or empty", Status.BAD_REQUEST
      );
    }

    if (StringUtil.isNullOrEmpty(profile.getPrimaryEmail())) {
      throw new WebApplicationException(
          "Profile primary email must not be null or empty", Status.BAD_REQUEST
      );
    }

    Profile result = new Profile();
    result.setName(profile.getName());

    this.profileReposetory.persist(result);
    return result;
  }

  @Transactional
  public Profile update(Profile.DTO profile) {
    if (profile.getId() == null) {
      throw new WebApplicationException(
          "Profile id must not be null for updating a Profile", Status.BAD_REQUEST
      );
    }

    Profile result = this.profileReposetory.findByIdOptional(profile.getId())
        .orElseThrow(() -> new NotFoundException("Profile not found"));

    if(StringUtil.isNullOrEmpty(profile.getName())) {
      result.setName(profile.getName());
    }

    if(StringUtil.isNullOrEmpty(profile.getPrimaryEmail())) {
      result.setPrimaryEmail(profile.getPrimaryEmail());
    }

    if (profile.getProfilePictureUrl() != null) {
      result.setProfilePictureUrl(profile.getProfilePictureUrl());
    }

    if (profile.getDisplayName() != null) {
      result.setDisplayName(profile.getDisplayName());
    }

    this.profileReposetory.persist(result);
    return result;
  }

  @Override
  public Profile getProfile(Long id) {
    return this.profileReposetory.findByIdOptional(id)
        .orElseThrow(() -> new NotFoundException("Profile not found"));
  }

  @Override
  public List<Profile> getAll(String searchQuery) {
    return searchQuery != null
        ? this.profileReposetory.listAllForQuery(searchQuery)
        : this.profileReposetory.listAll();
  }

  @Override
  @Transactional
  public Profile deleteProfile(Long id) {
    Profile profile = this.profileReposetory.findByIdOptional(id)
        .orElseThrow(() -> new NotFoundException("Profile not found"));

    this.profileReposetory.delete(profile);
    return profile;
  }
}
