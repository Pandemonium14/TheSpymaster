package code.actions;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class RemoveModifierAction extends AbstractGameAction {

    private final AbstractCard card;
    private final AbstractCardModifier mod;

    public RemoveModifierAction(AbstractCard c, AbstractCardModifier m) {
        card = c;
        mod = m;
    }

    @Override
    public void update() {
        CardModifierManager.removeSpecificModifier(card,mod,true);
        isDone = true;
    }
}
