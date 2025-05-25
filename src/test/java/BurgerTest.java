import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerTest {
    private Burger burger;
    private Bun bun;
    private Ingredient ingredient1;
    private Ingredient ingredient2;

    private final String bunName;
    private final float bunPrice;
    private final IngredientType ingredientType1;
    private final String ingredientName1;
    private final float ingredientPrice1;
    private final IngredientType ingredientType2;
    private final String ingredientName2;
    private final float ingredientPrice2;

    public BurgerTest(String bunName, float bunPrice,
                      IngredientType ingredientType1, String ingredientName1, float ingredientPrice1,
                      IngredientType ingredientType2, String ingredientName2, float ingredientPrice2) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientType1 = ingredientType1;
        this.ingredientName1 = ingredientName1;
        this.ingredientPrice1 = ingredientPrice1;
        this.ingredientType2 = ingredientType2;
        this.ingredientName2 = ingredientName2;
        this.ingredientPrice2 = ingredientPrice2;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"black bun", 100, IngredientType.SAUCE, "hot sauce", 100, IngredientType.FILLING, "cutlet", 100},
                {"white bun", 200, IngredientType.SAUCE, "sour cream", 200, IngredientType.FILLING, "dinosaur", 200},
                {"red bun", 300, IngredientType.SAUCE, "chili sauce", 300, IngredientType.FILLING, "sausage", 300}
        };
    }

    @Before
    public void setUp() {
        burger = new Burger();

        bun = mock(Bun.class);
        when(bun.getName()).thenReturn(bunName);
        when(bun.getPrice()).thenReturn(bunPrice);

        ingredient1 = mock(Ingredient.class);
        when(ingredient1.getType()).thenReturn(ingredientType1);
        when(ingredient1.getName()).thenReturn(ingredientName1);
        when(ingredient1.getPrice()).thenReturn(ingredientPrice1);

        ingredient2 = mock(Ingredient.class);
        when(ingredient2.getType()).thenReturn(ingredientType2);
        when(ingredient2.getName()).thenReturn(ingredientName2);
        when(ingredient2.getPrice()).thenReturn(ingredientPrice2);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void testGetPrice() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float expectedPrice = bunPrice * 2 + ingredientPrice1 + ingredientPrice2;
        assertEquals(expectedPrice, burger.getPrice(), 0);
    }

    @Test
    public void testGetReceipt() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains(bunName));
        assertTrue(receipt.contains(ingredientType1.toString().toLowerCase()));
        assertTrue(receipt.contains(ingredientName1));
        assertTrue(receipt.contains(ingredientType2.toString().toLowerCase()));
        assertTrue(receipt.contains(ingredientName2));
        String expectedPriceString = String.format("%.6f", burger.getPrice()).replaceAll("0*$", "").replaceAll("\\.$", "");
        assertTrue("Receipt should contain price: " + expectedPriceString, receipt.contains(expectedPriceString));
    }

    @Test(expected = NullPointerException.class)
    public void testGetReceiptWithNullIngredientType() {
        Ingredient nullTypeIngredient = mock(Ingredient.class);
        when(nullTypeIngredient.getType()).thenReturn(null);
        when(nullTypeIngredient.getName()).thenReturn("null ingredient");

        burger.setBuns(bun);
        burger.addIngredient(nullTypeIngredient);

        burger.getReceipt();
    }
}