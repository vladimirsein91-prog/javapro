package ru.vtb.mappers;

import org.mapstruct.ReportingPolicy;
import ru.vtb.dto.LimitOperationDto;

@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public class OperationMapper {

}
