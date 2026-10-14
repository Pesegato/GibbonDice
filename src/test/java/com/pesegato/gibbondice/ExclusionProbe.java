package com.pesegato.gibbondice;

/** Standalone regression: run with the exclusionRegression Gradle task. */
public class ExclusionProbe {
    private static void setup(String[] names, int[] weights) {
        GibbonDice.root = new GDNode("root");
        for (int i = 0; i < names.length; i++) {
            GibbonDice.root.makeChildfolders(names[i]);
            GibbonDice.root.getChild(names[i]).setWeight(weights[i]);
        }
        GibbonDice.root.calculateChildWeights();
    }

    private static void rejects(String folder, String excluded) {
        try {
            GibbonDice.getAnyExceptFor(folder, excluded);
            throw new AssertionError("Impossible exclusion accepted");
        } catch (IllegalArgumentException expected) {
            // A bad selection must fail rather than spin forever.
        }
    }

    public static void main(String[] args) {
        setup(new String[]{"single.only"}, new int[]{1});
        rejects("single", "only");
        rejects("missing", "only");

        setup(new String[]{"zero.a", "zero.b"}, new int[]{0, 0});
        rejects("zero", "a");

        setup(new String[]{"two.a", "two.b"}, new int[]{100, 1});
        for (int i = 0; i < 100; i++) {
            if (!GibbonDice.getAnyExceptFor("two", "a").equals("b")) {
                throw new AssertionError("Excluded result returned");
            }
        }

        setup(new String[]{"nested.branch", "nested.branch.a", "nested.branch.b"},
                new int[]{1, 1, 1});
        if (!GibbonDice.getAnyExceptFor("nested", "a").equals("b")) {
            throw new AssertionError("Nested alternative not selected");
        }
        System.out.println("PASS impossible, missing, zero-weight, viable and nested exclusions");
    }
}
