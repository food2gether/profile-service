package org.github.food2gether.profileservice.reposetory;

import com.github.food2gether.shared.model.ContactInformation;
import java.util.List;

public class ContactInformationReposetoryImpl implements ContactInformationReposetory {

  @Override
  public List<ContactInformation> findByProfileId(Long profileId) {
    return this.list("profile.id", profileId);
  }
}
