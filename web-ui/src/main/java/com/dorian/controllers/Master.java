package com.dorian.controllers;

import com.dorian.session.Base64Coder;
import com.dorian.session.UserSessionData;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public class Master {

    private String templateFolder;
    private String appName;
    private String layoutHeader = "./vm/layouts/header.vm";
    private String layoutFooter = "./vm/layouts/footer.vm";
    private String layoutMenu = "./vm/layouts/menu.vm";

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setTemplateFolder(String templateFolder) {
        this.templateFolder = templateFolder;
    }

    private String generaGrid(HashMap<String, String> infoConstruccionTabla) {
        DatagridHelper grid = new DatagridHelper();
        grid.setConfigHeader(infoConstruccionTabla);
        grid.formaTabla();
        return grid.getGridString();
    }

    private ModelAndView staticContent(ModelAndView mv) {

        mv = mv.addObject("layoutheader", this.layoutHeader);
        mv = mv.addObject("layoutmenu", this.layoutMenu);
        mv = mv.addObject("layoutfooter", this.layoutFooter);
        return mv;
    }

    private ModelAndView dynamicContent(ModelAndView mv,
            HttpServletRequest request, final UserSessionData user,
            final HashMap<String, String> infoConstruccionTabla) {

        mv = mv.addObject("grid", this.generaGrid(infoConstruccionTabla));
        mv = mv.addObject("url", request.getContextPath());
        mv = mv.addObject("username", user.getUserName());
        mv = mv.addObject("empresa", user.getRazonSocialEmpresa());
        mv = mv.addObject("sucursal", user.getSucursal());
        return mv;
    }

    public ModelAndView drawClassic(HttpServletRequest request,
            HttpServletResponse response,
            UserSessionData user,
            final HashMap<String, String> infoConstruccionTabla) throws ServletException, IOException {

        ModelAndView mv = new ModelAndView(this.templateFolder + "/display",
                "title", this.appName);
        mv = this.staticContent(mv);
        mv = this.dynamicContent(mv, request, user, infoConstruccionTabla);
        
        /* id de usuario codificado */
        mv = mv.addObject("iu", Base64Coder.encodeString(
                String.valueOf(user.getUserId())));
        
        return mv;
    }

    private class DatagridHelper {

        private HashMap<String, String> configHeader;
        private String gridString;

        public void setGridString(String gridString) {
            this.gridString = gridString;
        }

        public String getGridString() {
            return gridString;
        }

        public void setConfigHeader(HashMap<String, String> configHeader) {
            this.configHeader = configHeader;
        }

        public HashMap<String, String> getConfigHeader() {
            return configHeader;
        }

        public void formaTabla() {
            String cuerpo_tabla = new String();

            cuerpo_tabla = "<table id=\"pag1\" class=\"tablesorter pagedemo _current\" >";
            cuerpo_tabla += "<thead style=\"border-color:#303D74;\">";
            cuerpo_tabla += "<tr class=\"header backgroundheader\" height=\"30px\">";

            int ancho_sumatoria = 0;

            Iterator it2 = this.getConfigHeader().keySet().iterator();
            while (it2.hasNext()) {
                String key = (String) it2.next();
                String value = (String) this.getConfigHeader().get(key);
                String[] separados = null;
                separados = value.split(":");
                if (separados.length > 0) {
                    cuerpo_tabla += "<th width=" + separados[1] + " class=\"" + key + "\"><span class=\"header__\" style=\"display:none;\">" + key + "</span>" + separados[0] + "&nbsp;&nbsp;&nbsp;&nbsp;</th>";
                    ancho_sumatoria++;
                }
            }

            cuerpo_tabla += "</tr></thead>";
            cuerpo_tabla += "<tfoot ><tr align=\"center\">";
            cuerpo_tabla += "<th  colspan=" + ancho_sumatoria + "  height=\"30px\">";
            cuerpo_tabla += "<div style=\"height:25px;\"><div id=\"gridpaginator_data\" style=\"width:100%;height:20px;margin:0 auto;text-align:center;\">"
                    + "</div></div>";
            cuerpo_tabla += "</th></tr></tfoot><tbody></tbody></table>";

            this.setGridString(cuerpo_tabla + "\n");

        }
    }
}
