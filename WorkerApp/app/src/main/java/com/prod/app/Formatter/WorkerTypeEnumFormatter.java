package com.prod.app.Formatter;

import com.prod.app.protobuff.Workertype;
import com.prod.basic.common.httpCommon.Interfaces.IFormatter;

public class WorkerTypeEnumFormatter implements IFormatter<String, Workertype.WorkerTypeEnum> {
    @Override
    public String format(Workertype.WorkerTypeEnum workerTypeEnum) {

        switch (workerTypeEnum) {
            case MAID:
                return "Maid";
            case SALON:
                return "Salon";
            case CLEANING:
                return "Cleaning";
            case PAINTING:
                return "Painting";
            case PLUMBING:
                return "Plumbing";
            case CARPENTER:
                return "Carpenter";
            case CONSTRUCTOR:
                return "Construction";
            case ELECTRICIAN:
                return "Electrician";
            case PEST_CONTROL:
                return "Pest Control";
            case PACKERS_AND_MOVERS:
                return "Packers And Movers";
            case LAUNDARY_AND_DRY_CLEANING:
                return "Laundary And Cleaning";
            case UNKNOWN_WORKER_TYPE:
                return "";
            default:
                return "";
        }

    }

    public Workertype.WorkerTypeEnum format(int pos) {

        switch (pos) {
            case 1:
                return Workertype.WorkerTypeEnum.MAID;
            case 2:
                return Workertype.WorkerTypeEnum.SALON;
            case 3:
                return Workertype.WorkerTypeEnum.CLEANING;
            case 4:
                return Workertype.WorkerTypeEnum.PAINTING;
            case 5:
                return Workertype.WorkerTypeEnum.PLUMBING;
            case 6:
                return Workertype.WorkerTypeEnum.CARPENTER;
            case 7:
                return Workertype.WorkerTypeEnum.CONSTRUCTOR;
            case 8:
                return Workertype.WorkerTypeEnum.ELECTRICIAN;
            case 9:
                return Workertype.WorkerTypeEnum.PEST_CONTROL;
            case 10:
                return Workertype.WorkerTypeEnum.PACKERS_AND_MOVERS;
            case 11:
                return Workertype.WorkerTypeEnum.LAUNDARY_AND_DRY_CLEANING;
            default:
                return Workertype.WorkerTypeEnum.UNKNOWN_WORKER_TYPE;
        }

    }
}
