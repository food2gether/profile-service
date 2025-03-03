package org.github.food2gether.profileservice;

import com.github.food2gether.shared.model.ContactInformation;
import com.github.food2gether.shared.model.Profile;
import com.github.food2gether.shared.response.DataAPIResponse;
import com.github.food2gether.shared.response.ErrorAPIResponse;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection(
    targets = {
        Profile.DTO.class,
        ContactInformation.DTO.class,
        DataAPIResponse.class,
        ErrorAPIResponse.class
    }
)
public class ReflectionConfiguration {

}
