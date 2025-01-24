package org.learning.lld.practice_problems.parking_lot.VechileType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class Vechile {
    protected String licenceNumber;
    protected VechileType vechileType;

    @Override
    public String toString() {
        String msg = String.format("%s{licenceNumber: %s}", vechileType.toString(), licenceNumber);
        return msg;
    }
}
