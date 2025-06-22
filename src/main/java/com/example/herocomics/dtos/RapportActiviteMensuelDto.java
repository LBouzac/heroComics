package com.example.herocomics.dtos;

public class RapportActiviteMensuelDto {

    private int mois;
    private int annee;
    private long nouvellesMissions;
    private long missionsCompletees;
    private long superherosParticipants;
    private String missionLaPlusLongueNom;
    private long missionLaPlusLongueDuree;

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public long getNouvellesMissions() {
        return nouvellesMissions;
    }

    public void setNouvellesMissions(long nouvellesMissions) {
        this.nouvellesMissions = nouvellesMissions;
    }

    public long getMissionsCompletees() {
        return missionsCompletees;
    }

    public void setMissionsCompletees(long missionsCompletees) {
        this.missionsCompletees = missionsCompletees;
    }

    public long getSuperherosParticipants() {
        return superherosParticipants;
    }

    public void setSuperherosParticipants(long superherosParticipants) {
        this.superherosParticipants = superherosParticipants;
    }

    public String getMissionLaPlusLongueNom() {
        return missionLaPlusLongueNom;
    }

    public void setMissionLaPlusLongueNom(String missionLaPlusLongueNom) {
        this.missionLaPlusLongueNom = missionLaPlusLongueNom;
    }

    public long getMissionLaPlusLongueDuree() {
        return missionLaPlusLongueDuree;
    }

    public void setMissionLaPlusLongueDuree(long missionLaPlusLongueDuree) {
        this.missionLaPlusLongueDuree = missionLaPlusLongueDuree;
    }
}
