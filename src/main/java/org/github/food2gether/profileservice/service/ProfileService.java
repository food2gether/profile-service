package org.github.food2gether.profileservice.service;

import com.github.food2gether.shared.model.Profile;
import java.util.List;

public interface ProfileService {

  Profile createOrUpdateProfile(Profile.DTO profile);

  Profile getProfile(Long id);

  List<Profile> getAll(String searchQuery);

  Profile deleteProfile(Long id);

  Profile getProfileByEmail(String primaryEmail);
}
