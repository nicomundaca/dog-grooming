package com.patan.app.shift;

import com.patan.app.createuser.Create;
import com.patan.app.dto.requests.RequestShift;
import com.patan.app.models.ShiftEntity;
import com.patan.app.models.ShiftState;
import com.patan.app.models.Treatment;
import com.patan.app.models.User;
import com.patan.app.services.ShiftService;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class ShiftEntityTest extends Create {
    private ShiftService shiftService = new ShiftService(null);

    @Test
    public void showShiftListState() {
        RequestShift requestShift = new RequestShift();
        requestShift.setShiftState(ShiftState.DONE);
        User user = createUser();
        List<ShiftEntity> shiftEntityList = shiftService.getFilterdShift(requestShift, user);
        Assert.assertEquals(5, shiftEntityList.size());
        requestShift.setShiftState(null);
        List<ShiftEntity> shiftEntityList1 = shiftService.getFilterdShift(requestShift, user);
        Assert.assertEquals(15, shiftEntityList1.size());
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

        List<ShiftEntity> shiftEntityList = shiftService.getFilterdShift(requestShift, user);
        Assert.assertEquals(6, shiftEntityList.size());
        List<ShiftEntity> shiftEntityList1 = shiftService.getFilterdShift(requestShift, user);
        Assert.assertEquals(12, shiftEntityList1.size());
        List<ShiftEntity> shiftEntityList2 = shiftService.getFilterdShift(requestShift, user);
        Assert.assertEquals(9, shiftEntityList2.size());
        List<ShiftEntity> shiftEntityList3 = shiftService.getFilterdShift(null, user);
        Assert.assertEquals(15, shiftEntityList3.size());
    }

    @Test
    public void showShiftListTreatment() {
        User user = createUser();
        RequestShift requestShift = new RequestShift();
        requestShift.setTypeTreatment(Treatment.SANITARY_CUT);

        List<ShiftEntity> shiftEntityList = shiftService.getFilterdShift(requestShift, user);
        Assert.assertEquals(3, shiftEntityList.size());
        List<ShiftEntity> shiftEntityList1 = shiftService.getFilterdShift(null, user);
        Assert.assertEquals(15, shiftEntityList1.size());
    }


}
