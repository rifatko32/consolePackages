package ru.hofftech.consolepackages.service.packageitem.engine.impl;

import org.junit.jupiter.api.Test;
import ru.hofftech.consolepackages.service.packageitem.Package;
import ru.hofftech.consolepackages.service.packageitem.engine.impl.PackagePlaceByWidthAlgorithm;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PackagePlaceByWidthAlgorithmTest {
    //generateReport_givenTruckList_shouldReturnReport
    @Test
    public void placePackages_givenPackages_shouldReturnFilledTruck() {
        // Arrange
        var packageStrings = List.of("1", "22");
        var engine = new PackagePlaceByWidthAlgorithm();
        int[] [] expectedBackTruck = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 2, 2}};

        var packages = new ArrayList<Package>();

        for(String curPackageString : packageStrings){
            packages.add(new Package(curPackageString));
        }

        // Act
        var result = engine.placePackages(packages, 5);
        var truck = result.stream().findFirst().orElse(null);

        // Assert
        assertThat(truck).isNotNull();
        assertThat(truck.getBackTruckSlots()).isEqualTo(expectedBackTruck);
    }

    @Test
    public void placePackages_givenEmptyListOfPackages_shouldReturnFilledTruck() {
        // Arrange
        var packages = new ArrayList<Package>();
        var engine = new PackagePlaceByWidthAlgorithm();

        // Act
        var result = engine.placePackages(packages, 6);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.size()).isZero();
    }
}
