package com.example.herocomics.dtos;

public class RapportPerformanceDto {
    private long nbMissionsTerminees;
    private long nbMissionsLeader;
    private double tauxSucces;

    public RapportPerformanceDto(long nbMissionsTerminees, long nbMissionsLeader, double tauxSucces) {
        this.nbMissionsTerminees = nbMissionsTerminees;
        this.nbMissionsLeader = nbMissionsLeader;
        this.tauxSucces = tauxSucces;
    }

    public long getNbMissionsTerminees() {
        return nbMissionsTerminees;
    }

    public void setNbMissionsTerminees(long nbMissionsTerminees) {
        this.nbMissionsTerminees = nbMissionsTerminees;
    }

    public long getNbMissionsLeader() {
        return nbMissionsLeader;
    }

    public void setNbMissionsLeader(long nbMissionsLeader) {
        this.nbMissionsLeader = nbMissionsLeader;
    }

    public double getTauxSucces() {
        return tauxSucces;
    }

    public void setTauxSucces(double tauxSucces) {
        this.tauxSucces = tauxSucces;
    }
}