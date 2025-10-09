package ru.vtb.mappers;

import org.mapstruct.ReportingPolicy;
import ru.vtb.entity.UserEntity;
import ru.vtb.pojo.User;

import java.util.List;
import java.util.Optional;

@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface MapperUser {
    List<User> map(List<UserEntity> user);

    User map(UserEntity user);

    UserEntity map(User user);

}
