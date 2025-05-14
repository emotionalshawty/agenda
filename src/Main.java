
public class Main {


    public static void main(String[] args) {
        TUI tui = new TUI();
        Control app = new Control();

        while (true) {
            tui.showmsg(tui.menu());
            int opcio = tui.leerint(tui.select());

            switch (opcio) {
                case 1:
                    // esto crea el contacto
                    String nom = tui.leermsg(tui.solnom());
                    String cognom = tui.leermsg(tui.solcognom());
                    String tel = tui.leermsg(tui.soltel());
                    String email = tui.leermsg(tui.solemail());

                    app.crearcontacte(nom, cognom, tel, email);
                    tui.showmsg(tui.creat() + nom);
                    break;
                case 2:
                    // buscar contacto
                    int combuscar = tui.leerint(tui.combuscar());
                    int buscarid = -1;
                    String busca = null;

                    switch (combuscar) {
                        case 1 -> busca = tui.leermsg(tui.buscanom());
                        case 2 -> busca = tui.leermsg(tui.buscacognom());
                        case 3 -> busca = tui.leermsg(tui.buscatel());
                        case 4 -> busca = tui.leermsg(tui.buscaemail());
                        case 5 -> buscarid = tui.leerint(tui.buscaid());
                        default -> {
                            tui.showmsg(tui.cantsearch());
                            continue;
                        }
                    }

                    Contacte trobat = app.buscarcontacte(combuscar, busca, buscarid);
                    if (trobat != null) {
                        tui.showmsg(tui.trobat() + trobat);
                    } else {
                        tui.showmsg(tui.notrobat());
                    }
                    break;
                case 3:
                    // actualiza contacto
                    combuscar = tui.leerint(tui.combuscar());
                    buscarid = -1;
                    String actu = null;

                    switch (combuscar) {
                        case 1 -> actu = tui.leermsg(tui.buscanom());
                        case 2 -> actu = tui.leermsg(tui.buscacognom());
                        case 3 -> actu = tui.leermsg(tui.buscatel());
                        case 4 -> actu = tui.leermsg(tui.buscaemail());
                        case 5 -> buscarid = tui.leerint(tui.buscaid());
                        default -> {
                            tui.showmsg(tui.cantsearch());
                            continue;
                        }
                    }

                    trobat = app.buscarcontacte(combuscar, actu, buscarid);
                    if (trobat != null) {
                        int quin = tui.leerint(tui.queactu());
                        String nouValor = null;

                        switch (quin) {
                            case 1 -> {
                                nouValor = tui.leermsg(tui.posnom());
                                app.actucontact(combuscar, actu, buscarid, quin, nouValor);
                                tui.showmsg(tui.nounom() + nouValor);
                            }
                            case 2 -> {
                                nouValor = tui.leermsg(tui.poscognom());
                                app.actucontact(combuscar, actu, buscarid, quin, nouValor);
                                tui.showmsg(tui.noucognom() + nouValor);
                            }
                            case 3 -> {
                                nouValor = tui.leermsg(tui.postel());
                                app.actucontact(combuscar, actu, buscarid, quin, nouValor);
                                tui.showmsg(tui.nouTel() + nouValor);
                            }
                            case 4 -> {
                                nouValor = tui.leermsg(tui.posemail());
                                app.actucontact(combuscar, actu, buscarid, quin, nouValor);
                                tui.showmsg(tui.nouemail() + nouValor);
                            }
                            default -> tui.showmsg(tui.invalid());
                        }
                    } else {
                        tui.showmsg(tui.notrobat());
                    }
                    break;
                case 4:
                    // borra la vaina
                    combuscar = tui.leerint(tui.combuscar());
                    buscarid = -1;
                    String supr = null;

                    switch (combuscar) {
                        case 1 -> supr = tui.leermsg(tui.buscanom());
                        case 2 -> supr = tui.leermsg(tui.buscacognom());
                        case 3 -> supr = tui.leermsg(tui.buscatel());
                        case 4 -> supr = tui.leermsg(tui.buscaemail());
                        case 5 -> buscarid = tui.leerint(tui.buscaid());
                        default -> {
                            tui.showmsg(tui.cantsearch());
                            continue;
                        }
                    }

                    Contacte contacteToDelete = app.buscarcontacte(combuscar, supr, buscarid);
                    if (contacteToDelete != null) {
                        String nomElim = contacteToDelete.getNom();
                        if (app.elimcontacte(combuscar, supr, buscarid)) {
                            tui.showmsg(tui.eliminat() + nomElim);
                        } else {
                            tui.showmsg(tui.notrobat());
                        }
                    } else {
                        tui.showmsg(tui.notrobat());
                    }
                    break;
                case 5:
                    // lista de todos los contactos
                    tui.showmsg(tui.llistat());
                    if (app.getContactes().isEmpty()) {
                        tui.showmsg(tui.res());
                    } else {
                        for (Contacte c : app.getContactes().values()) {
                            tui.showmsg(c.toString());
                        }
                    }
                    break;
                case 6:
                    // salir del programa
                    tui.showmsg(tui.exit());
                    System.exit(0);
                    break;
                default:
                    tui.showmsg(tui.invalidtryagain());
            }
        }

    }



}