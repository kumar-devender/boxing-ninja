package com.ninja.boxing.app.module.bout;

public class EnemyAttributes {
    public enum Names{
        MANNY_PACQUIAO("manny pacquiao"),ROY_JONES("roy jones jr"),MAYWEATHER("floyd mayweather"),LOMACHENKO("Vasyl Lomachenko"),
        AMIR_KHAN("Amir Khan");
        private final String value;
        private Names(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
