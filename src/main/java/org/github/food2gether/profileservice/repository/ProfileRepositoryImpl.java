package org.github.food2gether.profileservice.repository;

import com.github.food2gether.shared.model.Profile;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ProfileRepositoryImpl implements ProfileRepository {

  @Override
  public List<Profile> listAllForQuery(String searchQuery) {
    return this.list("displayName LIKE ?1", "%" + searchQuery + "%");
  }

  @Override
  public List<Profile> listByPrimaryEmail(String email) {
    return this.list("primaryEmail", email);
  }
}
