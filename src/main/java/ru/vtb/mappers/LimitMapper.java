package ru.vtb.mappers;

import org.mapstruct.ReportingPolicy;
import ru.vtb.dto.LimitDTO;
import ru.vtb.entity.LimitEntity;

@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface LimitMapper {
     LimitDTO map(LimitEntity limitEntity);
}
