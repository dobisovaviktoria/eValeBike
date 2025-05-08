package integration4.evalebike.controller.technician.dto;

import integration4.evalebike.domain.Bike;
import integration4.evalebike.domain.BikeSize;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class BikeDto {
    private String bikeQR;
    private String qrCodeImage;
    private String brand;
    private String model;
    private String chassisNumber;
    private Integer productionYear;
    private BikeSize bikeSize;
    private Integer mileage;
    private String gearType;
    private String engineType;
    private String powerTrain;

    @NotNull(message = "Battery capacity is required")
    @DecimalMin(value = "0.01", message = "Battery capacity must be at least 0.01")
    @DecimalMax(value = "2000", message = "Battery capacity cannot exceed 2000.00")
    private double accuCapacity;

    @NotNull(message = "Max support is required")
    @DecimalMin(value = "0.01", message = "Max support must be at least 0.01")
    @DecimalMax(value = "2000", message = "Max support cannot exceed 2000")
    private double maxSupport;

    @NotNull(message = "Max engine power is required")
    @DecimalMin(value = "0.01", message = "Max engine power must be at least 0.01")
    @DecimalMax(value = "2000", message = "Max engine power cannot exceed 2000")
    private double maxEnginePower;

    @NotNull(message = "Nominal engine power is required")
    @DecimalMin(value = "0.01", message = "Nominal engine power must be at least 0.01")
    @DecimalMax(value = "2000", message = "Nominal engine power cannot exceed 2000")
    private double nominalEnginePower;

    @NotNull(message = "Engine torque is required")
    @DecimalMin(value = "0.01", message = "Engine torque must be at least 0.01")
    @DecimalMax(value = "2000", message = "Engine torque cannot exceed 2000")
    private double engineTorque;

    private LocalDate lastTestDate;


    public BikeDto(String bikeQR, String qrCodeImage, String brand, String model, String chassisNumber, Integer productionYear, BikeSize bikeSize, Integer mileage, String gearType, String engineType, String powerTrain, double accuCapacity, double maxSupport, double maxEnginePower, double nominalEnginePower, double engineTorque, LocalDate lastTestDate) {
        this.bikeQR = bikeQR;
        this.qrCodeImage = qrCodeImage;
        this.brand = brand;
        this.model = model;
        this.chassisNumber = chassisNumber;
        this.productionYear = productionYear;
        this.bikeSize = bikeSize;
        this.mileage = mileage;
        this.gearType = gearType;
        this.engineType = engineType;
        this.powerTrain = powerTrain;
        this.accuCapacity = accuCapacity;
        this.maxSupport = maxSupport;
        this.maxEnginePower = maxEnginePower;
        this.nominalEnginePower = nominalEnginePower;
        this.engineTorque = engineTorque;
        this.lastTestDate = lastTestDate;
    }


    public static BikeDto fromBike(final Bike bike) {
        return new BikeDto(bike.getBikeQR(), bike.getQrCodeImage(), bike.getBrand(), bike.getModel(), bike.getChassisNumber(), bike.getProductionYear(), bike.getBikeSize(), bike.getMileage(), bike.getGearType(), bike.getEngineType(), bike.getPowerTrain(), bike.getAccuCapacity(), bike.getMaxSupport(), bike.getMaxEnginePower(), bike.getNominalEnginePower(), bike.getEngineTorque(), bike.getLastTestDate());
    }

    public static BikeDto toBikeDto(Bike bike) {
        if (bike == null) {
            return null;
        }

        return new BikeDto(bike.getBikeQR(), bike.getQrCodeImage(), bike.getBrand(), bike.getModel(), bike.getChassisNumber(), bike.getProductionYear(), bike.getBikeSize(), bike.getMileage(), bike.getGearType(), bike.getEngineType(), bike.getPowerTrain(), bike.getAccuCapacity(), bike.getMaxSupport(), bike.getMaxEnginePower(), bike.getNominalEnginePower(), bike.getEngineTorque(), bike.getLastTestDate());
    }

    @NotNull(message = "Battery capacity is required")
    @DecimalMin(value = "0.01", message = "Battery capacity must be at least 0.01")
    @DecimalMax(value = "2000", message = "Battery capacity cannot exceed 2000.00")
    public double getAccuCapacity() {
        return accuCapacity;
    }

    public void setAccuCapacity(@NotNull(message = "Battery capacity is required") @DecimalMin(value = "0.01", message = "Battery capacity must be at least 0.01") @DecimalMax(value = "2000", message = "Battery capacity cannot exceed 2000.00") double accuCapacity) {
        this.accuCapacity = accuCapacity;
    }

    public String getBikeQR() {
        return bikeQR;
    }

    public void setBikeQR(String bikeQR) {
        this.bikeQR = bikeQR;
    }

    public BikeSize getBikeSize() {
        return bikeSize;
    }

    public void setBikeSize(BikeSize bikeSize) {
        this.bikeSize = bikeSize;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    @NotNull(message = "Engine torque is required")
    @DecimalMin(value = "0.01", message = "Engine torque must be at least 0.01")
    @DecimalMax(value = "2000", message = "Engine torque cannot exceed 2000")
    public double getEngineTorque() {
        return engineTorque;
    }

    public void setEngineTorque(@NotNull(message = "Engine torque is required") @DecimalMin(value = "0.01", message = "Engine torque must be at least 0.01") @DecimalMax(value = "2000", message = "Engine torque cannot exceed 2000") double engineTorque) {
        this.engineTorque = engineTorque;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getGearType() {
        return gearType;
    }

    public void setGearType(String gearType) {
        this.gearType = gearType;
    }

    public LocalDate getLastTestDate() {
        return lastTestDate;
    }

    public void setLastTestDate(LocalDate lastTestDate) {
        this.lastTestDate = lastTestDate;
    }

    @NotNull(message = "Max engine power is required")
    @DecimalMin(value = "0.01", message = "Max engine power must be at least 0.01")
    @DecimalMax(value = "2000", message = "Max engine power cannot exceed 2000")
    public double getMaxEnginePower() {
        return maxEnginePower;
    }

    public void setMaxEnginePower(@NotNull(message = "Max engine power is required") @DecimalMin(value = "0.01", message = "Max engine power must be at least 0.01") @DecimalMax(value = "2000", message = "Max engine power cannot exceed 2000") double maxEnginePower) {
        this.maxEnginePower = maxEnginePower;
    }

    @NotNull(message = "Max support is required")
    @DecimalMin(value = "0.01", message = "Max support must be at least 0.01")
    @DecimalMax(value = "2000", message = "Max support cannot exceed 2000")
    public double getMaxSupport() {
        return maxSupport;
    }

    public void setMaxSupport(@NotNull(message = "Max support is required") @DecimalMin(value = "0.01", message = "Max support must be at least 0.01") @DecimalMax(value = "2000", message = "Max support cannot exceed 2000") double maxSupport) {
        this.maxSupport = maxSupport;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @NotNull(message = "Nominal engine power is required")
    @DecimalMin(value = "0.01", message = "Nominal engine power must be at least 0.01")
    @DecimalMax(value = "2000", message = "Nominal engine power cannot exceed 2000")
    public double getNominalEnginePower() {
        return nominalEnginePower;
    }

    public void setNominalEnginePower(@NotNull(message = "Nominal engine power is required") @DecimalMin(value = "0.01", message = "Nominal engine power must be at least 0.01") @DecimalMax(value = "2000", message = "Nominal engine power cannot exceed 2000") double nominalEnginePower) {
        this.nominalEnginePower = nominalEnginePower;
    }

    public String getPowerTrain() {
        return powerTrain;
    }

    public void setPowerTrain(String powerTrain) {
        this.powerTrain = powerTrain;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public String getQrCodeImage() {
        return qrCodeImage;
    }

    public void setQrCodeImage(String qrCodeImage) {
        this.qrCodeImage = qrCodeImage;
    }
}