package code.relics;

import code.TheSpymaster;

import static code.SpyMod.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheSpymaster.Enums.TODO_COLOR);
    }
}
