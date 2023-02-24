package code.cardmods.rigs;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import code.actions.RemoveModifierAction;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRig extends AbstractCardModifier {

    public final String rigId;
    public final Texture texture;
    public final TooltipInfo tipInfo;
    public int amount = -1;

    public AbstractRig(String ID, Texture tex, TooltipInfo info) {
        rigId = ID;
        texture = tex;
        tipInfo = info;
    }

    @Override
    public List<TooltipInfo> additionalTooltips(AbstractCard card) {
        ArrayList<TooltipInfo> list = new ArrayList<>();
        list.add(tipInfo);
        return list;
    }

    @Override
    public void onDrawn(AbstractCard card) {
        trigger(card);
    }

    public void onScouted(AbstractCard card) {
        trigger(card);
    }

    public void trigger(AbstractCard card) {
        doEffect(card);
        AbstractDungeon.actionManager.addToBottom(new RemoveModifierAction(card,this));
    }

    public abstract void doEffect(AbstractCard card);

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        ExtraIcons.icon(texture).text(String.valueOf(amount)).render(card);
    }
}
