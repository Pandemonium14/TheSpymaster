package code.actions;

import code.cards.AbstractEasyCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.function.Consumer;

public class ScoutAction extends AbstractGameAction {

    private final CardGroup group;
    private Consumer<AbstractCard> callback;
    private int scoutedCards = -1;

    public ScoutAction(int amount, CardGroup group) {
        this.amount = amount;
        this.group = group;
        duration = startDuration = Settings.ACTION_DUR_FAST;
    }

    public ScoutAction(int amount) {
        this(amount, AbstractDungeon.player.drawPile);
    }

    public ScoutAction(int amount, CardGroup group, Consumer<AbstractCard> callback) {
        this(amount,group);
        this.callback = callback;
    }


    @Override
    public void update() {
        if (duration == startDuration) {
            //Preparing the screen
            CardGroup tmp = new CardGroup( CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : group.group) {
                tmp.addToTop(c);
            }
            CardGroup cards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            while (cards.size() < amount && tmp.size() > 0) {
                AbstractCard c = tmp.getRandomCard(AbstractDungeon.cardRandomRng);
                cards.addToRandomSpot(c);
                tmp.removeCard(c);
            }
            scoutedCards = cards.size();
            if (scoutedCards == 0) {
                isDone = true;
            } else {
                AbstractDungeon.gridSelectScreen.open(cards, 1, false, "TODO : Localization");
                for (AbstractCard c : cards.group) {
                    if (c instanceof AbstractEasyCard) {
                        ((AbstractEasyCard)c).onSeenByScouting();
                    }
                }
                tickDuration();
            }
        } else {
            ArrayList<AbstractCard> chosenCards = AbstractDungeon.gridSelectScreen.selectedCards;
            for (AbstractCard c : chosenCards) {
                if (callback != null) callback.accept(c);
                if (c instanceof AbstractEasyCard) {
                    ((AbstractEasyCard)c).onScouted();
                    ((AbstractEasyCard)c).triggerRigs();
                }
                AbstractDungeon.player.hand.moveToHand(c, group);
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            isDone = true;
        }
    }

}
