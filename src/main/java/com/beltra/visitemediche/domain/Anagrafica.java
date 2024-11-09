package com.beltra.visitemediche.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "anagrafiche")
public class Anagrafica {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "telefono")
    private Long telefono;

    @Column(name = "email")
    private String email;


// TODO: La relazione che intercorre tra anagrafiche e pazienti e' 1:1 quindi uso la @OneToOne
    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "codice_fiscale")
    private Paziente paziente;



    public Anagrafica() {}

    public Anagrafica(Long id, Long telefono, String email) {
        this.id = id;
        this.telefono = telefono;
        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
