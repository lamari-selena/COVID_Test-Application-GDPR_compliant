package covidlocation.services;

import covidlocation.models.Location;
import covidlocation.repositories.LocationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class LocationServiceTest {
    @Mock
    private LocationRepository serviceTested;
    private Location location;

    @BeforeEach
    @Disabled
    void setUp() {
        location = new Location();
    }

    @AfterEach
    @Disabled
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    @Disabled
    void addLocation() {
    }

    @Test
    @Disabled
    void findAllLocations() {
        serviceTested.findAllLocations();
        verify(locationRepository).findAll();
    }

    @Test
    @Disabled
    void findLocationByID() {
    }

    @Test
    @Disabled
    void deleteLocation() {
    }

    @Test
    @Disabled
    void updateLocation() {
    }

    @Test
    @Disabled
    void findLocationByUser() {
    }
}