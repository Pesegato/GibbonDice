package com.pesegato.gibbondice;

import java.util.ArrayList;

import static com.pesegato.gibbondice.GibbonDice.M_gibbondice;
import static com.pesegato.gibbondice.GibbonDice.log;

public class GDNode {

    private final String outcome;
    private int weight;
    private int childrenWeights[];
    private int bag;
    private final ArrayList<GDNode> children = new ArrayList<>();

    GDNode(String outcome) {
        this.outcome = outcome;
    }

    GDNode getChild(String path) {
        if (path.indexOf('.') == -1) {
            for (GDNode child : children) {
                if (child.outcome.equals(path)) {
                    return child;
                }
            }
            return null;
        }
        String folder = path.substring(0, path.indexOf('.'));
        for (GDNode child : children) {
            if (child.outcome.equals(folder)) {
                return child.getChild(path.substring(path.indexOf('.') + 1));
            }
        }
        return null;
    }

    void makeChildfolders(String path) {
        if (path.indexOf('.') == -1) {
            for (GDNode child : children) {
                if (child.outcome.equals(path)) {
                    return;
                }
            }
            children.add(new GDNode(path));
            return;
        }
        String folder = path.substring(0, path.indexOf('.'));
        for (GDNode child : children) {
            if (child.outcome.equals(folder)) {
                child.makeChildfolders(path.substring(path.indexOf('.') + 1));
                return;
            }
        }
        GDNode child = new GDNode(folder);
        child.makeChildfolders(path.substring(path.indexOf('.') + 1));
        children.add(child);
    }

    void setWeight(int weight) {
        this.weight = weight;
    }

    void calculateChildWeights() {
        if (children.isEmpty()) {
            return;
        }
        childrenWeights = new int[children.size()];
        for (int i = 0; i < childrenWeights.length; i++) {
            childrenWeights[i] = children.get(i).weight;
            if (i > 0) {
                childrenWeights[i] += childrenWeights[i - 1];
            }
        }
        bag = childrenWeights[childrenWeights.length - 1];
        for (int i = 0; i < childrenWeights.length; i++) {
            log.info(M_gibbondice, "BaboonDice: {} {}", children.get(i).outcome, childrenWeights[i]);
        }

        for (GDNode child : children) {
            child.calculateChildWeights();
        }
    }

    public String get(String folder) {
        GDNode odd = getChild(folder).get();
        while (!odd.children.isEmpty()) {
            odd = odd.get();
        }
        return odd.outcome;
    }

    public String getWithParent(String folder) {
        return folder + "." + getChild(folder).get().outcome;
    }

    private GDNode get() {
        int rng = RNG.nextInt(bag);
        for (int i = 0; i < childrenWeights.length; i++) {
            if (rng < childrenWeights[i]) {
                return children.get(i);
            }
        }
        log.error("RNG error: outcome {} > bag {}", rng, bag);
        System.exit(-1);
        return null;
    }

}
