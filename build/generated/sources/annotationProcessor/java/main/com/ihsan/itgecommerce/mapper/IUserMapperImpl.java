package com.ihsan.itgecommerce.mapper;

import com.ihsan.itgecommerce.dto.request.CreateAdminRequestDto;
import com.ihsan.itgecommerce.dto.request.RegisterRequestDto;
import com.ihsan.itgecommerce.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-10T10:26:00+0300",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public UserEntity toUser(RegisterRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.email( dto.getEmail() );
        userEntity.password( dto.getPassword() );
        userEntity.firstName( dto.getFirstName() );
        userEntity.lastName( dto.getLastName() );

        return userEntity.build();
    }

    @Override
    public UserEntity toUser(CreateAdminRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.email( dto.getEmail() );
        userEntity.password( dto.getPassword() );
        userEntity.firstName( dto.getFirstName() );
        userEntity.lastName( dto.getLastName() );

        return userEntity.build();
    }
}
