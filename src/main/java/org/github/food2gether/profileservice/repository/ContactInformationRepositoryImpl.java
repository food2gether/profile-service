package org.github.food2gether.profileservice.repository;

import com.github.food2gether.shared.model.ContactInformation;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ContactInformationRepositoryImpl implements ContactInformationRepository {

  @Override
  public List<ContactInformation> findByProfileId(Long profileId) {
    return this.list("profile.id", profileId);
  }
}
