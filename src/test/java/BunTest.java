import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {
    private final String name;
    private final float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Bun: name={0}, price={1}")
    public static Object[][] getData() {
        return new Object[][] {
                {"black bun", 100},
                {"white bun", 200},
                {"red bun", 300},
                {"", 0},
                {null, 0}
        };
    }

    @Test
    public void testGetName() {
        Bun bun = new Bun(name, price);
        assertEquals("Bun name should match constructor parameter", name, bun.getName());
    }

    @Test
    public void testGetPrice() {
        Bun bun = new Bun(name, price);
        assertEquals("Bun price should match constructor parameter", price, bun.getPrice(), 0);
    }
}
