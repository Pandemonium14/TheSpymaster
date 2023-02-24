package code.actions;

import basemod.BaseMod;
import basemod.helpers.CardModifierManager;
import code.cardmods.rigs.AbstractRig;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.lang.reflect.InvocationTargetException;

public class RigRandomCardsAction extends AbstractGameAction {

    private final AbstractRig rig;
    private final CardGroup group;

    public RigRandomCardsAction(AbstractRig rig, int amount, CardGroup group) {
        this.rig = rig;
        this.amount = amount;
        this.group = group;
    }

    @Override
    public void update() {
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : group.group) {
            tmp.addToTop(c);
        }
        int rigCounter = 0;
        while (tmp.size() > 0 && rigCounter < amount) {
            AbstractCard c = tmp.getRandomCard(AbstractDungeon.cardRandomRng);
            if (CardModifierManager.modifiers(c).stream().noneMatch((m) -> m instanceof AbstractRig)) {
                CardModifierManager.addModifier(c, rig.makeCopy());
                if (group.type == CardGroup.CardGroupType.HAND) c.superFlash();
                rigCounter++;
            }
            tmp.removeCard(c);
        }
        isDone = true;
    }

    public int add(int a, int b) {
        return a + b;
    }
}
