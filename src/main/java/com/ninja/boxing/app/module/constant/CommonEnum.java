package com.ninja.boxing.app.module.constant;


/**
 * @author DEVENDER
 * This file represent enum constants that can be seen as group. 
 */
public class CommonEnum {

    public enum WelcomeMenuItem{
        STARTNEWGAME("Start a new game"),RESUMEGAME("Resume existing game");
        private final String value;
        private WelcomeMenuItem(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public enum FightResult{
        DRAW,SPLIT_DECISION,ANANIMOUS_DECISION,MAJORITY_DECISION,KNOCKOUT,TECHNICAL_KNOCKOUT,DISQUALIFICATION
    }

    public enum BoxingTitle{
        WBA,WBC,WBO,IBF,BOCREC,THE_RING
    }

    public enum WeightCategory{
        PIN_WEIGHT("Pin Weight"),LIGHT_FLY_WEIGHT("Light Fly Weight"),FLYWEIGHT("Fly Weight"),
        LIGHT_BANTAM_WEIGHT("Light Bantam Weight"),BANTAM_WEIGHT("Bantam Weight"),FEATHERWEIGHT("Feather Weight"),
        LIGHT_WELTER_WEIGHT("Light Welter Weight"),WELTER_WEIGHT("Welter Weight"),LIGHT_MIDDLE_WEIGHT("Light Middle Weight")
        ,MIDDLE_WEIGHT("Middle Weight"),LIGHT_HEAVY_WEIGHT("Light Heavy Weight"),
        HEAVY_WEIGHT("Heavy Weight"),SUPER_HEAVY_WEIGHT("Super heavy Weight");
        private final String value;
        private WeightCategory(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    /**
     * This indicate defense %. Actual energy of player who is taking punch will be deducted according to this percentage.
     *
     */
    public enum Defence{
        SLIPPING(.80),BOBBING(.70),BLOCKING(.75)
        ,COVERUP(.65),CLINCH(.90),FOOTWORK(.95),PULLAWAY(.70);
        private final Double value;
        private Defence(final Double value) {
            this.value = value;
        }

        public double getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return Double.toString(value);
        }
    }

    public enum Punching{
        JAB("jab"),CROSS("cross"),HOOK("hook"),UPPERCUT("upper cut"),OVERHEADRIGHT("over head right"),CHECKHOOK("check hook");
        private final String value;
        private Punching(final String value) {
            this.value = value;
        }
        @Override
        public String toString() {
            return value;
        }
    }

    public enum  PunchingPower{
        TEN(10),TWENTY(20),THIRTY(30),FOURTY(40),FIFTY(50);
        private final Integer value;
        private PunchingPower(final Integer value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return Integer.toString(value);
        }
    }

    public enum FightStatus{
        IN_PROGRESS("In progress"),SAVED("Paused and saved."),COMPLETED("Completed");
        private final String value;
        private FightStatus(final String value){
            this.value = value;
        }
        @Override
        public String toString() {
            return value;
        }
    }
    public enum Stances{
        UPRIGHT("upright stance"),SEMICROUCH("semi crouch"),FULLCROUCH("full crouch");
        private final String value;
        private Stances(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public enum Level{
        Level1("Beginning professional"),
        Level2("Tomato Cans"),
        Level3("Young Prospect"),
        Level4("Journey Men"),
        Level5("Shot/Washed up Name"),
        Level6("Gate Keepers"),
        Level7("Contenders"),
        Level8("Belt Holder/champion"),
        Level9("Elite Fighter"),
        Level10("Super Star/ Legend"),;
        private final String value;
        private Level(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public enum BoutAction {
        ATTACK("Attack over opponent"),
        DEFENSE("Defense yourself"),
        DO_NOTHING("Do nothing"),
        SAVE_GAME("Save and exit game");;

        private final String title;

        BoutAction(final String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
