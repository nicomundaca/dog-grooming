package com.patan.app.services;

import com.patan.app.dao.UserDAO;
import com.patan.app.dto.ShiftDTO;
import com.patan.app.exceptions.CommonException;
import com.patan.app.exceptions.FilterException;
import com.patan.app.models.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShiftService {

    private final UserDAO userDAO;

    private DateTime fromDateFinal = new DateTime(2000, 1, 1, 0, 0, 0);
    private DateTime toDateFinal = new DateTime(2099, 12, 30, 0, 0, 0);

    @Autowired
    public ShiftService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public List<ShiftDTO> showList(Long userID, ShiftState state, Date fromDate, Date toDate, Treatment treatment) throws CommonException {
        Optional<User> userOptional = userDAO.findById(userID);
        if (!userOptional.isPresent()) {
            throw new CommonException("El usuario: " + userID + " no existe");
        }
        User user = userOptional.get();
        List<ShiftDTO> dtoList = new ArrayList<>();

        List<Shift> shiftList = getFilterdShift(state, fromDate, toDate, treatment, user);
        for (Shift a : shiftList) {
            ShiftDTO shiftDTO = new ShiftDTO(a.getClientId(), a.getPetId(), a.getDate(), a.getTreatment(), a.getState(), a.getPrice(), a.getTotalPrice(), a.getExtraSales());
            dtoList.add(shiftDTO);
        }
        return dtoList;
    }

    public List<Shift> getFilterdShift(ShiftState state, Date fromDate, Date toDate, Treatment treatment, User user) {
        return user.getShifts().stream()
                .filter(shift -> isValidState(shift.getState(), state))
                .filter(shift -> isValidTreatment(shift.getTreatment(), treatment))
                .filter(shift -> isValidDate(shift.getDate(), fromDate, toDate))
                .collect(Collectors.toList());
    }

    private boolean isValidDate(Date date, Date fromDate, Date toDate) {
        Date fromFinal = fromDateFinal.toDate();
        Date toFinal = toDateFinal.toDate();
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

    private boolean isValidState(ShiftState state, ShiftState paramState) {
        return paramState == null || state.equals(paramState);
    }


    public void save(Long userID, ShiftDTO aDTO) throws CommonException {
        Optional<User> userOptional = userDAO.findById(userID);
        if (!userOptional.isPresent()) {
            throw new CommonException("el usuario: " + userID + " no existe");
        }
        User user = userOptional.get();
        Shift shift = new Shift(aDTO.getClientId(), aDTO.getPetId(), aDTO.getDate(), aDTO.getTreatment(), aDTO.getState(), aDTO.getPrice(), aDTO.getTotalPrice(), aDTO.getExtraSales());
        user.getShifts().add(shift);
        userDAO.save(user);
    }


    public ShiftDTO show(Long userID, Long shiftID) throws CommonException, FilterException {
        Optional<User> userOptional = userDAO.findById(userID);
        if (!userOptional.isPresent()) {
            throw new CommonException("El usuario: " + userID + " no existe");
        }
        User user = userOptional.get();
        Optional<Shift> shiftOptional = user.getShifts().stream().filter(shift1 -> shift1.getId().equals(shiftID)).findFirst();
        if (!shiftOptional.isPresent()) {
            throw new FilterException("el turno: " + shiftID + " no existe");
        }
        Shift s = shiftOptional.get();
        return new ShiftDTO(s.getId(), s.getClientId(), s.getPetId(), s.getDate(), s.getTreatment(), s.getState(), s.getPrice(), s.getTotalPrice(), s.getExtraSales());
    }

    public List<ShiftDTO> showAllShift() {
        List<User> userList = userDAO.findAll();
        List<ShiftDTO> dtoList = new ArrayList<>();
        for (User user : userList) {
            for (Shift s : user.getShifts()) {
                ShiftDTO shiftDTO = new ShiftDTO(s.getId(), s.getClientId(), s.getPetId(), s.getDate(), s.getTreatment(), s.getState(), s.getPrice(), s.getTotalPrice(), s.getExtraSales());
                dtoList.add(shiftDTO);
            }
        }
        return dtoList;
    }

    public Integer quantityShift(List<Shift> shiftList, Date fromDate, Date toDate) throws CommonException {

        List<Shift> list = shiftList.stream().filter(shift -> isValidDate(shift.getDate(), fromDate, toDate))
                                             .filter(shift -> shift.getState().equals(ShiftState.DONE))
                                             .collect(Collectors.toList());
        return list.size();
    }

    public Integer collectShifts(List<Shift> shiftList, Date fromDate, Date toDate) throws CommonException {
        Integer total = 0;

        List<Shift> list = shiftList.stream().filter(shift -> isValidDate(shift.getDate(), fromDate, toDate)).collect(Collectors.toList());
        for (Shift shift : list) {
            total = total + shift.getTotalPrice();
        }
        return total;
    }

    public Integer totalHaircutAndBathing(List<Shift> shiftList, Date fromDate, Date toDate) {

        List<Shift> list = shiftList.stream().filter(shift -> shift.getTreatment().equals(Treatment.HAIRCUT_AND_BATHING))
                .filter(shift -> isValidDate(shift.getDate(), fromDate, toDate)).collect(Collectors.toList());
        return list.size();
    }

    public Integer totalScissorHaircutAndBathing(List<Shift> shiftList, Date fromDate, Date toDate) {

        List<Shift> list = shiftList.stream().filter(shift -> shift.getTreatment().equals(Treatment.SCISSOR_HAIRCUT_AND_BATHING))
                .filter(shift -> isValidDate(shift.getDate(), fromDate, toDate)).collect(Collectors.toList());
        return list.size();
    }

    public Integer totalSanitaryCut(List<Shift> shiftList, Date fromDate, Date toDate) {
        List<Shift> list = shiftList.stream().filter(shift -> shift.getTreatment().equals(Treatment.SANITARY_CUT))
                .filter(shift -> isValidDate(shift.getDate(), fromDate, toDate)).collect(Collectors.toList());
        return list.size();
    }

    public Integer totalBathing(List<Shift> shiftList, Date fromDate, Date toDate) {
        List<Shift> list = shiftList.stream().filter(shift -> shift.getTreatment().equals(Treatment.BATHING))
                .filter(shift -> isValidDate(shift.getDate(), fromDate, toDate)).collect(Collectors.toList());
        return list.size();
    }


    public Summary summaryShift(Long userID, Date fromDate, Date toDate) throws CommonException {
        Optional<User> userOptional = userDAO.findById(userID);
        if (!userOptional.isPresent()) {
            throw new CommonException("el usuario con id: " + userID + " no existe");
        }
        User user = userOptional.get();
        List<Shift> shiftList = user.getShifts();
        Integer collectShifts = collectShifts(shiftList, fromDate, toDate);
        Integer quantityShift = quantityShift(shiftList, fromDate, toDate);
        Integer totalBathing = totalBathing(shiftList, fromDate, toDate);
        Integer totalHaircutAndBathing = totalHaircutAndBathing(shiftList, fromDate, toDate);
        Integer totalScissorHaircutAndBathing = totalScissorHaircutAndBathing(shiftList, fromDate, toDate);
        Integer totalSanitaryCut = totalSanitaryCut(shiftList, fromDate, toDate);

        return new Summary(quantityShift, collectShifts, totalHaircutAndBathing, totalScissorHaircutAndBathing, totalSanitaryCut, totalBathing);
    }
}
