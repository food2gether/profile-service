package org.github.food2gether.profileservice.service;

import com.github.food2gether.shared.model.ContactInformation;
import com.github.food2gether.shared.model.ContactInformation.DTO;
import java.util.List;

public interface ContactInformationService {
  List<ContactInformation> getContactInfo(Long id);

  List<ContactInformation> createOrUpdate(Long id, List<DTO> contactInfoDtos);
}
