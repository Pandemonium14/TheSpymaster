package code.cardmods.rigs;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.TooltipInfo;
import code.SpyMod;
import code.util.TexLoader;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.sun.java.swing.action.ActionManager;

public class BombRig extends AbstractRig {

    public static final String ID = SpyMod.makeID("BombRig");
    public static final Texture tex = TexLoader.getTexture(SpyMod.makeImagePath("icons/BombRigIcon.png"));
    public static final UIStrings strings = CardCrawlGame.languagePack.getUIString(ID);

    public BombRig(int amount) {
        super(ID, tex, new TooltipInfo(strings.TEXT[0], strings.TEXT[1]));
        this.amount = amount;
    }

    @Override
    public void doEffect(AbstractCard card) {
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new BombRig(amount);
    }
}
