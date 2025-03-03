package org.github.food2gether.profileservice.service;

import com.github.food2gether.shared.model.ContactInformation;
import com.github.food2gether.shared.model.ContactInformation.DTO;
import com.github.food2gether.shared.model.Profile;
import io.netty.util.internal.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response.Status;
import java.util.List;
import org.github.food2gether.profileservice.repository.ContactInformationRepository;
import org.github.food2gether.profileservice.repository.ProfileRepository;

@ApplicationScoped
public class ContactInformationServiceImpl implements ContactInformationService {

  @Inject
  ContactInformationRepository contactInfoRepository;

  @Inject
  ProfileRepository profileRepository;

  @Inject
  EntityManager entityManager;

  @Override
  public List<ContactInformation> getAll(Long id) {
    if (this.profileRepository.findByIdOptional(id).isEmpty()) {
      throw new NotFoundException("Contact not found");
    }

    return this.contactInfoRepository.findByProfileId(id);
  }

  @Override
  @Transactional
  public List<ContactInformation> createOrUpdate(Long id, List<DTO> contactInfoDtos) {
    Profile profile = this.profileRepository.findByIdOptional(id)
        .orElseThrow(() -> new NotFoundException("Profile not found"));

    List<ContactInformation> contactInfos = contactInfoDtos.stream()
        .map(contactDTO ->
            contactDTO.getId() == null
                ? this.createContactInfo(contactDTO, id)
                : this.updateContactInfo(contactDTO))
        .toList();

    List<ContactInformation> updatedContactInfos = profile.getContactInformation();
    updatedContactInfos.clear();
    updatedContactInfos.addAll(contactInfos);

    this.profileRepository.persist(profile);
    return contactInfos;
  }

  private ContactInformation updateContactInfo(DTO contactDTO) {
    if (contactDTO.getId() == null) {
      throw new WebApplicationException(
          "Contact Information id must not be null for updating a contact Information",
          Status.BAD_REQUEST
      );
    }

    ContactInformation contactInformation = this.contactInfoRepository.findByIdOptional(
            contactDTO.getId())
        .orElseThrow(() -> new NotFoundException("Contact Information not found"));

    if (StringUtil.isNullOrEmpty(contactDTO.getType())) {
      contactInformation.setType(contactDTO.getType());
    }

    if (StringUtil.isNullOrEmpty(contactDTO.getValue())) {
      contactInformation.setValue(contactDTO.getValue());
    }

    this.contactInfoRepository.persist(contactInformation);
    return contactInformation;
  }

  private ContactInformation createContactInfo(ContactInformation.DTO contactDTO, Long id) {
    if (contactDTO.getId() != null) {
      throw new WebApplicationException(
          "Contact Information id must be null for creating a new contact Information",
          Status.BAD_REQUEST
      );
    }

    if (StringUtil.isNullOrEmpty(contactDTO.getType())) {
      throw new WebApplicationException(
          "Contact Information type must not be null or empty",
          Status.BAD_REQUEST
      );
    }

    if (StringUtil.isNullOrEmpty(contactDTO.getValue())) {
      throw new WebApplicationException(
          "Contact Information value must not be null or empty",
          Status.BAD_REQUEST
      );
    }

    ContactInformation contactInformation = new ContactInformation();
    contactInformation.setType(contactDTO.getType());
    contactInformation.setValue(contactDTO.getValue());
    contactInformation.setProfile(this.entityManager.getReference(Profile.class, id));

    return contactInformation;
  }
}
