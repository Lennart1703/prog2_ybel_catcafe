package catcafe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CatCafeTest {

    private CatCafe cafe;

    @BeforeEach
    void setUp() {
        cafe = new CatCafe();
    }

    @Test
    void shouldReturnZeroForEmptyCafe() {
        assertEquals(0, cafe.getCatCount());
    }

    @Test
    void shouldIncreaseCountWhenCatIsAdded() {
        cafe.addCat(new FelineOverLord("Luna", 4));

        assertEquals(1, cafe.getCatCount());
    }

    @Test
    void shouldReturnCorrectCatByName() {
        FelineOverLord cat = new FelineOverLord("Simba", 5);
        cafe.addCat(cat);

        assertSame(cat, cafe.getCatByName("Simba"));
    }

    @Test
    void shouldReturnNullWhenNameDoesNotExist() {
        cafe.addCat(new FelineOverLord("Simba", 5));

        assertNull(cafe.getCatByName("Garfield"));
    }

    @Test
    void shouldReturnNullForNullName() {
        assertNull(cafe.getCatByName(null));
    }

    @Test
    void shouldReturnCatWithinWeightRange() {
        FelineOverLord cat = new FelineOverLord("Mimi", 4);
        cafe.addCat(cat);

        assertSame(cat, cafe.getCatByWeight(3, 5));
    }

    @Test
    void shouldIncludeLowerWeightBoundary() {
        FelineOverLord cat = new FelineOverLord("Balu", 5);
        cafe.addCat(cat);

        assertSame(cat, cafe.getCatByWeight(5, 10));
    }

    @Test
    void shouldExcludeUpperWeightBoundary() {
        cafe.addCat(new FelineOverLord("Rex", 10));

        assertNull(cafe.getCatByWeight(5, 10));
    }

    @Test
    void shouldReturnNullForNegativeMinWeight() {
        assertNull(cafe.getCatByWeight(-1, 5));
    }

    @Test
    void shouldReturnNullWhenMaxWeightIsSmallerThanMinWeight() {
        assertNull(cafe.getCatByWeight(10, 5));
    }

    @Test
    void shouldThrowExceptionWhenAddingNull() {
        assertThrows(NullPointerException.class, () -> cafe.addCat(null));
    }
}
