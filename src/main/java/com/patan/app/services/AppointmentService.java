package com.patan.app.services;

import com.patan.app.dao.GroomerDAO;
import com.patan.app.dto.AppointmentDTO;
import com.patan.app.dto.requests.RequestAppointment;
import com.patan.app.dto.requests.RequestSummary;
import com.patan.app.exceptions.CommonException;
import com.patan.app.exceptions.FilterException;
import com.patan.app.models.*;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final GroomerDAO groomerDAO;

    private static final DateTime MIN_FROM_DATE = new DateTime(2000, 1, 1, 0, 0, 0);
    private static final DateTime MAX_TO_DATE = new DateTime(2099, 12, 30, 0, 0, 0);

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentService.class);

    @Autowired
    public AppointmentService(GroomerDAO groomerDAO) {
        this.groomerDAO = groomerDAO;
    }


    public List<AppointmentDTO> showList(RequestAppointment requestAppointment) throws CommonException {
        LOGGER.info("buscando turnos para el usuario {} ", requestAppointment.getGroomerID());
        Optional<Groomer> groomerOptional = groomerDAO.findById(requestAppointment.getGroomerID());
        if (!groomerOptional.isPresent()) {
            LOGGER.error("El usuario {} no existe", requestAppointment.getGroomerID());
            throw new CommonException("El usuario: " + requestAppointment.getGroomerID() + " no existe");
        }
        Groomer groomer = groomerOptional.get();
        List<AppointmentDTO> dtoList = new ArrayList<>();

        List<AppointmentEntity> appointmentEntityList = getFilterdAppointment(requestAppointment, groomer);
        for (AppointmentEntity a : appointmentEntityList) {
            AppointmentDTO appointmentDTO = new AppointmentDTO(a.getClientId(), a.getPetId(), a.getDate(), a.getTreatment(), a.getState(), a.getPrice(), a.getTotalPrice(), a.getExtraSales());
            dtoList.add(appointmentDTO);
        }
        return dtoList;
    }

    public List<AppointmentEntity> getFilterdAppointment(RequestAppointment requestAppointment, Groomer groomer) {
        LOGGER.info("comenzando a filtrar la lista de turnos con el request {} para el usuario {}", requestAppointment, groomer);
        return groomer.getAppointmentEntities().stream()
                .filter(appointment -> isValidState(appointment.getState(), requestAppointment.getAppointmentState()))
                .filter(appointment -> isValidPetId(appointment.getPetId(), requestAppointment.getPetID()))
                .filter(appointment -> isValidTreatment(appointment.getTreatment(), requestAppointment.getTypeTreatment()))
                .filter(appointment -> isValidDate(appointment.getDate(), requestAppointment.getFromDate(), requestAppointment.getToDate()))
                .collect(Collectors.toList());
    }

    private boolean isValidPetId(Long petId, Long paramPetID) {
        return paramPetID == null || paramPetID.equals(petId);
    }

    public boolean isValidDate(Date date, Date fromDate, Date toDate) {
        Date fromFinal = MIN_FROM_DATE.toDate();
        Date toFinal = MAX_TO_DATE.toDate();
        if (fromDate == null && toDate == null) {
            return true;
        } else if (fromDate == null) {
            return (date.after(fromFinal) && date.before(toDate));
        } else if (toDate == null) {
            return (date.after(fromDate) && date.before(toFinal));
        } else {
            return (date.after(fromDate) && date.before(toDate));
        }
    }


    private boolean isValidTreatment(Treatment treatment, Treatment paramTreatment) {
        return paramTreatment == null || paramTreatment.equals(treatment);

    }

    private boolean isValidState(AppointmentState state, AppointmentState paramState) {
        return paramState == null || state.equals(paramState);
    }


    public void save(Long groomerID, List<AppointmentDTO> appointmentDTOS) throws CommonException {
        Optional<Groomer> groomerOptional = groomerDAO.findById(groomerID);
        if (!groomerOptional.isPresent()) {
            LOGGER.error("el usuario {} no existe", groomerID);
            throw new CommonException("el usuario: " + groomerID + " no existe");
        }
        Groomer groomer = groomerOptional.get();
        for (AppointmentDTO s : appointmentDTOS) {
            AppointmentEntity appointmentEntity = new AppointmentEntity(s.getClientId(), s.getPetId(), s.getDate(), s.getTreatment(), s.getState(), s.getPrice(), s.getTotalPrice(), s.getExtraSales());
            groomer.getAppointmentEntities().add(appointmentEntity);
        }
        groomerDAO.save(groomer);
    }

    public void deleteAppointment(Long groomerID, Long appointmentID) throws CommonException {
        LOGGER.info("buscando al usuario del turno a borrar");
        Optional<Groomer> groomerOptional = groomerDAO.findById(groomerID);
        if (!groomerOptional.isPresent()) {
            LOGGER.error("el usuario {} no existe", groomerID);
            throw new CommonException("el usuario: " + groomerID + " no existe");
        }
        Groomer groomer = groomerOptional.get();
        LOGGER.info("buscando en la lista de turnos el elemento a borrar");
        Optional<AppointmentEntity> appointmentEntityOptional = groomer.getAppointmentEntities().stream().filter(appointmentEntity1 -> appointmentEntity1.getId().equals(appointmentID)).findFirst();
        if (!appointmentEntityOptional.isPresent()) {
            LOGGER.error("el turno {} no existe", appointmentID);
            throw new CommonException("el turno" + appointmentID + " no existe");
        }
        AppointmentEntity appointmentEntity = appointmentEntityOptional.get();
        appointmentEntity.setIsDeleted(true);
        groomerDAO.save(groomer);
    }


    public AppointmentDTO show(Long groomerID, Long appointmentID) throws CommonException, FilterException {
        LOGGER.info("Buscando el turno para el usuario {}", groomerID);
        Optional<Groomer> groomerOptional = groomerDAO.findById(groomerID);
        if (!groomerOptional.isPresent()) {
            LOGGER.error("El usuario {} no existe ", groomerID);
            throw new CommonException("El usuario: " + groomerID + " no existe");
        }
        Groomer groomer = groomerOptional.get();
        Optional<AppointmentEntity> appointmentEntityOptional = groomer.getAppointmentEntities().stream().filter(appointmentEntity -> appointmentEntity.getId().equals(appointmentID)).findFirst();
        if (!appointmentEntityOptional.isPresent()) {
            LOGGER.error("el turno {} no existe", appointmentID);
            throw new FilterException("el turno: " + appointmentID + " no existe");
        }
        AppointmentEntity s = appointmentEntityOptional.get();
        return new AppointmentDTO(s.getId(), s.getClientId(), s.getPetId(), s.getDate(), s.getTreatment(), s.getState(), s.getPrice(), s.getTotalPrice(), s.getExtraSales());
    }

    public List<AppointmentDTO> showAllAppointment() {
        LOGGER.info("buscando todos los turnos para todos los usuarios");
        List<Groomer> groomerList = groomerDAO.findAll();
        List<AppointmentDTO> dtoList = new ArrayList<>();
        for (Groomer groomer : groomerList) {
            for (AppointmentEntity s : groomer.getAppointmentEntities()) {
                AppointmentDTO appointmentDTO = new AppointmentDTO(s.getId(), s.getClientId(), s.getPetId(), s.getDate(), s.getTreatment(), s.getState(), s.getPrice(), s.getTotalPrice(), s.getExtraSales());
                dtoList.add(appointmentDTO);
            }
        }
        return dtoList;
    }

    public Integer quantityAppointment(List<AppointmentEntity> appointmentEntityList) throws CommonException {
        return appointmentEntityList.size();
    }

    public Integer collectAppointments(List<AppointmentEntity> appointmentEntityList) throws CommonException {
        Integer total = 0;
        for (AppointmentEntity appointmentEntity : appointmentEntityList) {
            total = total + appointmentEntity.getTotalPrice();
        }
        return total;
    }

    public Integer totalHaircutAndBathing(List<AppointmentEntity> appointmentEntityList) {

        List<AppointmentEntity> list = appointmentEntityList.stream().filter(appointmentEntity -> appointmentEntity.getTreatment().equals(Treatment.HAIRCUT_AND_BATHING))
                .collect(Collectors.toList());
        return list.size();
    }

    public Integer totalScissorHaircutAndBathing(List<AppointmentEntity> appointmentEntityList) {

        List<AppointmentEntity> list = appointmentEntityList.stream().filter(appointmentEntity -> appointmentEntity.getTreatment().equals(Treatment.SCISSOR_HAIRCUT_AND_BATHING))
                .collect(Collectors.toList());
        return list.size();
    }

    public Integer totalSanitaryCut(List<AppointmentEntity> appointmentEntityList) {
        List<AppointmentEntity> list = appointmentEntityList.stream().filter(appointmentEntity -> appointmentEntity.getTreatment().equals(Treatment.SANITARY_CUT))
                .collect(Collectors.toList());
        return list.size();
    }

    public Integer totalBathing(List<AppointmentEntity> appointmentEntityList) {
        List<AppointmentEntity> list = appointmentEntityList.stream().filter(appointmentEntity -> appointmentEntity.getTreatment().equals(Treatment.BATHING))
                .collect(Collectors.toList());
        return list.size();
    }


    public Summary summaryAppointment(RequestSummary requestSummary) throws CommonException {
        Optional<Groomer> groomerOptional = groomerDAO.findById(requestSummary.getGroomerID());
        if (!groomerOptional.isPresent()) {
            LOGGER.error("el usuario con id {} no existe ", requestSummary.getGroomerID());
            throw new CommonException("el usuario con id: " + requestSummary.getGroomerID() + " no existe");
        }
        Groomer groomer = groomerOptional.get();
        List<AppointmentEntity> appointmentEntityList = groomer.getAppointmentEntities().stream().filter(appointmentEntity -> appointmentEntity.getState().equals(AppointmentState.DONE))
                .filter(appointmentEntity -> isValidDate(appointmentEntity.getDate(), requestSummary.getFromDate(), requestSummary.getToDate()))
                .collect(Collectors.toList());
        Integer collectAppointments = collectAppointments(appointmentEntityList);
        Integer quantityAppointment = quantityAppointment(appointmentEntityList);
        Integer totalBathing = totalBathing(appointmentEntityList);
        Integer totalHaircutAndBathing = totalHaircutAndBathing(appointmentEntityList);
        Integer totalScissorHaircutAndBathing = totalScissorHaircutAndBathing(appointmentEntityList);
        Integer totalSanitaryCut = totalSanitaryCut(appointmentEntityList);

        return new Summary(quantityAppointment, collectAppointments, totalHaircutAndBathing, totalScissorHaircutAndBathing, totalSanitaryCut, totalBathing);
    }
}
