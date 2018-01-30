package com.dorian.db;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.JdbcTemplate;


public class Cfdi {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String q_serie_folio(final Integer usr_id) {
        String SQL = "select fac_cfds_conf_folios.serie as serie, " +
            "fac_cfds_conf_folios.folio_actual::character varying as folio " +
            "FROM gral_suc AS SUC " +
            "LEFT JOIN fac_cfds_conf ON fac_cfds_conf.gral_suc_id = SUC.id " +
            "LEFT JOIN fac_cfds_conf_folios ON fac_cfds_conf_folios.fac_cfds_conf_id = fac_cfds_conf.id " +
            "LEFT JOIN gral_usr_suc AS USR_SUC ON USR_SUC.gral_suc_id = SUC.id " +
            "WHERE fac_cfds_conf_folios.proposito = \'FAC\' " +
            "AND USR_SUC.gral_usr_id=" + usr_id;
        Logger.getLogger(Cfdi.class.getName()).log(Level.INFO, SQL);
        Map<String, Object> map_iva = this.jdbcTemplate.queryForMap(SQL);
        return (map_iva.get("serie").toString() + map_iva.get("folio").toString());
    }
}
