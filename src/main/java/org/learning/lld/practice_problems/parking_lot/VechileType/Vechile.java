package org.learning.lld.practice_problems.parking_lot.VechileType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public abstract class Vechile {
    protected String licenceNumber;
    protected VechileType vechileType;
}
