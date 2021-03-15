package com.neueda.url.servicetest;


import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import com.neueda.url.service.BaseConversion;

public class BaseConversionTest {

    private BaseConversion baseConversion = new BaseConversion();

    @Test
    public void encode_lessThan62() {
        assertEquals("k", baseConversion.encode(10));
    }

    @Test
    public void encode_moreThan62() {
        assertEquals("bq", baseConversion.encode(78));
    }

    @Test
    public void decode_singleCharacter() {
        assertEquals(11, baseConversion.decode("l"));
    }
}
