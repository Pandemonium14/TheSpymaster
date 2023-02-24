package code.cards;

import code.actions.RigRandomCardsAction;
import code.actions.ScoutAction;
import code.cardmods.rigs.BombRig;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.SpyMod.makeID;

public class TestCard extends AbstractEasyCard {
    public final static String ID = makeID("TestCard");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public TestCard() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        magicNumber = baseMagicNumber = 4;
        name = "TestCard";
        rawDescription = "spymod:Rig 3 random cards in hand : Deal !M! damage to ALL enemies.";
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RigRandomCardsAction(new BombRig(magicNumber),3, AbstractDungeon.player.hand));
    }

    public void upp() {
        upgradeBlock(3);
    }
}
