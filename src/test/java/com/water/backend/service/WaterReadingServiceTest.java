package com.water.backend.service;

import com.water.backend.dto.request.WaterReadingRequest;
import com.water.backend.entity.*;
import com.water.backend.repository.*;
import com.water.backend.service.impl.WaterReadingServiceImpl;
import org.junit.jupiter.api.Test; import org.junit.jupiter.api.extension.ExtendWith; import org.mockito.*; import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate; import java.util.*; import static org.junit.jupiter.api.Assertions.*; import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) class WaterReadingServiceTest {
 @Mock WaterReadingRepository readingRepo; @Mock ResidentRepository residentRepo; @Mock WaterTariffRepository tariffRepo; @Mock TariffTierRepository tierRepo; @Mock WaterMeterRepository meterRepo; @InjectMocks WaterReadingServiceImpl service;
 @Test void rejectsDecreasingReading(){Resident r=Resident.builder().id(1L).community(Community.builder().id(1L).build()).build(); when(residentRepo.findById(1L)).thenReturn(Optional.of(r)); when(readingRepo.findTopByResidentOrderByReadingDateDesc(r)).thenReturn(Optional.of(WaterReading.builder().currentReading(100.0).build())); WaterReadingRequest q=new WaterReadingRequest();q.setResidentId(1L);q.setCurrentReading(90.0);q.setReadingDate(LocalDate.now()); assertThrows(RuntimeException.class,()->service.addReading(q));}
}
