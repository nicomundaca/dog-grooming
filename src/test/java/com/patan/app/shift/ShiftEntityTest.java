package com.patan.app.shift;

import com.patan.app.createuser.Create;
import com.patan.app.dto.requests.RequestShift;
import com.patan.app.models.Shift;
import com.patan.app.models.ShiftState;
import com.patan.app.models.Treatment;
import com.patan.app.models.User;
import com.patan.app.services.ShiftService;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class ShiftTest extends Create {
    private ShiftService shiftService = new ShiftService(null);

    @Test
    public void showShiftListState() {
        RequestShift requestShift = new RequestShift();
        requestShift.setShiftState(ShiftState.DONE);
        User user = createUser();
        List<Shift> shiftList = shiftService.getFilterdShift(requestShift, user);
        Assert.assertEquals(5, shiftList.size());
        requestShift.setShiftState(null);
        List<Shift> shiftList1 = shiftService.getFilterdShift(requestShift, user);
        Assert.assertEquals(15, shiftList1.size());
    }

    @Test
    public void showShiftListDate() {
        User user = createUser();
        DateTime from = new DateTime(2015, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2020, 12, 30, 1, 0);
        Date toDate = to.toDate();
        RequestShift requestShift = new RequestShift();
        requestShift.setFromDate(fromDate);
        requestShift.setToDate(toDate);

        List<Shift> shiftList = shiftService.getFilterdShift(requestShift, user);
        Assert.assertEquals(6, shiftList.size());
        List<Shift> shiftList1 = shiftService.getFilterdShift(requestShift, user);
        Assert.assertEquals(12, shiftList1.size());
        List<Shift> shiftList2 = shiftService.getFilterdShift(requestShift, user);
        Assert.assertEquals(9, shiftList2.size());
        List<Shift> shiftList3 = shiftService.getFilterdShift(null, user);
        Assert.assertEquals(15, shiftList3.size());
    }

    @Test
    public void showShiftListTreatment() {
        User user = createUser();
        RequestShift requestShift = new RequestShift();
        requestShift.setTypeTreatment(Treatment.SANITARY_CUT);

        List<Shift> shiftList = shiftService.getFilterdShift(requestShift, user);
        Assert.assertEquals(3, shiftList.size());
        List<Shift> shiftList1 = shiftService.getFilterdShift(null, user);
        Assert.assertEquals(15, shiftList1.size());
    }


}
