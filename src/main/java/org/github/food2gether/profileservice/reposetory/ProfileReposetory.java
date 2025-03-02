package org.github.food2gether.profileservice.reposetory;

import com.github.food2gether.shared.model.Profile;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.util.List;

public interface ProfileReposetory extends PanacheRepository<Profile> {
  List<Profile> listAllForQuery(String searchQuery);
}
