package com.pesegato.gibbondice;

import com.google.gson.Gson;
import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.pesegato.goldmonkey.GM;
import com.pesegato.goldmonkey.Outcome;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class GibbonDice extends BaseAppState {

    public static Marker M_gibbondice = MarkerFactory.getMarker("GibbonDice");
    static Logger log = LoggerFactory.getLogger(GibbonDice.class);

    static GDNode root = new GDNode("root");
    public static HashMap<String, Outcome> outcomes;

    public static Outcome getOutcome(String id) {
        log.debug("Loading outcome {}", id);
        try {
            return outcomes.get(id);
        } catch (NullPointerException e) {
            log.error("Can't find outcome: {}", id, e);
        }
        return null;
    }

    public static int getWeight(String id) {
        return getOutcome(id).weight;
    }

    @Override
    protected void initialize(Application app) {
        try {
            outcomes = new HashMap<>();
            Outcome[] data = new Gson().fromJson(GM.getJSON("GibbonDice"), Outcome[].class);
            for (Outcome obj : data) {
                outcomes.put(obj.id, obj);
            }
        } catch (FileNotFoundException ex) {
            log.error(null, ex);
        }
        for (String key : outcomes.keySet()) {

            root.makeChildfolders(key);
            root.getChild(key).setWeight(outcomes.get(key).weight);
        }
        root.calculateChildWeights();

    }

    public static String get(String folder) {
        return root.get(folder);
    }

    public static String getAnyExceptFor(String folder, String unobtainable) {
        String outcome = null;
        do {
            outcome = root.get(folder);
        } while (outcome.equals(unobtainable));
        return outcome;
    }

    @Override
    protected void cleanup(Application app) {
    }

    @Override
    protected void onEnable() {
    }

    @Override
    protected void onDisable() {
    }
}
