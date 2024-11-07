import jakarta.persistence.*;

@Entity
@Table(name = "Paziente")
public class Paziente {

    @Id
    @Column(name = "codice_fiscale")
    private String codiceFiscale;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Column(name = "nome", nullable = false)
    private String nome;

    // Relazione molti-a-uno con Medico
    @ManyToOne
    @JoinColumn(name = "medico", nullable = false) // 'medico' è la chiave esterna in Paziente che si riferisce a Medico.codice
    private Medico medico;

    // Costruttori, getter e setter

    public Paziente() {}

    public Paziente(String codiceFiscale, String cognome, String nome, Medico medico) {
        this.codiceFiscale = codiceFiscale;
        this.cognome = cognome;
        this.nome = nome;
        this.medico = medico;
    }

    // Getters e Setters
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
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

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
