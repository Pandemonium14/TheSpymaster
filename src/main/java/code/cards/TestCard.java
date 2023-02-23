package code.cards;

import code.actions.ScoutAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.SpyMod.makeID;

public class TestCard extends AbstractEasyCard {
    public final static String ID = makeID("TestCard");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public TestCard() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        magicNumber = baseMagicNumber = 4;
        name = "TestCard";
        rawDescription = "Scout !M!.";
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ScoutAction(magicNumber));
    }

    public void upp() {
        upgradeBlock(3);
    }
}
