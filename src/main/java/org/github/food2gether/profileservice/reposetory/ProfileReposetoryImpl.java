package org.github.food2gether.profileservice.reposetory;

import com.github.food2gether.shared.model.Profile;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ProfileReposetoryImpl implements ProfileReposetory {

  @Override
  public List<Profile> listAllForQuery(String searchQuery) {
    return this.list("displayName LIKE ?1", "%" + searchQuery + "%");
  }
}
