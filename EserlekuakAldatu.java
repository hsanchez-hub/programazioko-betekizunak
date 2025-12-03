public class EserlekuakAldatu {

    public EserlekuakAldatu() {
    }
/*
Eserlekuz aldatzeko balio duen funtzioa
@param 2 zenbaki eta 2 string (lehenengo zenbakia eta String-a lehenengo eserlekuarena
 eta bestea bigarren eserlekua da aldatu nahi duzuna)
 */
    public static void erreserbaAldatu(int zenb, String nondik, int eserleku, String nora){
        String[] abc=new String[]{"A","B","C","D"};
        String[][] taula= new String[100/ abc.length][abc.length];
        int indz=-1;
        int indl=-1;
        int inde=-1;
        int indn=-1;

        for (int i=0;i< abc.length;i++){
            if (nondik.equals(abc[i])){
                indl=i;
            }
        }
        for (int j=0;j<100/ abc.length;j++) {
            if (j == zenb) {
                indz = j;
            }
        }

        for (int i=0;i< abc.length;i++){
            if (nora.equals(abc[i])){
                indn=i;
            }
        }
        for (int j=0;j<100/ abc.length;j++) {
            if (j == eserleku) {
                inde = j;
            }
        }


        if (taula[indz-1][indl].equals(" X |")){
            taula[indz-1][indl]="   |";
            if (taula[inde-1][indn].equals(" X |")){
                System.out.println("Sartu beste eserleku bat.");
            }else{
                taula[inde-1][indn]=" X |";
            }
        }
    }

/*
Eserlekua aldatzearengatik ordaindu behar dezun prezioa(+%5)
@param nondik nora prezioa kalkulatzeko eta igoera egiteko(Geltokien izenak)
 */
    public static double erreserbaAldaketaPrezioa(String nondi, String nor){

        return Geltokia.artekoPrezio(nondi,nor) * 1.05;
    }
    public static void erakutsi() {
        String[] abc=new String[]{"A","B","C","D"};
        String[][] taula= new String[100/ abc.length][abc.length];
        for (int i = 0; i < 100 / abc.length; i++) {
            if (i > 8) {
                System.out.print((i + 1) + "|");
            } else {
                System.out.print((i + 1) + " |");
            }
            for (int k = 0; k < abc.length; k++) {
                if (taula[i][k] == null) {
                    System.out.print("   |");
                } else {
                    System.out.print(taula[i][k]);
                }
            }
            System.out.println();
        }
    }
}
