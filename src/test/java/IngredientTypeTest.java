import org.junit.Test;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {
    @Test
    public void testIngredientTypeValuesCount() {
        IngredientType[] types = IngredientType.values();
        assertEquals("There should be exactly 2 ingredient types", 2, types.length);
    }

    @Test
    public void testFirstIngredientTypeIsSauce() {
        IngredientType[] types = IngredientType.values();
        assertEquals("First ingredient type should be SAUCE", IngredientType.SAUCE, types[0]);
    }

    @Test
    public void testSecondIngredientTypeIsFilling() {
        IngredientType[] types = IngredientType.values();
        assertEquals("Second ingredient type should be FILLING", IngredientType.FILLING, types[1]);
    }

    @Test
    public void testValueOfSauce() {
        assertEquals("SAUCE should be resolved correctly", IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }

    @Test
    public void testValueOfFilling() {
        assertEquals("FILLING should be resolved correctly", IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}

