package com.epam.training.ticketservice.core.screening;

import com.epam.training.ticketservice.core.screening.impl.ScreeningServiceImpl;
import com.epam.training.ticketservice.core.screening.persistance.repository.ScreeningRepository;
import com.epam.training.ticketservice.util.FormatUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ScreeningServiceImplTest {

    @Mock
    static private ScreeningRepository screeningRepository;

    @InjectMocks
    static private ScreeningServiceImpl screeningService;

    @Test
    void getScreeningId() {

        String screeningDate = "2021-11-29 17:00";

        LocalDateTime testTime = LocalDateTime.parse(screeningDate, FormatUtil.getAppDateTimeFormatter());

        assertEquals(-1L, screeningService.getScreeningId("asd", "asd3", testTime));

    }
}