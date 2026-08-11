package io.github.masyumero.mekavaritia.datagen.common.recipe.pattern;

import mekanism.api.annotations.NothingNullByDefault;
import org.jetbrains.annotations.Nullable;

//Note: We don't have a 1x1 pattern as that makes more sense to be done via a shapeless recipe
@NothingNullByDefault
public class RecipePattern {

    public final String row1;
    @Nullable
    public final String row2;
    @Nullable
    public final String row3;
    @Nullable
    public final String row4;
    @Nullable
    public final String row5;
    @Nullable
    public final String row6;
    @Nullable
    public final String row7;
    @Nullable
    public final String row8;
    @Nullable
    public final String row9;


    private RecipePattern(String row1) {
        this(row1, null, null, null, null, null, null, null, null);
    }

    private RecipePattern(String row1, @Nullable String row2) {
        this(row1, row2, null, null, null, null, null, null, null);
    }

    private RecipePattern(String row1, @Nullable String row2, @Nullable String row3) {
        this(row1, row2, row3, null, null, null, null, null, null);
    }

    private RecipePattern(String row1, @Nullable String row2, @Nullable String row3,
                          @Nullable String row4, @Nullable String row5, @Nullable String row6,
                          @Nullable String row7, @Nullable String row8, @Nullable String row9) {
        this.row1 = row1;
        this.row2 = row2;
        this.row3 = row3;
        this.row4 = row4;
        this.row5 = row5;
        this.row6 = row6;
        this.row7 = row7;
        this.row8 = row8;
        this.row9 = row9;
    }

    //For 1x2 recipes
    public static RecipePattern createPattern(DoubleLine row1) {
        return new RecipePattern(row1.columns);
    }

    //For 2x1 recipes
    public static RecipePattern createPattern(char row1, char row2) {
        return new RecipePattern(Character.toString(row1), Character.toString(row2));
    }

    //For 2x2 recipes
    public static RecipePattern createPattern(DoubleLine row1, DoubleLine row2) {
        return new RecipePattern(row1.columns, row2.columns);
    }

    //For 1x3 recipes
    public static RecipePattern createPattern(TripleLine row1) {
        return new RecipePattern(row1.columns);
    }

    //For 2x3 recipes
    public static RecipePattern createPattern(TripleLine row1, TripleLine row2) {
        return new RecipePattern(row1.columns, row2.columns);
    }

    //For 3x1 recipes
    public static RecipePattern createPattern(char row1, char row2, char row3) {
        return new RecipePattern(Character.toString(row1), Character.toString(row2), Character.toString(row3));
    }

    //For 3x2 recipes
    public static RecipePattern createPattern(DoubleLine row1, DoubleLine row2, DoubleLine row3) {
        return new RecipePattern(row1.columns, row2.columns, row3.columns);
    }

    //For 3x3 recipes
    public static RecipePattern createPattern(TripleLine row1, TripleLine row2, TripleLine row3) {
        return new RecipePattern(row1.columns, row2.columns, row3.columns);
    }

    //For 9x9 ExtremeCrafting recipes
    public static RecipePattern createPattern(NonupleLine row1, NonupleLine row2, NonupleLine row3,
                                               NonupleLine row4, NonupleLine row5, NonupleLine row6,
                                               NonupleLine row7, NonupleLine row8, NonupleLine row9) {
        return new RecipePattern(row1.columns, row2.columns, row3.columns, row4.columns, row5.columns, row6.columns, row7.columns, row8.columns, row9.columns);
    }

    public static class DoubleLine {

        private final String columns;

        private DoubleLine(String columns) {
            this.columns = columns;
        }

        public static DoubleLine of(char column1, char column2) {
            return new DoubleLine(Character.toString(column1) + column2);
        }
    }

    public static class TripleLine {

        private final String columns;

        private TripleLine(String columns) {
            this.columns = columns;
        }

        public static TripleLine of(char column1, char column2, char column3) {
            return new TripleLine(Character.toString(column1) + column2 + column3);
        }
    }

    public static class NonupleLine {

        private final String columns;

        private NonupleLine(String columns) {
            this.columns = columns;
        }

        public static NonupleLine of(char column1, char column2, char column3,
                                     char column4, char column5, char column6,
                                     char column7, char column8, char column9) {
            return new NonupleLine(Character.toString(column1) + column2 + column3 + column4 + column5 + column6 + column7 + column8 + column9);
        }
    }
}
