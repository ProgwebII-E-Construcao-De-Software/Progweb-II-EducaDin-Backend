package com.g2.Progweb_II_EducaDin_Backend.controller;

import br.ueg.progweb2.arquitetura.controllers.GenericCRUDController;
import com.g2.Progweb_II_EducaDin_Backend.mapper.NotificationPreferenceMapper;
import com.g2.Progweb_II_EducaDin_Backend.model.NotificationPreference;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.NotificationPreferenceDTO;
import com.g2.Progweb_II_EducaDin_Backend.model.dto.NotificationPreferenceDTOCreateUpdate;
import com.g2.Progweb_II_EducaDin_Backend.service.NotificationPreferenceService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "1.0/notification-preferences")
@CrossOrigin()
public class NotificationPreferenceController extends GenericCRUDController<
        NotificationPreferenceDTO,
        NotificationPreferenceDTOCreateUpdate,
        NotificationPreferenceDTOCreateUpdate,
        NotificationPreferenceDTO,
        NotificationPreference,
        Long,
        NotificationPreferenceService,
        NotificationPreferenceMapper> {
}