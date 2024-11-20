package com.beltra.visitemediche.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "utenti")
@NoArgsConstructor
public class Utente {

    @Id
    private String id;

    private String username;

    private String password;

    @Column(name = "attivo" )
    private Boolean attivo = true;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "utente") // TODO: importantissimo che siano FetchType.EAGER !!
    private Set<Ruolo> ruoli = new HashSet<>();

    /**  */
    public Utente(String id, String username, String password, Boolean attivo, Set<Ruolo> ruoli) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.attivo = attivo;
        this.ruoli = ruoli;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }

    public Set<Ruolo> getRuoli() {
        return ruoli;
    }

    public void setRuoli(Set<Ruolo> ruoli) {
        this.ruoli = ruoli;
    }


    /** TODO: Metodo helper per gestione bidirezionale.
     *   si occupa di mantenere la relazione bidirezionale tra Utente e Ruolo.
     * */
    public void addRuolo(Ruolo ruolo) {
        this.ruoli.add(ruolo);
        ruolo.setUtente(this); // Associazione bidirezionale
    }
}
