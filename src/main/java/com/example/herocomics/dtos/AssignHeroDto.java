package com.example.herocomics.dtos;

public class AssignHeroDto {
    private Integer superHeroId;
    private String role;
    private String status;

    public Integer getSuperHeroId() { return superHeroId; }
    public void setSuperHeroId(Integer superHeroId) { this.superHeroId = superHeroId; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
