package io.github.henriquemcc.dtidigital.testepratico.model;

public class DroneTest {
    public static Drone build() {
        return new Drone(
                "Drone INC",
                "CargoWing Vortex 300",
                "1234567890",
                20,
                100
        );
    }
}
