package tech.chillo.sa.controller;

import tech.chillo.sa.service.HistoriqueService;

public class HistoriqueController {
    private HistoriqueService historiqueService;
    public HistoriqueController(HistoriqueService historiqueService){
        this.historiqueService = historiqueService;
    }

}
