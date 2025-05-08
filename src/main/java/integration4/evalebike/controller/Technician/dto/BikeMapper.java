//package integration4.evalebike.controller.technician.dto;
//
//import integration4.evalebike.domain.Bike;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Mappings;
//
//@Mapper(componentModel = "spring")
//public interface BikeMapper {
//    @Mappings({
//            @Mapping(source = "bikeQR", target = "bikeQR"),
//            @Mapping(source = "qrCodeImage", target = "qrCodeImage"),
//            @Mapping(source = "brand", target = "brand"),
//            @Mapping(source = "model", target = "model"),
//            @Mapping(source = "chassisNumber", target = "chassisNumber"),
//            @Mapping(source = "productionYear", target = "productionYear"),
//            @Mapping(source = "bikeSize", target = "bikeSize"),
//            @Mapping(source = "mileage", target = "mileage"),
//            @Mapping(source = "gearType", target = "gearType"),
//            @Mapping(source = "engineType", target = "engineType"),
//            @Mapping(source = "powerTrain", target = "powerTrain"),
//            @Mapping(source = "accuCapacity", target = "accuCapacity"),
//            @Mapping(source = "maxSupport", target = "maxSupport"),
//            @Mapping(source = "maxEnginePower", target = "maxEnginePower"),
//            @Mapping(source = "nominalEnginePower", target = "nominalEnginePower"),
//            @Mapping(source = "engineTorque", target = "engineTorque"),
//            @Mapping(source = "lastTestDate", target = "lastTestDate")
//    })
//    BikeDto toBikeDto(Bike bike);
//}
