package org.github.food2gether.profileservice.repository;

import com.github.food2gether.shared.model.ContactInformation;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.util.List;

public interface ContactInformationRepository extends PanacheRepository<ContactInformation> {
  List<ContactInformation> findByProfileId(Long profileId);
}
