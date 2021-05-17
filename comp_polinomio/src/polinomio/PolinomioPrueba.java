package polinomio;

public class PolinomioPrueba {

    private double[] pol_G1 = {2, 6};
    private double[] pol_G2 = {-1, 7, -10};
    private double[] pol_G2_1 = {-1, 4, -7};
    private double[] pol_G3 = {1, -4, -3, -10};
    private double[][] pol = {pol_G1, pol_G2, pol_G2_1, pol_G3};

    public PolinomioPrueba() throws Exception {
        Polinomio p = new Polinomio();
        System.out.println("*********************************");
        for (int i=0; i<pol.length; i++) {
            p.setCoeficiente(pol[i]);
            System.out.println("Ecuación de grado "+p.getGrado());
            String sPolinomio = p.getPolinomio();
            System.out.println(sPolinomio);
            int j =0;
            for (double res : p.getRaices()) {
                System.out.println("X"+j+"= "+res);
                j++;
            }
            System.out.println("*********************************");
        }
    }

    public static void main(String[] args) throws Exception {
        new PolinomioPrueba();
    }
}
