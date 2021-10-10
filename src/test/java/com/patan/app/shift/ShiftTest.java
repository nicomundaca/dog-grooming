package com.patan.app.shift;

import com.patan.app.createuser.Create;
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
        User user = createUser();
        List<Shift> shiftList = shiftService.getFilterdShift(ShiftState.DONE, null, null, null, user);
        Assert.assertEquals(5, shiftList.size());
        List<Shift> shiftList1 = shiftService.getFilterdShift(null, null, null, null, user);
        Assert.assertEquals(15, shiftList1.size());
    }

    @Test
    public void showShiftListDate() {
        User user = createUser();
        DateTime from = new DateTime(2015, 1, 1, 1, 0);
        Date fromDate = from.toDate();
        DateTime to = new DateTime(2020, 12, 30, 1, 0);
        Date toDate = to.toDate();

        List<Shift> shiftList = shiftService.getFilterdShift(null, fromDate, toDate, null, user);
        Assert.assertEquals(6, shiftList.size());
        List<Shift> shiftList1 = shiftService.getFilterdShift(null, null, toDate, null, user);
        Assert.assertEquals(12, shiftList1.size());
        List<Shift> shiftList2 = shiftService.getFilterdShift(null, fromDate, null, null, user);
        Assert.assertEquals(9, shiftList2.size());
        List<Shift> shiftList3 = shiftService.getFilterdShift(null, null, null, null, user);
        Assert.assertEquals(15, shiftList3.size());
    }

    @Test
    public void showShiftListTreatment() {
        User user = createUser();

        List<Shift> shiftList = shiftService.getFilterdShift(null, null, null, Treatment.SANITARY_CUT, user);
        Assert.assertEquals(3, shiftList.size());
        List<Shift> shiftList1 = shiftService.getFilterdShift(null, null, null, null, user);
        Assert.assertEquals(15, shiftList1.size());
    }


}
