import jakarta.persistence.*;
import java.util.List;

import com.beltra.visitemediche.domain.*;

@Entity
@Table(name = "Medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // oppure altro tipo di strategia di generazione, se necessario
    @Column(name = "codice")
    private Long codice;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "citta", nullable = false)
    private String citta;

    // Relazione uno-a-molti con Paziente
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paziente> pazienti;

    // Costruttori, getter e setter

    public Medico() {}

    public Medico(String cognome, String nome, String citta) {
        this.cognome = cognome;
        this.nome = nome;
        this.citta = citta;
    }

    // Getters e Setters
    public Long getCodice() {
        return codice;
    }

    public void setCodice(Long codice) {
        this.codice = codice;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public List<Paziente> getPazienti() {
        return pazienti;
    }

    public void setPazienti(List<Paziente> pazienti) {
        this.pazienti = pazienti;
    }
}
