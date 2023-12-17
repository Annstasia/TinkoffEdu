package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;

public record SimpleRecord(int justInt,
                           @NotNull @Min(value = 10) Integer bigInteger,
                           @Min(value = 10) @Max(value = 100) Integer someInteger,
                           @Min(value = 'a') @Max(value = 'z' + 1) char someChar) {
}
