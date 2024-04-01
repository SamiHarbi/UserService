package rosol.userservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * AppUser class labeled as @Entity to be managed by Spring Data JPA to persist AppUSer instances
 */
@Entity
public class AppUser {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String lastname;
    private LocalDate dateOfBirth;
    private char gender;
    private String nationality;
    private String phone;
    private String email;
    private String username;
    private String password;
    private String entityId;
    private int nationalId;
    private long role;
    private int permission;
    private String missionId;
    private Date creationDate;
    private Date modificationDate;
    private boolean active;

    public AppUser() {
    }

    public AppUser(String name, String lastname, LocalDate dateOfBirth, char gender, String nationality, String phone, String email,
                   String username, String password, String entityId, int nationalId, long role, int permission, String missionId,
                   Date creationDate, Date modificationDate, boolean active) {
        this.name = name;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.nationality = nationality;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.entityId = entityId;
        this.nationalId = nationalId;
        this.role = role;
        this.permission = permission;
        this.missionId = missionId;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public int getNationalId() {
        return nationalId;
    }

    public void setNationalId(int nationalId) {
        this.nationalId = nationalId;
    }

    public long getRole() {
        return role;
    }

    public void setRole(long role) {
        this.role = role;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser user = (AppUser) o;
        return id == user.id && gender == user.gender && nationalId == user.nationalId && role == user.role && permission == user.permission && active == user.active && Objects.equals(name, user.name) && Objects.equals(lastname, user.lastname) && Objects.equals(dateOfBirth, user.dateOfBirth) && Objects.equals(nationality, user.nationality) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(entityId, user.entityId) && Objects.equals(missionId, user.missionId) && Objects.equals(creationDate, user.creationDate) && Objects.equals(modificationDate, user.modificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, dateOfBirth, gender, nationality, phone, email, username, password, entityId, nationalId, role, permission, missionId, creationDate, modificationDate, active);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", nationality='" + nationality + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", entityId=" + entityId +
                ", nationalId=" + nationalId +
                ", role=" + role +
                ", permission=" + permission +
                ", missionId=" + missionId +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                ", active=" + active +
                '}';
    }
}
