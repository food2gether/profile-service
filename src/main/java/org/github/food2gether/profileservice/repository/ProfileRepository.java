package org.github.food2gether.profileservice.repository;

import com.github.food2gether.shared.model.Profile;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.util.List;

public interface ProfileRepository extends PanacheRepository<Profile> {
  List<Profile> listAllForQuery(String searchQuery);

  List<Profile> listByPrimaryEmail(String email);

}
