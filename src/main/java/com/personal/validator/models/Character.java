package com.personal.validator.models;

public class Character {

        private String name;

        private Long healthPoints;

        private Long attack;

        private Long defense;

        private Long stamina;

        private Long intelligence;

        private Status status;


        public String getName() {

                return name;
        }

        public void setName(String name) {

                this.name = name;
        }

        public Long getHealthPoints() {

                return healthPoints;
        }

        public void setHealthPoints(Long healthPoints) {

                this.healthPoints = healthPoints;
        }

        public Long getAttack() {

                return attack;
        }

        public void setAttack(Long attack) {

                this.attack = attack;
        }

        public Long getDefense() {

                return defense;
        }

        public void setDefense(Long defense) {

                this.defense = defense;
        }

        public Long getStamina() {

                return stamina;
        }

        public void setStamina(Long stamina) {

                this.stamina = stamina;
        }

        public Long getIntelligence() {

                return intelligence;
        }

        public void setIntelligence(Long intelligence) {

                this.intelligence = intelligence;
        }

        public Status getStatus() {

                return status;
        }

        public void setStatus(Status status) {

                this.status = status;
        }
}
