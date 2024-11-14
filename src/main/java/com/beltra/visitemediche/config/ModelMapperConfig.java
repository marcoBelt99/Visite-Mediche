package com.beltra.visitemediche.config;

import com.beltra.visitemediche.domain.Articolo;
import com.beltra.visitemediche.dto.ArticoloDTO;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Salto i valori a null: se nel recordset che otterrò dal mio strato di persistenza sono presente
        // dei valori a null, questi saranno ignorati dal modelmapper
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        /** TODO: modelMapper.addMappings() mi mappa i campi tra Entity e EntityDto
         * Specifico la prima configurazione di mappatura, usando il metodo addMappings, a cui
         *  passo il metodo articoloMapping, che mi consente di mappare la classe Articolo rispetto
         *  alla classe ArticoloDTO
         * */
        // modelMapper.addMappings(pazienteMapping);
        modelMapper.addMappings(articoloMapping);

        /** TODO: modelMapper.addConverter() posso fare delle alterazioni che varranno per qualsiasi recordset
         *        presente nel sistema di mappatura.
         *  Uso di un convertitore
         *
         *
         * */
        modelMapper.addConverter(articoloConverter);

        // Ritorno il model mapper
        return modelMapper;
    }


    /** Per la mappatura dei campi */
    PropertyMap<Articolo, ArticoloDTO> articoloMapping = new PropertyMap<Articolo, ArticoloDTO>() {
        @Override
        protected void configure() {
            map().setDataCreazione( source.getDataCreazione() );

            // altri campi da mappare
        }
    };


    /** Per la conversione dei campi */
    Converter<String, String> articoloConverter = new Converter<String, String>() {
        @Override
        public String convert(MappingContext<String, String> mappingContext) {

    /** Verifico se la sorgente è == null.
     *  Se è null, la sostituisco con una stringa vuota.
     *  Quindi, se ad esempio i dati che ottengo dal mio DB relazionale presentano dei
     *  valori a null, questi sono riconvertiti automaticamente in una stringa vuota.
     *  Questo è molto interessante, perchè a volte nei frontend la presenza di valori null da problemi,
     *  perchè impediscono l'esecuzione di determinate operazioni. Con questo convertitore non avrò questo
     *  problema, perchè qualsiasi record set otterrò dalla mia base dati sarà automaticamente riconvertito (laddove
     *  sia a null) in una stringa vuota. Non solo ma, laddove non sia null il valore del dato che ottengo dal DB
     *  relazionale sarà trimmato, quindi sarà eliminato qualsiasi spazio vuoto
 *  TODO: la presenza di spazi in eccesso Es) nella descrizione, unità di misura o in qualunque altro campo può dare
 *        dei problemi soprattutto nella gestione degli Unit Test: gli UT sono molto precisi e, anche laddove siano
 *        presenti dei semplici spazi bianchi in eccesso, le prove (che ho posto in essere negli UT) potrebbero fallire
 *        ==> grazie a questo convertitore non ci sarà il problema, perchè qualsiasi spazio in eccesso sarà eliminato
 *        dal metodo trim().
     * */
            return mappingContext.getSource() == null ? "" : mappingContext.getSource().trim();
        }
    };


}
