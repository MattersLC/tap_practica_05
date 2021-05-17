package polinomio;

import java.io.Serializable;

public class Polinomio implements Serializable {

    protected double coeficiente[];
    protected static int MAX_GRADO = 3; // Para este caso muestra
    protected static int MIN_GRADO = 1;
    protected int grado;

    public Polinomio() {
        this.grado = MAX_GRADO;
        this.coeficiente = new double[MAX_GRADO+1];
        for (int c=0; c<=MAX_GRADO; c++) {
            this.coeficiente[c] = 0.0d;
        }
    }

    public Polinomio(double[] coeficiente) throws Exception {
        if (coeficiente.length > MIN_GRADO) {
            this.grado = coeficiente.length - 1;
            this.coeficiente = coeficiente;
        } else {
            throw new Exception("El número de coeficientes debe ser > 1");
        }
    }

    public void setCoeficiente(double coeficiente[]) throws Exception {
        //grado = coefs.length-1;
        // Asegura el mínimo de coeficientes
        if (coeficiente.length > MIN_GRADO) {
            this.grado = coeficiente.length - 1;
            this.coeficiente = coeficiente;
        } else {
            throw new Exception("Número de coeficientes debe ser mayor a uno");
        }
    }

    public int getGrado() {
        return grado;
    }

    public double[] getCoeficientes() {
        return coeficiente;
    }

    public double getY(double x) {
        double y = 0.0;
        for (int c=0; c<=grado; c++) {
            y += coeficiente[c] * Math.pow(x, c);
        }
        return y;
    }

    public double getDerFx(double x) {
        double dx = 0;
        for (int c=1; c<grado; c++) {
            dx+= c * coeficiente[c] * Math.pow(x, c-1);
        }
        return dx;
    }

    public String getPolinomio() {
        // Regresa una cadena representando al polinomio, como por ejemplo
        // 1.01-3.0x+2.0x^2
        String polinomio = "";

        polinomio += coeficiente[0];
        for (int i = 1; i < coeficiente.length; i++) {
            polinomio += " + ";
            if (i == 1) {
                polinomio += coeficiente[i] + "X";
            } else {
                polinomio += coeficiente[i] + "X^" + i;
            }

        }

        return polinomio;
    }

    public double[] getRaices() throws Exception {
        double[] x = null;
        if (grado==1) {
            // Ecuación lineal, cálculo de x
            x = new double[grado];
            if (coeficiente[0]!=0) {
                x[0] = -1 * coeficiente[0] / coeficiente[1];
            } else {
                throw new Exception("El coeficiente de x debe ser != 0");
            }
        } else if (grado==2) {
            // Ecuación cuadrática
            // Regresa las raíces de la ecuación x[0] y x[1] si son reales, si son imaginarios: x[0] es el valor real y
            // x[1] y x[2] son los valores imaginarios
            // Usando la fórmula general:
            double a = coeficiente[2];
            double b = coeficiente[1];
            double c = coeficiente[0];
            double rad = Math.pow(b, 2.0) - 4*a*c;
            if (rad>=0) { // Raíces reales
                x = new double[2];
                x[0] = (-b + Math.sqrt(rad)) / (2*a);
                x[1] = (-b - Math.sqrt(rad)) / (2*a);
            } else { // Raíces imaginarias
                x = new double[4];
                rad = -1 * rad;
                x[0] = x[2]= -b/(2*a); // Parte real
                x[1] = Math.sqrt(rad) / (2 * a); //parte real positiva
                x[3] = -1 * (Math.sqrt(rad) / (2 * a)); //parte real negativa
            }
        } else if (grado > 2) { // Utilizando el método Newton-Raphson
            double derivada;
            final int MAX_ITERA = 100;
            final double PRECISION = 0.001;
            x = new double[grado -2];
            double error = 999.0, xi=0, x1=0;
            int i=0, n=0;
            for (n=0; n<grado-2; n++) {
                i=0;
                do {
                    xi = x1;
                    x1 = xi - (getY(xi)/getDerFx(xi));
                    error = (Math.abs(x1-xi))*100;
                    if (error < PRECISION) {
                        break;
                    }
                    i++;
                } while (i < MAX_ITERA);
                x[n] = xi;
            }
        }
        return x;
    }
}
